package flights.android.com.flightslist.ui.activities;

import android.app.LoaderManager;
import android.content.Intent;
import android.content.Loader;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import flights.android.com.flightslist.R;
import flights.android.com.flightslist.modal.FlightListData;
import flights.android.com.flightslist.networkhandler.RequestHandlingLoader;
import flights.android.com.flightslist.parser.FlightListParser;

public class SplashActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<FlightListData> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_splash);
        getLoaderManager().initLoader(10, null, this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_splash, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public Loader onCreateLoader(int id, Bundle args) {
        String url = "http://blog.ixigo.com/sampleflightdata.json";
        RequestHandlingLoader loader = new RequestHandlingLoader(this, url, new FlightListParser());
        return loader;
    }

    @Override
    public void onLoadFinished(android.content.Loader<FlightListData> loader, FlightListData data) {

        Intent intent = new Intent(this, FlightListActivity.class);
        intent.putExtra("data", data);
        startActivity(intent);
        finish();


    }

    @Override
    public void onLoaderReset(android.content.Loader<FlightListData> loader) {

    }

}
