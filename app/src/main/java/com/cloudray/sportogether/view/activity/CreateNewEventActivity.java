package com.cloudray.sportogether.view.activity;

import android.location.Location;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_event);
        findView();
        setListener();
        init();
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
                Location location = new MyLocationTool().getLocation(this);
                int tyep = 1;
                if(basketballRadio.isChecked())
                    tyep = 1;
                if(footballRadio.isChecked())
                    tyep = 2;
                if(runningRadio.isChecked())
                    tyep = 3;
                event.setType(tyep);
                event.setPhone(phoneText.getText().toString().trim());
                event.setEventTitle(titleText.getText().toString().trim());
                event.setUserId((String)MySharedPreference.getData(this, "userid", ""));
                event.setUserName(usernameText.getText().toString().trim());
                event.setRequiredPlayerNumber(Integer.parseInt(needText.getText().toString().trim()));
                event.setLocation(spotText.getText().toString().trim());
                event.setEventDescription(descriptionText.getText().toString().trim());
                event.setLocation_x(location.getLongitude());
                event.setLocation_y(location.getLatitude());
                event.setValid(true);

                String time;
                int hours = timePicker.getCurrentHour();
                int minutes = timePicker.getCurrentMinute();
//                int hours = timePicker.getHour();
//                int minutes = timePicker.getMinute();
                time = hours+" : "+minutes;
                event.setTime(time);

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("192.169.1.1")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                EventService service = retrofit.create(EventService.class);
                Call<Event> call = service.addEvent(event);
                call.enqueue(new Callback<Event>() {
                    @Override
                    public void onResponse(Call<Event> call, Response<Event> response) {
                        Toast.makeText(CreateNewEventActivity.this, "Submit Success !", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<Event> call, Throwable t) {
                        Toast.makeText(CreateNewEventActivity.this, "Submit Error !", Toast.LENGTH_SHORT).show();
                    }
                });
                break;
            default:
                break;
        }
    }
}
