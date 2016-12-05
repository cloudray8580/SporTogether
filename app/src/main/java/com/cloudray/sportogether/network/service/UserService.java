package com.cloudray.sportogether.network.service;

import com.cloudray.sportogether.model.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

/**
 * Created by Cloud on 2016/11/25.
 */

public interface UserService {

    @POST("user/login")
    Call<User> login(@Body User user);

    @POST("user/add")
    Call<User> addUser(@Body User user);

    @PUT("user/update")
    Call<User> updateUser(@Body User user);
}