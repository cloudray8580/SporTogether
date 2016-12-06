package com.cloudray.sportogether.view.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.cloudray.sportogether.R;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    Button loginButton;
    Button registerButton;
    EditText usernameText, passwordText;

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
        usernameText = (EditText)findViewById(R.id.activity_login_username);
        passwordText = (EditText)findViewById(R.id.activity_login_password);
    }

    public void setListener(){
        loginButton.setOnClickListener(this);
        registerButton.setOnClickListener(this);
    }

    public boolean checkValidate(String username, String password){
        if (username == null || username == "" || password == null || password == "")
            return false;
        return true;
    }

    @Override
    public void onClick(View v) {

        String username = usernameText.getText().toString().trim();
        String password = passwordText.getText().toString().trim();
        Intent intent1 = new Intent(this, HomeActivity.class);

        switch(v.getId()){
            case R.id.activity_login_button_login:
                if (checkValidate(username, password)){
                    // call login in
                    //Intent intent1 = new Intent(this, HomeActivity.class);
                    //startActivity(intent1);
                } else {
                    Toast.makeText(this, "user name or password empty", Toast.LENGTH_SHORT).show();
                }
                startActivity(intent1);
                break;
            case R.id.activity_login_button_register:
                if (checkValidate(username, password)){
                    // call register
                    //Intent intent1 = new Intent(this, HomeActivity.class);
                    //startActivity(intent1);
                } else {
                    Toast.makeText(this, "user name or password empty", Toast.LENGTH_SHORT).show();
                }
                startActivity(intent1);
                break;
            default:
                break;
        }
    }
}
