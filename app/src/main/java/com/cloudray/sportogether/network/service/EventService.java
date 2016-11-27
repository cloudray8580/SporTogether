package com.cloudray.sportogether.network.service;

import com.cloudray.sportogether.model.Event;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Cloud on 2016/11/25.
 */

public interface EventService{

    @GET("events")
    Call<List<Event>> getAllEvents();

    @GET("events/{event}")
    Call<Event> getSpecificEvent(@Path("event") String path);

    @GET("events/local")
    Call<List<Event>> getLocalEvents(@Query("locationX") double locationX, @Query("locationY") double locationY);

    @GET("events/suitable")
    Call<Event> getMostSuitableEvent(@Query("locationX") double locationX, @Query("locationY") double locationY, @Query("sportType") int sportType);

    @POST("events/add")
    Call<Event> addEvent(@Body Event event);
}
