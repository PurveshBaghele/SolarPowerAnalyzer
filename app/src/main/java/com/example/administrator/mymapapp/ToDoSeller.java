package com.example.administrator.mymapapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ToDoSeller extends AppCompatActivity {
Button btnProfile,btnaddPanels,btnSellers;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.to_do_seller);
        initViews();
        initListeners();
    }

    private void initListeners() {
        btnProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Profile.class);
                startActivity(intent);
            }
        });
        btnaddPanels.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Panels.class);
                startActivity(intent);
            }
        });
        btnSellers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void initViews() {
        btnProfile=(Button)findViewById(R.id.updateProfile);
        btnaddPanels=(Button)findViewById(R.id.addPanles);
        btnSellers=(Button)findViewById(R.id.otherSeller);

    }
}
