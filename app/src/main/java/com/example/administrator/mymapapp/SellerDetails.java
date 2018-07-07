package com.example.administrator.mymapapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SellerDetails extends AppCompatActivity {
    private DatabaseReference mFirebaseDatabase;
    private FirebaseDatabase mFirebaseInstance;
    ArrayList<Seller> seller;
    int position;
    Seller s1;
    TextView name,city,mail,addr,contact,business;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.seller_details);
      s1 =(Seller)getIntent().getSerializableExtra("Seller");
        Toast.makeText(getApplicationContext(),"chal gaya "+ s1.getName() , Toast.LENGTH_SHORT).show();

initView();
       // readFromFire();
    }

    private void initView() {
        name=(TextView)findViewById(R.id.name);
        addr=(TextView)findViewById(R.id.address);

        contact=(TextView)findViewById(R.id.contact);
        business=(TextView)findViewById(R.id.business);
        city=(TextView)findViewById(R.id.city);
       // seller=new ArrayList<>();
        Toast.makeText(getApplicationContext(),"chal gaya "+ s1.getName() , Toast.LENGTH_SHORT).show();
        name.setText(s1.getName().toString());
       addr.setText(s1.getAddress().toString());
       contact.setText(s1.getNumber().toString());
        city.setText(s1.getCity().toString());
       //mail.setText(s1.getEmail().toString());
        business.setText(s1.getBusiness_name().toString());
        //initComp();
        //name=(TextView)findViewById(R.id.name);*/

    }

   /* private void readFromFire() {
        mFirebaseInstance = FirebaseDatabase.getInstance();
       // panellist=new ArrayList<>();
        // get reference to 'users' node
        mFirebaseDatabase = mFirebaseInstance.getReference("panels");

        mFirebaseDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
               // panellist.clear();
                for (DataSnapshot ch : dataSnapshot.getChildren()) {
                 //    Toast.makeText(getApplicationContext(),"in ondata seller : "+ch,Toast.LENGTH_LONG).show();
                    //  // Log.e("" +
                    //    "Keyfrom Firebase",""+ ch.getKey()); //displays the key for the node

                    Seller s=ch.getValue(Seller.class);
                    seller.add(s);
                    // panellist.add( p1);

                }

                //adapter=new PanelAdapter(getApplicationContext(),panellist);
              //  list.setAdapter(adapter);

                Seller s1=seller.get(position);
                Toast.makeText(getApplicationContext(),"Data: "+s1.getName(),Toast.LENGTH_LONG).show();
                //
                //initComp(s1);



            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }*/

    private void initComp() {

        name.setText(name.getText()+s1.getName());
        addr.setText(addr.getText()+s1.getAddress());
        contact.setText(contact.getText()+s1.getNumber());
       city.setText(city.getText()+s1.getCities_of_operation());
        mail.setText(mail.getText()+s1.getEmail());
        business.setText(business.getText()+s1.getBusiness_name());






    }


}
