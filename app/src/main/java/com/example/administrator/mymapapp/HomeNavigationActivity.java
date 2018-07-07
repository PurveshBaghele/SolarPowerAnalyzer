package com.example.administrator.mymapapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class HomeNavigationActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private FirebaseAuth auth;
    Button btnStart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_navigation);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        auth = FirebaseAuth.getInstance();
        btninit();
        initGrid();
        
    }

    private void btninit() {
        btnStart=(Button)findViewById(R.id.btnStart);
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), LocationActivity.class));



            }
        });
    }

    private void initGrid() {
        GridView grid;
        String[] web = {
                "Reduced Energy Bills",

                "Negligible Maintenance",
                "Eco Friendly",
                "Anyone can use"

        } ;
        int[] imageId = {
                R.drawable.bulb,

                R.drawable.set,
                R.drawable.env,
                R.drawable.man,

                R.drawable.money

        };

        CustomGrid adapter = new CustomGrid(this, web, imageId);
        grid=(GridView)findViewById(R.id.grid);
        grid.setAdapter(adapter);


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if(id==R.id.signOut)
        {
            auth.signOut();
            startActivity(new Intent(getApplicationContext(), ChooseLogin.class));
            finish();
            Toast.makeText(getApplicationContext(),"Signout success !!!",Toast.LENGTH_LONG).show();

        }

        return super.onOptionsItemSelected(item);
    }

    private void signout() {

        Toast.makeText(getApplicationContext(),"Signout success !!!",Toast.LENGTH_LONG).show();

        FirebaseAuth.AuthStateListener authListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user == null) {
                    // user auth state is changed - user is null
                    // launch login activity
                    startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                    finish();
                    Toast.makeText(getApplicationContext(),"Signout success !!!",Toast.LENGTH_LONG).show();
                }
            }
        };
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        Intent i1;
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.panelItem) {
            i1=new Intent(this,ShowCatalogue.class);
            startActivity(i1);

        }
    else    if (id == R.id.panCatItem) {
            i1=new Intent(this,PanelListCat.class);
            startActivity(i1);

        }
      else  if (id == R.id.help) {
            i1=new Intent(this,HelpActivity.class);
            startActivity(i1);

        }
        else if (id == R.id.logout) {
           auth.signOut();
            startActivity(new Intent(getApplicationContext(), ChooseLogin.class));
            finish();
            Toast.makeText(getApplicationContext(),"Signout success !!!",Toast.LENGTH_LONG).show();

        }
        else if (id == R.id.contact) {

            startActivity(new Intent(getApplicationContext(), AboutUs.class));

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
