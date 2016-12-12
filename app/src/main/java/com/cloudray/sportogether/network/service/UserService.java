package com.cloudray.sportogether.network.service;

import com.cloudray.sportogether.model.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

/**
 * Created by Cloud on 2016/11/25.
 */

public interface UserService {

    @GET("user/get")
    Call<User> getUser(@Query("userid") String userid);

    @GET("user/login")
    Call<User> login(@Query("user_id") String user_name, @Query("user_pwd") String user_pwd);

    @POST("user/add")
    Call<User> addUser(@Body User user);

//    @POST("poll/test3")
//    Call<User> addUser(@Body User user);

    @POST("user/update")
    Call<User> updateUser(@Body User user);
}
