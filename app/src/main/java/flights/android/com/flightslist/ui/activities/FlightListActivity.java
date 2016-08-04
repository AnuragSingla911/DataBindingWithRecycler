package flights.android.com.flightslist.ui.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import flights.android.com.flightslist.R;

/**
 * Created by jade on 3/8/16.
 */

public class FlightListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getIntent() != null && getIntent().hasExtra("data")) {

            android.app.FragmentManager fm = getFragmentManager();
            FlightListFragment fragment = (FlightListFragment) fm.findFragmentByTag("data");
            if (fragment == null) {
                fragment = new FlightListFragment();
                fragment.setRetainInstance(true);
                Bundle b = new Bundle();
                b.putParcelable("data", getIntent().getParcelableExtra("data"));
                fragment.setArguments(b);
                fragment.setHasOptionsMenu(false);
                android.app.FragmentTransaction ft = fm.beginTransaction();
                ft.add(R.id.layout_container, fragment, "data");
                ft.commit();
            }
        }
    }
}
