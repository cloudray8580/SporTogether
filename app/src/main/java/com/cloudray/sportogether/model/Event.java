package com.cloudray.sportogether.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by Cloud on 2016/11/25.
 */

public class Event implements Serializable {

    private int event_id;

    private String event_title;

    private String event_location;

    private double event_location_x;

    private double event_location_y;

    private String event_description;

    private int event_sporttype; // 1:basketball 2:football 3:run

    private int event_requirednum;

    private int event_currentnum;

    private String event_time;

    private String event_creatorname; // creator

    private String event_creatorphone; // creator

    private int event_isValid;

    public int getEvent_isValid() {
        return event_isValid;
    }

    public void setEvent_isValid(int event_isValid) {
        this.event_isValid = event_isValid;
    }

    public String getEvent_creatorphone() {
        return event_creatorphone;
    }

    public void setEvent_creatorphone(String event_creatorphone) {
        this.event_creatorphone = event_creatorphone;
    }

    public String getEvent_creatorname() {
        return event_creatorname;
    }

    public void setEvent_creatorname(String event_creatorname) {
        this.event_creatorname = event_creatorname;
    }

    public String getEvent_time() {
        return event_time;
    }

    public void setEvent_time(String event_time) {
        this.event_time = event_time;
    }

    public int getEvent_currentnum() {
        return event_currentnum;
    }

    public void setEvent_currentnum(int event_currentnum) {
        this.event_currentnum = event_currentnum;
    }

    public int getEvent_requirednum() {
        return event_requirednum;
    }

    public void setEvent_requirednum(int event_requirednum) {
        this.event_requirednum = event_requirednum;
    }

    public int getEvent_sporttype() {
        return event_sporttype;
    }

    public void setEvent_sporttype(int event_sporttype) {
        this.event_sporttype = event_sporttype;
    }

    public String getEvent_description() {
        return event_description;
    }

    public void setEvent_description(String event_description) {
        this.event_description = event_description;
    }

    public double getEvent_location_y() {
        return event_location_y;
    }

    public void setEvent_location_y(double event_location_y) {
        this.event_location_y = event_location_y;
    }

    public double getEvent_location_x() {
        return event_location_x;
    }

    public void setEvent_location_x(double event_location_x) {
        this.event_location_x = event_location_x;
    }

    public String getEvent_location() {
        return event_location;
    }

    public void setEvent_location(String event_location) {
        this.event_location = event_location;
    }

    public String getEvent_title() {
        return event_title;
    }

    public void setEvent_title(String event_title) {
        this.event_title = event_title;
    }

    public int getEvent_id() {
        return event_id;
    }

    public void setEvent_id(int event_id) {
        this.event_id = event_id;
    }

}
