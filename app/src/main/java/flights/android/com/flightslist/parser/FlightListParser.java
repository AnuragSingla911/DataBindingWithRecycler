package flights.android.com.flightslist.parser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;

import flights.android.com.flightslist.modal.Flight;
import flights.android.com.flightslist.modal.FlightListData;
import flights.android.com.flightslist.network.Response;

/**
 * Created by jade on 3/8/16.
 */

public class FlightListParser implements Parser {


    @Override
    public Response<FlightListData> parseResponse(String body) {
        Response<FlightListData> response = new Response<>();

        try {

            FlightListData data = new FlightListData();
            JSONObject object = new JSONObject(body);

            JSONObject airLineMapJson = object.getJSONObject(Keys.AIRLINE_MAP);
            if (airLineMapJson != null && airLineMapJson.keys() != null) {
                HashMap<String, String> airLineMap = new HashMap<>();
                Iterator<String> keys = airLineMapJson.keys();
                while (keys.hasNext()) {
                    String key = keys.next();
                    airLineMap.put(key, airLineMapJson.optString(key));
                }

                data.setFlightNameMapping(airLineMap);
            }


            JSONObject airportMapJson = object.getJSONObject(Keys.AIRPORT_MAP);
            if (airportMapJson != null && airportMapJson.keys() != null) {
                HashMap<String, String> airPortMap = new HashMap<>();
                Iterator<String> keys = airportMapJson.keys();
                while (keys.hasNext()) {
                    String key = keys.next();
                    airPortMap.put(key, airportMapJson.optString(key));
                }
                data.setCityNameMapping(airPortMap);
            }

            JSONArray flightListData = object.optJSONArray(Keys.FLIGHTS_DATA);
            if(flightListData != null && flightListData.length() > 0){
                ArrayList<Flight> flightList = new ArrayList<>();

                for(int i =0; i< flightListData.length() ; i++){
                   flightList.add(parseFlight(flightListData.optJSONObject(i),data));
                }
                data.setMflightList(flightList);
            }

            response.setData(data);


        } catch (JSONException e) {
            e.printStackTrace();
            response.setStatusCode(Response.ERROR_RESPONSE_CODE);
            return response;
        }
        response.setStatusCode(Response.SUCCESS_RESPONSE_CODE);
        return response;
    }

    private String getDuration(long time){
        long Hours = time/(1000 * 60 * 60);
        long Mins = time % (1000*60*60);
        String diff = (Hours < 10 ? "0"+ Hours : Hours) + ":" + (Mins < 10 ? "0"+ Mins : Mins);
        return diff;
    }

    private String getDate(long time) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm a");
        Date date = new Date();
        date.setTime(time);
        String dateString = sdf.format(date);
        return dateString;
    }

    public Flight parseFlight(JSONObject object,FlightListData data) throws JSONException {
        Flight flight = new Flight();

        flight.setAirlineCode(data.getFlightNameMapping().get(object.optString(Keys.AIRLINE_CODE)));
        flight.setClassType(object.optString(Keys.CLASS_TYPE));
        flight.setDestinationCode("To : "+data.getCityNameMapping().get(object.optString(Keys.DESTINATION_CODE)));
        flight.setLandingTime(getDate(object.optLong(Keys.LANDING_TIME)));
        flight.setOriginalCode("From : "+data.getCityNameMapping().get(object.optString(Keys.ORIGIN_CODE)));
        flight.setPrice("Rs. "+object.optString(Keys.PRICE));
        flight.setTakeOffTime(getDate(object.optLong(Keys.TAKE_OFF_TIME)));
        long diff = object.optLong(Keys.LANDING_TIME) - object.optLong(Keys.TAKE_OFF_TIME);
        flight.setDuration(getDuration(diff));

        return flight;
    }
}
