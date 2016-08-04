package flights.android.com.flightslist.ui.activities;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.provider.ContactsContract;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import flights.android.com.flightslist.BR;
import flights.android.com.flightslist.R;
import flights.android.com.flightslist.databinding.ListItemBinding;
import flights.android.com.flightslist.modal.Flight;
import flights.android.com.flightslist.modal.FlightListData;

/**
 * Created by jade on 3/8/16.
 */

public class FlightListAdapter extends RecyclerView.Adapter<FlightListAdapter.ViewHolder> {

    private Context mContext;
    private FlightListData mData;

    public FlightListAdapter(Context context, FlightListData data) {
        mContext = context;
        mData = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.list_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Flight flight = mData.getMflightList().get(position);

        holder.getListItemBinding().setVariable(BR.info , flight);
        holder.getListItemBinding().executePendingBindings();

    }

    @Override
    public int getItemCount() {
        return mData.getMflightList().size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private ListItemBinding listItemBinding;


        public ViewHolder(View itemView) {
            super(itemView);

            listItemBinding = DataBindingUtil.bind(itemView);

        }

        public ListItemBinding getListItemBinding(){
            return listItemBinding;
        }
    }
}
