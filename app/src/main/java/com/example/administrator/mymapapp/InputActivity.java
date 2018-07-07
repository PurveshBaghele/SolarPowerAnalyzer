package com.example.administrator.mymapapp;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class InputActivity extends AppCompatActivity {

    ImageView iv;
    Spinner s1,s2;
    EditText e1,e2,e3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);

        iv=(ImageView)findViewById(R.id.imageView);
        s1=(Spinner)findViewById(R.id.spinner);
        s2=(Spinner)findViewById(R.id.spinner2);
        e1=(EditText)findViewById(R.id.editText);
        e2=(EditText)findViewById(R.id.editText3);
        e3=(EditText)findViewById(R.id.editText4);


        ArrayList<String> sub = new ArrayList<>();
        sub.add("(220-250W)");
        sub.add("(250-280W)");
        sub.add("(280-320W)");
        sub.add("(320-350W)");
        sub.add("(350-380W)");
        ArrayAdapter<String> adp = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, sub);
        s1.setAdapter(adp);

        sub = new ArrayList<>();
        sub.add("Residential");
        sub.add("Institutional");
        sub.add("Industrial");
        sub.add("Government");
        sub.add("Commercial");
        adp = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, sub);
        s2.setAdapter(adp);


        final Bundle b=getIntent().getExtras();

        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s[] = new String[5];

                s[0] = "";
                s[1] = "";
                s[2] = "";
                s[3] = "";
                s[4] = "";

                s[0] = e1.getText().toString();
                s[1] = e2.getText().toString();
                s[2] = s1.getSelectedItem().toString();
                s[3] = s1.getSelectedItem().toString();
                s[4] = e3.getText().toString();

                boolean flag=true;
                for(int i=0;i<5;i++)
                    if(s[i].compareTo("")==0)
                        flag=false;

                if(!flag)
                {
                    Toast.makeText(getApplicationContext(),"Details Incomplete.",Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent i=new Intent(getApplicationContext(),TbbdAct.class);
                b.putStringArray("input",s);
                i.putExtras(b);
                startActivity(i);

            }
        });
    }
}
