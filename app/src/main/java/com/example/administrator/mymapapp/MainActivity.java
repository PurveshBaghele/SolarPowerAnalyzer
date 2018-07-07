package com.example.administrator.mymapapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity  {

    EditText et;
    TextView tv;
    Button b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et=(EditText)findViewById(R.id.editText);
        tv=(TextView)findViewById(R.id.textView2);
        b=(Button)findViewById(R.id.button);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


              /*  if (view == this.tvValidate) {
                    if (view != null) {
                        ((InputMethodManager) getSystemService("input_method")).hideSoftInputFromWindow(view.getWindowToken(), 0);
                    }*/
                    if (isValidate()) {
                        try {
                            showMessage(Utility.validGSTIN(et.getText().toString().trim()));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    if(tv.getText().toString().equals("Valid"))
                    {

                        Toast.makeText(getApplicationContext(),"Registration Done!",Toast.LENGTH_LONG).show();
                        startActivity(new Intent(getApplicationContext(),SellerLoginActivity.class));
                    }
                }
           /// }
        });
    }

    private boolean isValidate() {
        if (!et.getText().toString().trim().isEmpty() && !et.getText().toString().trim().equals("")) {
            return true;
        }
        Toast.makeText(getApplicationContext(), "Please enter GSTIN number", Toast.LENGTH_LONG).show();
        return false;
    }

    private void showMessage(boolean valid) {
       // this.llCheckMessage.setVisibility(0);
       // this.tv.setText(this.etGSTIN.getText().toString().trim());
        if (valid) {
            tv.setText("Valid");
            //this.tvValidationMessage.setTextColor(getResources().getColor(R.color.green));
            return;
        }
        tv.setText("Invalid");
        //this.tvValidationMessage.setTextColor(getResources().getColor(R.color.red));
    }
}
