package com.example.administrator.mymapapp;

import android.app.AlertDialog;
import android.app.Service;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.Settings;

public class GPSTracker extends Service implements LocationListener {

    private final Context mContext;

    boolean isGPSEnabled = false,isNetworkEnabled = false,canGetLocation = false;

    Location location;

    double latitude,longitude;

    protected LocationManager locationManager;

    public GPSTracker(Context context) {
        this.mContext = context;
        getLocation();
    }

    public Location getLocation() throws SecurityException {
        try {
            locationManager = (LocationManager) mContext.getSystemService(LOCATION_SERVICE);

            isGPSEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
            isNetworkEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);

            if (isGPSEnabled || isNetworkEnabled)  {
                this.canGetLocation = true;

                if (isNetworkEnabled)
                    locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,60000,10, this);
                if (locationManager != null)
                    location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

                if (isGPSEnabled)
                    if (location == null) {
                        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,60000,10, this);
                        if (locationManager != null)
                            location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                    }

                if (location != null) {
                    latitude = location.getLatitude();
                    longitude = location.getLongitude();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return location;
    }

    /*
    public void stopUsingGPS(){
        if(locationManager != null){
            locationManager.removeUpdates(GPSTracker.this);
        }
    }*/

    public double getLatitude(){
        if(location != null)
            latitude = location.getLatitude();
        return latitude;
    }

    public double getLongitude(){
        if(location != null)
            longitude = location.getLongitude();
        return longitude;
    }

    public boolean canGetLocation() {
        return this.canGetLocation;
    }

    public void showSettingsAlert(){
        new AlertDialog.Builder(mContext)
                .setTitle("GPS Disabled")
                .setMessage("Do you want to enable GPS?")
                .setPositiveButton("Settings", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        mContext.startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                    }})
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }})
                .show();
    }

    @Override
    public void onLocationChanged(Location location) {
    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    @Override
    public void onProviderEnabled(String provider) {
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
    }

    @Override
    public IBinder onBind(Intent arg0) {
        return null;
    }

}