package com.example.administrator.mymapapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewStub;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ShowCatalogue extends AppCompatActivity {
    private ViewStub stubGrid;
    private ViewStub stubList;
    private ListView listView;
    private GridView gridView;
    private ListViewAdapter listViewAdapter;
    private GridViewAdapter gridViewAdapter;
    private List<Seller> productList;
    private int currentViewMode = 0;
    private DatabaseReference mFirebaseDatabase;
    private FirebaseDatabase mFirebaseInstance;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_catalogue);

        //stubList = (ViewStub) findViewById(R.id.stub_list);
       // stubGrid = (ViewStub) findViewById(R.id.stub_grid);

        //Inflate ViewStub before get view

        //stubList.inflate();
        //stubGrid.inflate();

       // listView = (ListView) findViewById(R.id.mylistview);
        gridView = (GridView) findViewById(R.id.mygridview);

        //get list of product
        getProductList();

        //Get current view mode in share reference
       // SharedPreferences sharedPreferences = getSharedPreferences("ViewMode", MODE_PRIVATE);
        //currentViewMode = sharedPreferences.getInt("currentViewMode", VIEW_MODE_LISTVIEW);//Default is view listview
        //Register item lick
       // listView.setOnItemClickListener(onItemClick);
        gridView.setOnItemClickListener(onItemClick);

        //switchView();
      // setAdapters();

    }

    /*private void switchView() {

        if(VIEW_MODE_LISTVIEW == currentViewMode) {
            //Display listview
            stubList.setVisibility(View.VISIBLE);
            //Hide gridview
            stubGrid.setVisibility(View.GONE);
        } else {
            //Hide listview
            stubList.setVisibility(View.GONE);
            //Display gridview
            stubGrid.setVisibility(View.VISIBLE);
        }

    }
*/
  /*  private void setAdapters() {
       /* if(VIEW_MODE_LISTVIEW == currentViewMode) {
            listViewAdapter = new ListViewAdapter(this, R.layout.list_item, productList);
            listView.setAdapter(listViewAdapter);
        } else {*/
                  // }
    //}

    public List<Seller> getProductList() {
        mFirebaseInstance = FirebaseDatabase.getInstance();
        productList=new ArrayList<Seller>();
        // get reference to 'users' node
        mFirebaseDatabase = mFirebaseInstance.getReference("sellers");

        mFirebaseDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot ch : dataSnapshot.getChildren()) {
                   //  Log.e("" +
                     //   "Keyfrom Firebase",""+ ch.getKey()); //displays the key for the node
                      //Toast.makeText(getApplicationContext(),"Data: "+ ch.child("name").getValue(),Toast.LENGTH_LONG).show();
                 productList.add( new Seller(ch.child("name").getValue().toString(),ch.child("business_name").getValue().toString(),ch.child("city").getValue().toString(),ch.child("cities_of_operation").getValue().toString(),ch.child("number").getValue().toString(),ch.child("address").getValue().toString(),ch.child("email").getValue().toString()));

                }
                gridViewAdapter = new GridViewAdapter(getApplicationContext(), R.layout.grid_item, productList);
                gridView.setAdapter(gridViewAdapter);

            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    
       // Toast.makeText(getApplicationContext()," "+productList.size()+" Sellers available !!!",Toast.LENGTH_LONG).show();

        return productList;
    }

    AdapterView.OnItemClickListener onItemClick = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Intent i1=new Intent(getApplicationContext(), SellerDetails.class);
            Seller s1=productList.get(position);
             Toast.makeText(getApplicationContext(), productList.get(position).getName() +"Position : "+position, Toast.LENGTH_SHORT).show();

            i1.putExtra("Seller",s1);

            startActivity(i1);
            finish();
            //Do any thing when user click to item
           // Toast.makeText(getApplicationContext(), productList.get(position).getName() + " - " + productList.get(position).getDescription(), Toast.LENGTH_SHORT).show();
        }
    };

   /* @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }
*/
  /*  @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item_menu_1:
                if(VIEW_MODE_LISTVIEW == currentViewMode) {
                    currentViewMode = VIEW_MODE_GRIDVIEW;
                } else {
                    currentViewMode = VIEW_MODE_LISTVIEW;
                }
                //Switch view
                switchView();
                //Save view mode in share reference
                SharedPreferences sharedPreferences = getSharedPreferences("ViewMode", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt("currentViewMode", currentViewMode);
                editor.commit();

                break;
        }
        return true;
    }*/
}

