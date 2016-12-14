// # CSIT 5510     # Li Zhe        20386967    zlicx@connect.ust.hk
// # CSIT 5510     # Zhang Chen    20399782    jxzcv.zhang@connect.ust.hk
// # CSIT 5510     # Zhao Shixiong 20402060    szhaoag@connect.ust.hk
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
