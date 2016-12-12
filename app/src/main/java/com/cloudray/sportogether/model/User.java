package com.cloudray.sportogether.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Cloud on 2016/11/25.
 */

public class User implements Serializable {

    private int user_id;

    private String user_name;

    private String user_pwd;

    private String user_nickname;

    private int user_gender; // 0:female 1:male 2:others

    private int user_age; // use -1 to denote an invalid user

    private String user_phone;

    private String user_selfintro;

    private int user_interest; // 1:basketball 2:football 4:run

    public User(String username, String password) {
        this.user_name = username;
        this.user_pwd = password;
        this.setUser_phone("00000000");
        this.setUser_nickname("user_"+user_name);
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_pwd() {
        return user_pwd;
    }

    public void setUser_pwd(String user_pwd) {
        this.user_pwd = user_pwd;
    }

    public String getUser_nickname() {
        return user_nickname;
    }

    public void setUser_nickname(String user_nickname) {
        this.user_nickname = user_nickname;
    }

    public int getUser_gender() {
        return user_gender;
    }

    public void setUser_gender(int user_gender) {
        this.user_gender = user_gender;
    }

    public int getUser_age() {
        return user_age;
    }

    public void setUser_age(int user_age) {
        this.user_age = user_age;
    }

    public String getUser_phone() {
        return user_phone;
    }

    public void setUser_phone(String user_phone) {
        this.user_phone = user_phone;
    }

    public String getUser_selfintro() {
        return user_selfintro;
    }

    public void setUser_selfintro(String user_selfintro) {
        this.user_selfintro = user_selfintro;
    }

    public int getUser_interest() {
        return user_interest;
    }

    public void setUser_interest(int user_interest) {
        this.user_interest = user_interest;
    }
}

