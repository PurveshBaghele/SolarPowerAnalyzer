package com.example.administrator.mymapapp;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;
public class SplashActivity extends AppCompatActivity {

     TextView logotext;
        ImageView logoimage;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_splash);
            getSupportActionBar().hide();
            logotext=(TextView)findViewById(R.id.textlogo);
            logotext.setSelected(true);
            logoimage=(ImageView)findViewById(R.id.logoimg);
            Animation myanim= AnimationUtils.loadAnimation(this,R.anim.logo);
            //RotateAnimation rotateImage=new RotateAnimation()
            RotateAnimation rotateImage = new RotateAnimation(30, 360, Animation.RELATIVE_TO_SELF, 0.5f,  Animation.RELATIVE_TO_SELF, 0.5f);
            rotateImage.setDuration(2500);
            //rotate_image.startAnimation(rotate);
            logotext.startAnimation(myanim);
            logoimage.startAnimation(rotateImage);
            final Intent i=new Intent(this,ChooseLogin.class);
            Thread timer=new Thread(){
                public void run(){
                    try{
                        sleep(4800);
                    } catch(InterruptedException e){
                        e.printStackTrace();
                    }
                    finally{
                        startActivity(i);
                        finish();
                    }
                }
            };
            timer.start();
        }
    }


