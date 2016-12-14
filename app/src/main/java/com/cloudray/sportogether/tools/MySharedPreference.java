// # CSIT 5510     # Li Zhe        20386967    zlicx@connect.ust.hk
// # CSIT 5510     # Zhang Chen    20399782    jxzcv.zhang@connect.ust.hk
// # CSIT 5510     # Zhao Shixiong 20402060    szhaoag@connect.ust.hk
package com.cloudray.sportogether.tools;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.cloudray.sportogether.model.User;

import org.apache.commons.codec.binary.Base64;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Created by Cloud on 2016/10/10.
 *
 * need to invoke when login
 * to store both userinfo and user type as different permission
 *
 * user     type:String base64 encode object storage
 * username type:String; default:""
 * userid   type:String; default:""
 * userphone  type:String; default:""
 * sportstype type:Integer; default:-1; value:0, 1, 2
 * userage   type:Integer default -1
 *
 * chooseStudentName    type:String; default:"xxx", "none"
 * chooseStudentId      type:String; default:""
 */
public class MySharedPreference {

    private static final String FILE_NAME = "shared1";

    public static void storeData(Context context,String key, Object object) {
        String type = object.getClass().getSimpleName();
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        if(type.equals("String")){
            editor.putString(key, (String) object);
        } else if (type.equals("Integer")) {
            editor.putInt(key, (Integer) object);
        } else if (type.equals("Boolean")) {
            editor.putBoolean(key, (Boolean)object);
        } else if (type.equals("Float")) {
            editor.putFloat(key, (Float)object);
        } else if (type.equals("Long")) {
            editor.putLong(key, (Long)object);
        }
        editor.commit();
    }

    public static Object getData(Context context, String key, Object defaultValue) {
        String type = defaultValue.getClass().getSimpleName();
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        if(type.equals("String")){
            return sp.getString(key, (String)defaultValue);
        } else if (type.equals("Integer")) {
            return sp.getInt(key, (Integer)defaultValue);
        } else if (type.equals("Boolean")) {
            return sp.getBoolean(key, (Boolean)defaultValue);
        } else if (type.equals("Float")) {
            return sp.getFloat(key, (Float)defaultValue);
        } else if (type.equals("Long")) {
            return sp.getLong(key, (Long)defaultValue);
        }
        return null;
    }

    public static String base64Encode(User user){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        String base64User = null;
        try {
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(user);
            base64User = new String(Base64.encodeBase64(baos.toByteArray()));
        } catch(IOException e){
            e.printStackTrace();
            Log.e("base64", "encoded failed!");
        }
        return  base64User;
    }

    public static User base64Decode(String base64User){
        User user = null;
        byte[] base64 = Base64.decodeBase64(base64User.getBytes());
        ByteArrayInputStream bis = new ByteArrayInputStream(base64);
        try{
            ObjectInputStream ois = new ObjectInputStream(bis);
            try {
                user = (User) ois.readObject();
            } catch (ClassNotFoundException e){
                e.printStackTrace();
            }
        } catch (IOException e){
            e.printStackTrace();
            Log.e("base64", "decoded failed!");
        }
        return user;
    }

}
