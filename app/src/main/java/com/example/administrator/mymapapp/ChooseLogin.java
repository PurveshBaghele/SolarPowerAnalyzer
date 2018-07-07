package com.example.administrator.mymapapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ChooseLogin extends AppCompatActivity implements View.OnClickListener {
TextView tvu,tva;
    ImageView imgu,imga;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose_login);
        getSupportActionBar().hide();
        initView();
    }

    private void initView() {
        tva= (TextView) findViewById(R.id.txtadmin);
        tvu= (TextView) findViewById(R.id.txtusr);
        imga= (ImageView) findViewById(R.id.imgadmin);
        imgu= (ImageView) findViewById(R.id.imgusr);
        tva.setOnClickListener(this);
        tvu.setOnClickListener(this);
        imga.setOnClickListener(this);
        imgu.setOnClickListener(this);



    }

    @Override
    public void onClick(View v) {
        Intent i1;
switch(v.getId())
{
    case R.id.txtadmin:
       i1=new Intent(this,SellerLoginActivity.class);
        startActivity(i1);
        break;

    case R.id.txtusr:
        i1=new Intent(this,LoginActivity.class);
        startActivity(i1);
        break;

    case R.id.imgadmin:
        i1=new Intent(this,SellerLoginActivity.class);
        startActivity(i1);
        break;

    case R.id.imgusr:
        i1=new Intent(this,LoginActivity.class);
        startActivity(i1);
        break;

}





    }


}
