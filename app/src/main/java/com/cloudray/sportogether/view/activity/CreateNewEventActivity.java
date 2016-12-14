// # CSIT 5510     # Li Zhe        20386967    zlicx@connect.ust.hk
// # CSIT 5510     # Zhang Chen    20399782    jxzcv.zhang@connect.ust.hk
// # CSIT 5510     # Zhao Shixiong 20402060    szhaoag@connect.ust.hk
package com.cloudray.sportogether.view.activity;

import android.location.Location;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.cloudray.sportogether.R;
import com.cloudray.sportogether.model.Event;
import com.cloudray.sportogether.network.service.EventService;
import com.cloudray.sportogether.tools.MyLocationTool;
import com.cloudray.sportogether.tools.MySharedPreference;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.sql.Time;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CreateNewEventActivity extends AppCompatActivity implements View.OnClickListener{

    TextView usernameText, phoneText;
    TimePicker timePicker;
    MaterialEditText titleText, needText, spotText;
    RadioButton basketballRadio, footballRadio, runningRadio;
    EditText descriptionText;
    Button submitButton;
    MyLocationTool myLocationTool;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_event);
        findView();
        setListener();
        init();
        myLocationTool = new MyLocationTool();
        myLocationTool.initLocation(this);
    }

    public void findView(){
        usernameText = (TextView)findViewById(R.id.activity_create_new_event_username);
        phoneText = (TextView)findViewById(R.id.activity_create_new_event_phone);

        timePicker = (TimePicker) findViewById(R.id.activity_create_new_event_time);

        titleText = (MaterialEditText)findViewById(R.id.activity_create_new_event_title);
        needText = (MaterialEditText)findViewById(R.id.activity_create_new_event_required_people_number);
        spotText = (MaterialEditText)findViewById(R.id.activity_create_new_event_location);

        basketballRadio = (RadioButton)findViewById(R.id.activity_create_new_event_radiobutton1);
        footballRadio = (RadioButton)findViewById(R.id.activity_create_new_event_radiobutton2);
        runningRadio = (RadioButton)findViewById(R.id.activity_create_new_event_radiobutton3);

        descriptionText = (EditText)findViewById(R.id.activity_create_new_event_description);
        submitButton = (Button)findViewById(R.id.activity_create_new_event_submit_button);
    }

    public void setListener(){
        submitButton.setOnClickListener(this);
    }

    public void init(){
        usernameText.setText((String)MySharedPreference.getData(this, "username", ""));
        phoneText.setText((String)MySharedPreference.getData(this, "userphone", ""));
        timePicker.setIs24HourView(true);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.activity_create_new_event_submit_button:
                Event event = new Event();
                Location location = myLocationTool.getLocation();
                int tyep = 1;
                if(basketballRadio.isChecked())
                    tyep = 1;
                if(footballRadio.isChecked())
                    tyep = 2;
                if(runningRadio.isChecked())
                    tyep = 3;
                event.setEvent_sporttype(tyep);
                event.setEvent_creatorphone(phoneText.getText().toString().trim());
                event.setEvent_title(titleText.getText().toString().trim());
                event.setEvent_creatorname(usernameText.getText().toString().trim());
                event.setEvent_requirednum(Integer.parseInt(needText.getText().toString().trim()));
                event.setEvent_location(spotText.getText().toString().trim());
                event.setEvent_description(descriptionText.getText().toString().trim());
                Log.e("create new event", event.getEvent_title()+"");
                Log.e("create new event", event.getEvent_creatorname()+"");
                if (location == null){
                    event.setEvent_location_x(114.264390);
                    event.setEvent_location_y(22.33812);
                } else {
                    event.setEvent_location_x(location.getLongitude());
                    event.setEvent_location_y(location.getLatitude());
                }
                event.setEvent_isValid(1);

                String time;
                int hours = timePicker.getCurrentHour();
                int minutes = timePicker.getCurrentMinute();
//                int hours = timePicker.getHour();
//                int minutes = timePicker.getMinute();
                time = hours+" : "+minutes;
                Log.e("time：", time);
                event.setEvent_time(time);

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("http://52.43.221.21:8081/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                EventService service = retrofit.create(EventService.class);
                Call<Event> call = service.addEvent(event);
                call.enqueue(new Callback<Event>() {
                    @Override
                    public void onResponse(Call<Event> call, Response<Event> response) {
                        Toast.makeText(CreateNewEventActivity.this, "Submit Success !", Toast.LENGTH_SHORT).show();
                        Log.e("create event", "create success!");
                        Log.e("create event", response.body()+"");
                    }

                    @Override
                    public void onFailure(Call<Event> call, Throwable t) {
                        Toast.makeText(CreateNewEventActivity.this, "Submit Error !", Toast.LENGTH_SHORT).show();
                        Log.e("create event", "create fail");
                        Log.e("create event", t.toString());
                    }
                });
                break;
            default:
                break;
        }
    }
}
