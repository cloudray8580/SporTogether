package com.cloudray.sportogether.view.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.cloudray.sportogether.R;
import com.cloudray.sportogether.adapter.EventListAdapter;
import com.cloudray.sportogether.model.Event;
import com.cloudray.sportogether.network.service.EventService;
import com.cloudray.sportogether.tools.MySharedPreference;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HistoryEventActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    EventListAdapter mAdapter;
    List<Event> events;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_event);

        /*
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.89.185.54:8000/") // need to change
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        EventService service = retrofit.create(EventService.class);
        Call<List<Event>> call = service.getHistoryEvents((String) MySharedPreference.getData(this,"userid", "")); // need to change
        call.enqueue(new Callback<List<Event>>() {
            @Override
            public void onResponse(Call<List<Event>> call, Response<List<Event>> response) {
                events = response.body();
            }

            @Override
            public void onFailure(Call<List<Event>> call, Throwable t) {
                Toast.makeText(HistoryEventActivity.this, "get history events failed", Toast.LENGTH_SHORT).show();
            }
        });
        */

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
        myEvent.setTime("19: 00");
        myEvent.setValid(true);

        List<Event> testList = new ArrayList<Event>();
        for(int i = 0; i < 10; i++)
            testList.add(myEvent);

        events = testList;
    }
}
