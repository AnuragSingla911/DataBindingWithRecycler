package flights.android.com.flightslist.parser;

import flights.android.com.flightslist.network.Response;

/**
 * Created by jade on 3/8/16.
 */

public interface Parser<T> {

    public Response<T> parseResponse(String body);

}
