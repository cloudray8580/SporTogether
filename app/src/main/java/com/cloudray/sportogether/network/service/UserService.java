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
    Call<User> getUser(@Query("user_id") String userid);

    @POST("user/login")
    Call<User> login(@Query("user_id") String userid, @Query("user_pwd") String userpwd);

    @POST("user/add")
    Call<User> addUser(@Query("request") String userobjectjson);

    @POST("user/update")
    Call<User> updateUser(@Query("user_id") String userid, @Query("update_field") String field, @Query("update_value") String value);
}
