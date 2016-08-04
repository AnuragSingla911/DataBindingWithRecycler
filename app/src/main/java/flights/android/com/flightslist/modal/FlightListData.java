package flights.android.com.flightslist.modal;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by jade on 3/8/16.
 */

public class FlightListData implements Parcelable{

    private HashMap<String,String> flightNameMapping = new HashMap<>();

    private HashMap<String,String> cityNameMapping = new HashMap<>();

    private ArrayList<Flight> mflightList = new ArrayList<>();

    public FlightListData(){}

    protected FlightListData(Parcel in) {
        mflightList = in.createTypedArrayList(Flight.CREATOR);

        cityNameMapping = (HashMap<String, String>) in.readSerializable();

        flightNameMapping = (HashMap<String, String>) in.readSerializable();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(mflightList);

        dest.writeSerializable(cityNameMapping);

        dest.writeSerializable(flightNameMapping);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<FlightListData> CREATOR = new Creator<FlightListData>() {
        @Override
        public FlightListData createFromParcel(Parcel in) {
            return new FlightListData(in);
        }

        @Override
        public FlightListData[] newArray(int size) {
            return new FlightListData[size];
        }
    };

    public HashMap<String, String> getFlightNameMapping() {
        return flightNameMapping;
    }

    public void setFlightNameMapping(HashMap<String, String> flightNameMapping) {
        this.flightNameMapping = flightNameMapping;
    }

    public HashMap<String, String> getCityNameMapping() {
        return cityNameMapping;
    }

    public void setCityNameMapping(HashMap<String, String> cityNameMapping) {
        this.cityNameMapping = cityNameMapping;
    }

    public ArrayList<Flight> getMflightList() {
        return mflightList;
    }

    public void setMflightList(ArrayList<Flight> mflightList) {
        this.mflightList = mflightList;
    }
}
