package com.example.administrator.mymapapp;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

class MapViewController
{
    GoogleMap googleMap;
    private GPSTracker gps;
    private Context mAct;

     MapViewController(Context mAct){
        this.mAct=mAct;
    }

     void onMapReady(GoogleMap map) {
        googleMap = map;
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(21.1766, 79.0614), 11.0f));
        UiSettings controls = googleMap.getUiSettings();
        controls.setZoomControlsEnabled(true);
        controls.setCompassEnabled(true);
        controls.setMapToolbarEnabled(false);
        controls.setMyLocationButtonEnabled(true);
        if (ActivityCompat.checkSelfPermission(mAct, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(mAct, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
            return;

        googleMap.setMyLocationEnabled(true);
        googleMap.setOnMyLocationButtonClickListener(new GoogleMap.OnMyLocationButtonClickListener() {
            @Override
            public boolean onMyLocationButtonClick() {
                gps = new GPSTracker(mAct);
                if (gps.canGetLocation()) {
                    if(gps.getLatitude()!=0.0 && gps.getLongitude()!=0.0) {
                        setMarker(googleMap, gps.getLatitude(), gps.getLongitude());
                    }
                    return true;
                }
                else {
                    gps.showSettingsAlert();
                    return false;
                }
            }
        });

        googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng pt) {

                setMarker(googleMap,pt.latitude,pt.longitude);
                //googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
            }
        });
    }

    void setMarker(GoogleMap map,double lat,double lng)
    {
        MarkerOptions options = new MarkerOptions();
        map.clear();

        LatLng point=new LatLng(lat,lng);
        options.position(point);

        map.addMarker(options.position(point));
        //map.moveCamera(CameraUpdateFactory.newLatLngZoom(point, 19.0f));
        map.animateCamera(CameraUpdateFactory.newLatLngZoom(point, 19.0f));

        ((LocationActivity)mAct).setLatitudeLongitude(lat,lng);
    }
}
