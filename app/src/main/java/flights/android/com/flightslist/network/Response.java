package flights.android.com.flightslist.network;

/**
 * Created by jade on 3/8/16.
 */

public class Response<T> {

    public static final int SUCCESS_RESPONSE_CODE = 1;
    public static final int ERROR_RESPONSE_CODE = 0;

    T data;

    int statusCode;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }
}
