package com.example.administrator.mymapapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class Panels extends AppCompatActivity {

    Spinner spinner;
    EditText et;
    private String userID;
    private FirebaseDatabase mFirebaseDatabase;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private DatabaseReference myRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panels);
        spinner = (Spinner) findViewById(R.id.spinner);
        et=(EditText)findViewById(R.id.cost);
        List<String> list = new ArrayList<String>();
        list.add("Crystalline Silicon");
        list.add("Monocrystalline");
        list.add("Polycrystalline");
        list.add("Thin-Film Solar Panels");
        list.add("Amorphous Silicon (a-Si)");
        list.add("Cadmium Telluride (CdTe)");
        list.add("Copper Indium Gallium Selenide (CIS/CIGS)");
        list.add("Building-Integrated Photovoltaics");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item, list);
        //dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);

        mAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        myRef = mFirebaseDatabase.getReference();
        FirebaseUser user = mAuth.getCurrentUser();
        userID = user.getUid();
    }

    public void add_click(View v)
    {
        String id= FirebaseAuth.getInstance().getCurrentUser().getUid();
        String n=spinner.getSelectedItem().toString();
        String c=et.getText().toString();
        PanelProduct p1=new PanelProduct(n,c);
        myRef.child("panels").push().setValue(p1);
       // myRef.child("panels").child("cost").push().setValue();
        Toast.makeText(this,"Added Successfully",Toast.LENGTH_SHORT).show();
et.setText("");
    }
    public void finish_click(View v)
    {
    startActivity(new Intent(this,ToDoSeller.class));
    }
}
