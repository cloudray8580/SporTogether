// # CSIT 5510     # Li Zhe        20386967    zlicx@connect.ust.hk
// # CSIT 5510     # Zhang Chen    20399782    jxzcv.zhang@connect.ust.hk
// # CSIT 5510     # Zhao Shixiong 20402060    szhaoag@connect.ust.hk
package com.cloudray.sportogether.tools;

import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.List;
import java.util.jar.Manifest;

/**
 * Created by Cloud on 2016/12/6.
 */

public class MyLocationTool {

    private LocationManager locationManager;
    private Location location;
    private String provider;
    private Context context;

    public Location getLocation(){
        if (ActivityCompat.checkSelfPermission(context, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(context, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Log.e("location permission", "failed!");
            return null;
        }
        location = locationManager.getLastKnownLocation(provider);
        if (location != null){
                Log.e("location: ", "location not null");
            } else {
                // can not get current location
                Log.e("location: ", "location is null");
            }
        return location;
    }

    public void initLocation(Context context){
        this.context = context;
        locationManager = (LocationManager)context.getSystemService(Context.LOCATION_SERVICE);
        provider = judgeProvider();
        if (provider != null){
            Log.e("provider", "not null");
            Log.e("provider: ", provider);
            if (ActivityCompat.checkSelfPermission(context, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                    && ActivityCompat.checkSelfPermission(context, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                Log.e("location permission", "failed!");
                return;
            }
            locationManager.requestLocationUpdates(provider, 100, 0, new LocationListener() {
                @Override
                public void onLocationChanged(Location location) {

                }

                @Override
                public void onStatusChanged(String provider, int status, Bundle extras) {

                }

                @Override
                public void onProviderEnabled(String provider) {

                }

                @Override
                public void onProviderDisabled(String provider) {

                }
            });
            //locationManager.setTestProviderEnabled(provider, true);
        }
    }

    public String judgeProvider(){
        List<String> providerList = locationManager.getProviders(true);
         if (providerList.contains(LocationManager.NETWORK_PROVIDER)){
            return LocationManager.NETWORK_PROVIDER;
        }else if(providerList.contains(LocationManager.GPS_PROVIDER)){
            return LocationManager.GPS_PROVIDER;
        }else {
            return null;
        }
    }

}
