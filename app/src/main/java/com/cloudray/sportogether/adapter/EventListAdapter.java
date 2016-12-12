package com.cloudray.sportogether.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cloudray.sportogether.R;
import com.cloudray.sportogether.model.Event;
import com.cloudray.sportogether.view.dialog.ConfirmPaticipateDialog;

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
    private String time;

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
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        MyViewHolder myHolder = (MyViewHolder)holder;

        sportsType = data.get(position).getEvent_sporttype();
        sportsTitle = data.get(position).getEvent_title();
        sportsPeople = "Require People : " + data.get(position).getEvent_requirednum();
        sportsLocation = "Location : " + data.get(position).getEvent_location();
        sportsDescription = data.get(position).getEvent_description();
        time = data.get(position).getEvent_time();

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
        myHolder.time.setText(time);
        myHolder.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConfirmPaticipateDialog dialog = new ConfirmPaticipateDialog(context, R.style.dialog);
                ConfirmPaticipateDialog.Builder builder = dialog. new Builder(context);
//              ConfirmPaticipateDialog.Builder builder = new ConfirmPaticipateDialog(context, R.style.dialog). new Builder(context);
                dialog = builder.getDialog();
                dialog.setEvent(data.get(position));
                //dialog.printEvent();
                Log.e("dialog before create: ", dialog.toString());
                Log.e("dialog before create: ", dialog.event.toString());
                dialog = builder.create();
                //System.out.println("after create!!!:");
                //dialog.printEvent();
                Log.e("dialog after create: ", dialog.toString());
                Log.e("dialog after create: ", dialog.event.toString());
                dialog.show();
                Log.e("adapter", data.get(position).getEvent_sporttype()+"");
                Log.e("adapter", data.get(position).getEvent_creatorname()+"");
                Log.e("adapter", data.get(position).getEvent_title()+"");
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        LinearLayout container;
        ImageView sportType;
        TextView eventTitle;
        TextView requirePeople;
        TextView location;
        TextView time;

        public MyViewHolder(View itemView) {
            super(itemView);
            container = (LinearLayout)itemView.findViewById(R.id.listitem_events_container);
            sportType = (ImageView)itemView.findViewById(R.id.listitem_events_sport_type_image);
            eventTitle = (TextView)itemView.findViewById(R.id.listitem_events_title);
            requirePeople = (TextView)itemView.findViewById(R.id.listitem_events_require_people);
            location = (TextView)itemView.findViewById(R.id.listitem_events_location);
            time = (TextView)itemView.findViewById(R.id.listitem_events_time);

            //container.setOnClickListener(this);// 怎么获取当前position？还是说直接传如属性值? 放在onBindView里?
        }

//        @Override
//        public void onClick(View v) {
//            switch (v.getId()){
//                case R.id.listitem_events_container:
//                    break;
//                default:
//                    break;
//            }
//        }
    }
}
