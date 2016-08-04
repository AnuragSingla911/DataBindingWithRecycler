package flights.android.com.flightslist.ui.activities;

import android.app.Fragment;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import flights.android.com.flightslist.R;
import flights.android.com.flightslist.modal.FlightListData;

/**
 * Created by jade on 3/8/16.
 */

public class FlightListFragment extends Fragment {


    private RecyclerView mRecyclerView;
    private FlightListData data;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("anurag"," oncreate");
        Bundle b = getArguments();
        if(b != null && b.containsKey("data")){
            data = b.getParcelable("data");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_view,container,false);
        mRecyclerView = (RecyclerView)view.findViewById(R.id.list);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        FlightListAdapter adapter = new FlightListAdapter(getActivity(), data);
        mRecyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {

            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                outRect.bottom = 10;
            }

            @Override
            public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
                super.onDrawOver(c, parent, state);
            }
        });
        mRecyclerView.setAdapter(adapter);
        return view;
    }
}
