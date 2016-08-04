package flights.android.com.flightslist.networkhandler;

import java.util.HashMap;

import flights.android.com.flightslist.network.NetworkProcesser;
import flights.android.com.flightslist.parser.Parser;
import flights.android.com.flightslist.network.Request;
import flights.android.com.flightslist.network.Response;

/**
 * Created by jade on 3/8/16.
 */

public class RequestBuilder<T> {

    Request<T> request;

    public RequestBuilder(){
        request = new Request();
    }

    public RequestBuilder loadUrl(String url){
        this.request.setUrl(url);
        return this;
    }

    public RequestBuilder loadHeaders(HashMap<String,String> headers){
        this.request.setHeaders(headers);
        return this;
    }

    public RequestBuilder parser(Parser parser){
        this.request.setmParser(parser);
        return this;
    }

    public Response<T> executeRequestInSameThread(){
        NetworkProcesser processer = new NetworkProcesser(request);
        return processer.executeRequestInSameThread();
    }
}
