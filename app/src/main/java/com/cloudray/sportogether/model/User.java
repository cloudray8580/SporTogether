package com.cloudray.sportogether.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Cloud on 2016/11/25.
 */

public class User {

    @SerializedName("user_id")
    private String userId;

    private String userName;

    private String password;

    private String nickname;

    private int gender; // 0:female 1:male 2:others

    private int age;

    private String phone;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    private List<Integer> interestedSports;
    
    public List<Integer> getInterestedSprots() {
        return interestedSports;
    }

    public void setInterestedSprots(List<Integer> interestedSprots) {
        this.interestedSports = interestedSprots;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
