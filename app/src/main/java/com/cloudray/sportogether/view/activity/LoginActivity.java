package com.cloudray.sportogether.view.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.cloudray.sportogether.R;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    Button loginButton;
    Button registerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        findView();
        setListener();
    }

    public void findView(){
        loginButton = (Button)findViewById(R.id.activity_login_button_login);
        registerButton = (Button)findViewById(R.id.activity_login_button_register);
    }

    public void setListener(){
        loginButton.setOnClickListener(this);
        registerButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.activity_login_button_login:
                Intent intent1 = new Intent(this, HomeActivity.class);
                startActivity(intent1);
                break;
            case R.id.activity_login_button_register:
                break;
            default:
                break;
        }
    }
}
