package com.cloudray.sportogether.view.activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;

import com.cloudray.sportogether.R;
import com.cloudray.sportogether.model.Event;
import com.cloudray.sportogether.network.service.EventService;
import com.cloudray.sportogether.tools.PermissionUtils;
import com.cloudray.sportogether.view.dialog.ConfirmPaticipateDialog;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleApiClient.OnConnectionFailedListener, GoogleApiClient.ConnectionCallbacks, GoogleMap.OnMarkerClickListener, ActivityCompat.OnRequestPermissionsResultCallback {

    private GoogleMap mMap;
    private GoogleApiClient mGoogleApiClient;
    private Location mLastLocation;
    private List<Event> eventList;

//    private ArrayList<Marker> markers;

    /**
     * Flag indicating whether a requested permission has been denied after returning in
     * {@link #onRequestPermissionsResult(int, String[], int[])}.
     */
    private boolean mPermissionDenied = false;


    /**
     * Request code for location permission request.
     *
     * @see #onRequestPermissionsResult(int, String[], int[])
     */
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Create an instance of GoogleAPIClient.
        if (mGoogleApiClient == null) {
            mGoogleApiClient = new GoogleApiClient.Builder(this)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();
        }

        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    protected void onStart() {
        mGoogleApiClient.connect();
        super.onStart();
    }

    protected void onStop() {
        mGoogleApiClient.disconnect();
        super.onStop();
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        enableMyLocation();
        mMap.setOnMarkerClickListener(this);
    }

    @Override
    public void onConnected(Bundle connectionHint) {

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // Permission to access the location is missing.
            PermissionUtils.requestPermission(this, LOCATION_PERMISSION_REQUEST_CODE,
                    Manifest.permission.ACCESS_FINE_LOCATION, true);
            return;
        }

        /*
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://52.43.221.21:8081/").addConverterFactory(GsonConverterFactory.create()).build();
        EventService service = retrofit.create(EventService.class);
        Call<List<Event>> call = service.getAllEvents();
        call.enqueue(new Callback<List<Event>>() {
            @Override
            public void onResponse(Call<List<Event>> call, Response<List<Event>> response) {
                eventList = response.body();
            }

            @Override
            public void onFailure(Call<List<Event>> call, Throwable t) {

            }
        });
        */

        mLastLocation = LocationServices.FusedLocationApi.getLastLocation(
                mGoogleApiClient);
        if (mLastLocation != null) {

            /*
            for(int i = 0; i < eventList.size(); i++){
                LatLng marker0 = new LatLng(eventList.get(i).getEvent_location_x()+0.001, eventList.get(i).getEvent_location_y()+0.001);
                mMap.addMarker(new MarkerOptions().position(marker0).title(i+""));
            }
            */

            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(mLastLocation.getLatitude(),mLastLocation.getLongitude()),15));

            // Add some marker
            LatLng marker1 = new LatLng(mLastLocation.getLatitude()+0.001, mLastLocation.getLongitude()+0.001);
            mMap.addMarker(new MarkerOptions().position(marker1).title("Marker1"));

            LatLng marker2 = new LatLng(mLastLocation.getLatitude()+0.003, mLastLocation.getLongitude()+0.001);
            mMap.addMarker(new MarkerOptions().position(marker2).title("Marker2"));

            LatLng marker3 = new LatLng(mLastLocation.getLatitude()+0.001, mLastLocation.getLongitude()+0.003);
            mMap.addMarker(new MarkerOptions().position(marker3).title("Marker3"));

            LatLng marker4 = new LatLng(mLastLocation.getLatitude()+0.0010, mLastLocation.getLongitude()+0.0007);
            mMap.addMarker(new MarkerOptions().position(marker4).title("Marker4"));

            LatLng marker5 = new LatLng(mLastLocation.getLatitude()-0.001, mLastLocation.getLongitude()-0.0001);
            mMap.addMarker(new MarkerOptions().position(marker5).title("Marker5"));
        }
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (requestCode != LOCATION_PERMISSION_REQUEST_CODE) {
            return;
        }

        if (PermissionUtils.isPermissionGranted(permissions, grantResults,
                Manifest.permission.ACCESS_FINE_LOCATION)) {
            // Enable the my location layer if the permission has been granted.
            enableMyLocation();
        } else {
            // Display the missing permission error dialog when the fragments resume.
            mPermissionDenied = true;
        }
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
//        int id = Integer.parseInt(marker.getTitle());
//        ConfirmPaticipateDialog dialog = new ConfirmPaticipateDialog(this, R.style.dialog);
//        ConfirmPaticipateDialog.Builder builder = dialog. new Builder(this);
//        dialog = builder.getDialog();
//        dialog.setEvent(eventList.get(id));
//        dialog = builder.create();
//        dialog.show();

        ConfirmPaticipateDialog dialog;
        ConfirmPaticipateDialog.Builder builder = new ConfirmPaticipateDialog(this, R.style.dialog). new Builder(this);
        dialog = builder.create();
        Event event = new Event();
        event.setEvent_location_x(marker.getPosition().longitude);
        event.setEvent_location_y(marker.getPosition().latitude);
        dialog.setEvent(event);
        dialog.show();
        return false;
    }

    /**
     * Enables the My Location layer if the fine location permission has been granted.
     */
    private void enableMyLocation() {

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // Permission to access the location is missing.
            PermissionUtils.requestPermission(this, LOCATION_PERMISSION_REQUEST_CODE,
                    Manifest.permission.ACCESS_FINE_LOCATION, true);
        } else if (mMap != null) {
            // Access to the location has been granted to the app.
            mMap.setMyLocationEnabled(true);
        }
    }
}
