package flights.android.com.flightslist.network;

import java.util.HashMap;

import flights.android.com.flightslist.parser.Parser;

/**
 * Created by jade on 3/8/16.
 */

public class Request<T> {

    private String url;

    private HashMap<String,String> headers;

    private Parser<T> mParser;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public HashMap<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(HashMap<String, String> headers) {
        this.headers = headers;
    }

    public Parser getmParser() {
        return mParser;
    }

    public void setmParser(Parser mParser) {
        this.mParser = mParser;
    }
}
