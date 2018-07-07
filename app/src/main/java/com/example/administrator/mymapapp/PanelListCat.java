package com.example.administrator.mymapapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class PanelListCat extends AppCompatActivity {
ListView list;

    private DatabaseReference mFirebaseDatabase;
    private FirebaseDatabase mFirebaseInstance;
    ArrayList<PanelProduct> panellist;


    PanelAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cat_panel_list);

    init();
}

    private void init() {
        list=(ListView)findViewById(R.id.list);


        readFromFire();




    }
    private void readFromFire() {
        mFirebaseInstance = FirebaseDatabase.getInstance();
        panellist=new ArrayList<>();
        // get reference to 'users' node
        mFirebaseDatabase = mFirebaseInstance.getReference("panels");

        mFirebaseDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
               panellist.clear();
                for (DataSnapshot ch : dataSnapshot.getChildren()) {
                   // Toast.makeText(getApplicationContext(),"in ondata ch : "+ch,Toast.LENGTH_LONG).show();
                    //  // Log.e("" +
                    //    "Keyfrom Firebase",""+ ch.getKey()); //displays the key for the node

                    PanelProduct p1=ch.getValue(PanelProduct.class);
                   // Toast.makeText(getApplicationContext(),"Data: "+p1.getName()+"Cost : "+p1.getCost(),Toast.LENGTH_LONG).show();
                    panellist.add( p1);

                }

                adapter=new PanelAdapter(getApplicationContext(),panellist);
                list.setAdapter(adapter);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }


}


