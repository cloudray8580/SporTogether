package com.cloudray.sportogether.view.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.cloudray.sportogether.R;
import com.cloudray.sportogether.adapter.EventListAdapter;
import com.cloudray.sportogether.model.Event;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HistoryEventActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    EventListAdapter mAdapter;
    List<Event> events;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_event);
        getData();
        recyclerView = (RecyclerView)findViewById(R.id.activity_history_event_recycle_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new EventListAdapter(this, events);
        recyclerView.setAdapter(mAdapter);
    }

    public void getData(){
        Event myEvent = new Event();
        myEvent.setCurrentPlayerNumber(2);
        myEvent.setRequiredPlayerNumber(5);
        myEvent.setType(1);
        myEvent.setEventTitle("Let's play basketball!");
        myEvent.setLocation("School gym in LG1");
        myEvent.setEventDescription("welcome everyone here");
        myEvent.setTime(new Date());
        myEvent.setValid(true);

        List<Event> testList = new ArrayList<Event>();
        for(int i = 0; i < 10; i++)
            testList.add(myEvent);

        events = testList;
    }
}
