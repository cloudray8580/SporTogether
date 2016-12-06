package com.cloudray.sportogether.tools;

import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;

import java.util.List;
import java.util.jar.Manifest;

/**
 * Created by Cloud on 2016/12/6.
 */

public class MyLocationTool {

    private LocationManager locationManager;
    private Location location;
    private String provider;

    public void init(Context context){
        locationManager = (LocationManager)context.getSystemService(Context.LOCATION_SERVICE);
        provider = judgeProvider();
        if (provider != null){
            if (ActivityCompat.checkSelfPermission(context, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                    && ActivityCompat.checkSelfPermission(context, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
                return;
            location = locationManager.getLastKnownLocation(provider);
            if (location != null){
                location.getLongitude();
                location.getLatitude();
            } else {
                // can not get current location
            }
        }
    }

    public String judgeProvider(){
        List<String> providerList = locationManager.getProviders(true);
        if (providerList.contains(LocationManager.NETWORK_PROVIDER)){
            return LocationManager.NETWORK_PROVIDER;
        } else if(providerList.contains(LocationManager.GPS_PROVIDER)){
            return LocationManager.GPS_PROVIDER;
        } else {
            return null;
        }
    }
}
