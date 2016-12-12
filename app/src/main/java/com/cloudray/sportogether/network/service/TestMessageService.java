package com.cloudray.sportogether.network.service;

import com.cloudray.sportogether.model.TestWechatMessage;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by Cloud on 2016/12/7.
 */

public interface TestMessageService {

    @GET("poll/test")
    Call<TestWechatMessage> getToken();

    @GET("poll/test2")
    Call<TestWechatMessage> getToken(@Query("param1") String param1, @Query("param2") String param2);

    @POST("poll/test3")
    Call<TestWechatMessage> getToken1(@Body TestWechatMessage message);
}
