package com.cloudray.sportogether.view.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.cloudray.sportogether.R;
import com.cloudray.sportogether.model.User;
import com.cloudray.sportogether.network.service.UserService;
import com.cloudray.sportogether.tools.MySharedPreference;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    Button loginButton;
    Button registerButton;
    EditText usernameText, passwordText;
    Retrofit retrofit;
    CheckBox remember;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
       // initRetrofit();
        findView();
        setListener();
        init();
    }

    public void init(){
        if(remember.isChecked()){
            String username, password;
            if((username = (String)MySharedPreference.getData(this, "username", "")) != ""){
                usernameText.setText(username);
                remember.setChecked(true);
            }
        }
    }

    public void initRetrofit(){
        retrofit = new Retrofit.Builder()
                .baseUrl("") // need to change
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public void findView(){
        loginButton = (Button)findViewById(R.id.activity_login_button_login);
        registerButton = (Button)findViewById(R.id.activity_login_button_register);
        usernameText = (EditText)findViewById(R.id.activity_login_username);
        passwordText = (EditText)findViewById(R.id.activity_login_password);
        remember = (CheckBox)findViewById(R.id.activity_login_remember_account_checkbox);
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
                String base64User = MySharedPreference.base64Encode(new User(username, password));
                Log.e("my_base64_encoded: ", base64User);
                MySharedPreference.storeData(LoginActivity.this, "user",  base64User);
                Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                startActivity(intent1);
                /*
                if (checkValidate(username, password)){
                    // call login in
                    UserService service = retrofit.create(UserService.class);
                    Call<User> call = service.login(new User(username, password));
                    call.enqueue(new Callback<User>() {
                        @Override
                        public void onResponse(Call<User> call, Response<User> response) {
                            MySharedPreference.storeData(LoginActivity.this, "userid", response.body().getUserId());
                            MySharedPreference.storeData(LoginActivity.this, "username", response.body().getUserName());
                            MySharedPreference.storeData(LoginActivity.this, "user",  MySharedPreference.base64Encode(response.body()));
                            Intent intent1 = new Intent(LoginActivity.this, HomeActivity.class);
                            startActivity(intent1);
                        }

                        @Override
                        public void onFailure(Call<User> call, Throwable t) {
                            Toast.makeText(LoginActivity.this, "login fail due to network", Toast.LENGTH_SHORT).show();
                        }
                    });
                } else {
                    Toast.makeText(this, "user name or password empty", Toast.LENGTH_SHORT).show();
                }
                */
                break;
            case R.id.activity_login_button_register:
                if (checkValidate(username, password)){
                    // call register
                    UserService service = retrofit.create(UserService.class);
                    Call<User> call = service.addUser(new User(username, password));
                    call.enqueue(new Callback<User>() {
                        @Override
                        public void onResponse(Call<User> call, Response<User> response) {
                            MySharedPreference.storeData(LoginActivity.this, "userid", response.body().getUserId());
                            MySharedPreference.storeData(LoginActivity.this, "username", response.body().getUserName());
                            MySharedPreference.storeData(LoginActivity.this, "user",  MySharedPreference.base64Encode(response.body()));
                            Intent intent1 = new Intent(LoginActivity.this, HomeActivity.class);
                            startActivity(intent1);
                        }

                        @Override
                        public void onFailure(Call<User> call, Throwable t) {
                            Toast.makeText(LoginActivity.this, "register fail due to network", Toast.LENGTH_SHORT).show();
                        }
                    });
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
