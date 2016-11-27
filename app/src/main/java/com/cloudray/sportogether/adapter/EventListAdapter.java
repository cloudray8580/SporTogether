package com.cloudray.sportogether.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cloudray.sportogether.R;
import com.cloudray.sportogether.model.Event;

import java.util.Date;
import java.util.List;

/**
 * Created by Cloud on 2016/11/26.
 */

public class EventListAdapter extends RecyclerView.Adapter {

    private Context context;
    private List<Event> data;

    private int sportsType;
    private String sportsTitle;
    private String sportsDescription;
    private String sportsPeople;
    private String sportsLocation;
    private Date time;

    public EventListAdapter(Context context, List<Event> data){
        this.context = context;
        this.data = data;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.listitem_events, parent, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyViewHolder myHolder = (MyViewHolder)holder;

        sportsType = data.get(position).getType();
        sportsTitle = data.get(position).getEventTitle();
        sportsPeople = "Require People : " + data.get(position).getRequiredPlayerNumber();
        sportsLocation = "Location : " + data.get(position).getLocation();
        sportsDescription = data.get(position).getEventDescription();
        time = data.get(position).getTime();

        switch(sportsType){
            case 1:
                myHolder.sportType.setImageResource(R.mipmap.basketball);
                break;
            case 2:
                myHolder.sportType.setImageResource(R.mipmap.football);
                break;
            case 3:
                myHolder.sportType.setImageResource(R.mipmap.runner);
                break;
            default:
                break;
        }
        myHolder.eventTitle.setText(sportsTitle);
        myHolder.requirePeople.setText(sportsPeople);
        myHolder.location.setText(sportsLocation);
        myHolder.time.setText(time.toString());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView sportType;
        TextView eventTitle;
        TextView requirePeople;
        TextView location;
        TextView time;

        public MyViewHolder(View itemView) {
            super(itemView);
            sportType = (ImageView)itemView.findViewById(R.id.listitem_events_sport_type_image);
            eventTitle = (TextView)itemView.findViewById(R.id.listitem_events_title);
            requirePeople = (TextView)itemView.findViewById(R.id.listitem_events_require_people);
            location = (TextView)itemView.findViewById(R.id.listitem_events_location);
            time = (TextView)itemView.findViewById(R.id.listitem_events_time);
        }
    }
}
