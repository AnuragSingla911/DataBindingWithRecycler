package flights.android.com.flightslist.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by jade on 3/8/16.
 */
public class NetworkProcesser<T> {

    private Request<T> request;

    public NetworkProcesser(Request request) {
        this.request = request;

    }

    public Response<T> executeRequestInSameThread() {
        {
            InputStream inputStream = null;
            String httpData = "";
            HttpURLConnection urlConnection = null;
            try {
                URL url = new URL(request.getUrl());
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.connect();
                inputStream = urlConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuffer stringBuffer = new StringBuffer();
                String line = "";
                while ((line = bufferedReader.readLine()) != null) {
                    stringBuffer.append(line);
                }
                httpData = stringBuffer.toString();

                Response<T> respone = request.getmParser().parseResponse(httpData);

                bufferedReader.close();
                return respone;
            } catch (MalformedURLException e) {
                e.printStackTrace();
                return null;
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            } finally {
                try {
                    if (inputStream != null)
                        inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                urlConnection.disconnect();
            }
        }
    }

}
