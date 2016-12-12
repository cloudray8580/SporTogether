package com.cloudray.sportogether.model;

/**
 * Created by Cloud on 2016/12/7.
 */

public class TestWechatMessage {
    public String errcode;
    public String errmsg;

    public TestWechatMessage(String a, String b){
        this.errcode = a;
        this.errmsg = b;
    }
}
