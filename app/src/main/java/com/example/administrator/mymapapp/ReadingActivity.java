package com.example.administrator.mymapapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ReadingActivity extends AppCompatActivity {
    // Get a reference to the database service
   // DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("users");
    private DatabaseReference mFirebaseDatabase;
    private FirebaseDatabase mFirebaseInstance;
    ArrayList<Seller> list=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reading);
        read();
    }

    private void read() {
        mFirebaseInstance = FirebaseDatabase.getInstance();

        // get reference to 'users' node
        mFirebaseDatabase = mFirebaseInstance.getReference("sellers");

        mFirebaseDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot ch : dataSnapshot.getChildren()) {
                   // Log.e("" +
                        //    "Keyfrom Firebase",""+ ch.getKey()); //displays the key for the node
                   //  Toast.makeText(getApplicationContext(),"Data: "+ childDataSnapshot.child("long").getValue(),Toast.LENGTH_LONG).show();
list.add( new Seller(ch.child("name").getValue().toString(),ch.child("business_name").getValue().toString(),ch.child("city").getValue().toString(),ch.child("cities_of_operation").getValue().toString(),ch.child("number").getValue().toString(),ch.child("address").getValue().toString(),ch.child("email").getValue().toString()));

                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}
