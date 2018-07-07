package com.example.administrator.mymapapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LocationActivity extends AppCompatActivity implements OnMapReadyCallback,View.OnFocusChangeListener{

    Button next;
    MapViewController mvc;
    EditText latt,longg;
    DatabaseReference dbr;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        mvc = new MapViewController(this);

        dbr= FirebaseDatabase.getInstance().getReference();

        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        assignIDS();
    }

    private void assignIDS() {
        next = (Button) findViewById(R.id.calculate);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validateLatLng()){

                    String id= FirebaseAuth.getInstance().getCurrentUser().getUid();
                    writ(id,latt.getText().toString(),longg.getText().toString());

                    Intent intent = new Intent(getApplicationContext(),InputActivity.class);
                    Bundle b=new Bundle();
                    b.putString("LAT",latt.getText().toString());
                    b.putString("LONG",longg.getText().toString());
                    intent.putExtras(b);
                    startActivity(intent);
                    //finish();
                }
                else
                    Toast.makeText(getApplicationContext(),"Invalid Latitude or Longitude",Toast.LENGTH_LONG).show();
            }
        });
        latt = (EditText) findViewById(R.id.latt);
        latt.setOnFocusChangeListener(this);
        longg = (EditText) findViewById(R.id.longg);
        longg.setOnFocusChangeListener(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mvc.onMapReady(googleMap);
    }

    public void setLatitudeLongitude(double lat,double lng){
        latt.setText(String.valueOf(lat));
        longg.setText(String.valueOf(lng));
    }

    @Override
    public void onFocusChange(View view, boolean b) {
        if(validateLatLng()) {
            mvc.setMarker(mvc.googleMap, Double.parseDouble(latt.getText().toString()), Double.parseDouble(longg.getText().toString()));
            return;
        }
        if( !latt.getText().toString().isEmpty() && !longg.getText().toString().isEmpty()) {
            if (view.getId() == latt.getId()) {
                Toast.makeText(LocationActivity.this, "Invalid Latitude", Toast.LENGTH_SHORT).show();
                latt.setText("");
            }
            if (view.getId() == longg.getId()){
                Toast.makeText(LocationActivity.this, "Invalid Longitude", Toast.LENGTH_SHORT).show();
                longg.setText("");
            }
        }
    }

    boolean validateLatLng(){
        double la,lo;
        String laa,loo;

        laa=latt.getText().toString();
        loo=longg.getText().toString();

        if(!laa.isEmpty() && !loo.isEmpty()) {
            la = Double.parseDouble(laa);
            lo = Double.parseDouble(loo);

            if (la >= -85.0 && la <= 85.0 && lo >= -180 && lo <= 180)
                return true;
        }
        return false;
    }

    public void writ(String id,String l1,String l2){
        Map m1=new Map(Double.parseDouble(l1),Double.parseDouble(l2));
        dbr.child("users").child(id).setValue(m1);
    }
}