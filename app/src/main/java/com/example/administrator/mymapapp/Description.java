package com.example.administrator.mymapapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Description extends AppCompatActivity {
    private String userID;
    private FirebaseDatabase mFirebaseDatabase;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private DatabaseReference myRef;
    EditText desc;
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);
        mAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        myRef = mFirebaseDatabase.getReference();
        FirebaseUser user = mAuth.getCurrentUser();
        userID = user.getUid();
        desc=(EditText)findViewById(R.id.desc);
        pd = new ProgressDialog(this);
        pd.setMessage("Saving....");
    }
    public void next_click(View v)
    {
        String id= FirebaseAuth.getInstance().getCurrentUser().getUid();
        if (TextUtils.isEmpty(desc.getText().toString())) {
            Toast.makeText(getApplicationContext(), "Enter email address", Toast.LENGTH_SHORT).show();
            return;
        }
        pd.show();
        myRef.child("sellers").child(userID).child("description").setValue(desc.getText().toString());

        startActivity(new Intent(this,Panels.class));

    }
}
