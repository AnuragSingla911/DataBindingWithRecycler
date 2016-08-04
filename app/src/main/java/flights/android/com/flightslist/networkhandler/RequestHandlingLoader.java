package flights.android.com.flightslist.networkhandler;

import android.content.AsyncTaskLoader;
import android.content.Context;

import flights.android.com.flightslist.modal.FlightListData;
import flights.android.com.flightslist.parser.Parser;
import flights.android.com.flightslist.network.Response;

/**
 * Created by jade on 3/8/16.
 */

public class RequestHandlingLoader extends AsyncTaskLoader<FlightListData> {

    private FlightListData mData;
    private String mUrl;
    private Parser mPArser;

    public RequestHandlingLoader(Context ctx,String url,Parser parser) {
        super(ctx);
        mUrl = url;
        mPArser = parser;
    }

    @Override
    public FlightListData loadInBackground() {
        Response<FlightListData> response = new RequestBuilder<FlightListData>().loadUrl(mUrl).parser(mPArser).executeRequestInSameThread();
        return response.getData();
    }

    @Override
    public void deliverResult(FlightListData data) {
        if (isReset()) {
            return;
        }

        mData = data;

        if (isStarted()) {
            super.deliverResult(data);
        }

    }

    @Override
    protected void onStartLoading() {
        if (mData != null) {
            deliverResult(mData);
        }

        if (takeContentChanged() || mData == null) {
            forceLoad();
        }
    }

    @Override
    protected void onStopLoading() {
        cancelLoad();
    }

    @Override
    protected void onReset() {
        onStopLoading();

        if (mData != null) {
            mData = null;
        }

    }

    @Override
    public void onCanceled(FlightListData data) {
        super.onCanceled(data);
    }

}


