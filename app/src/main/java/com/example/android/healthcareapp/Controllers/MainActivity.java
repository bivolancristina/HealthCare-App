package com.example.android.healthcareapp.Controllers;

import android.app.ActivityManager;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.android.healthcareapp.R;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener , FragmentReminder.OnFragmentInteractionListener ,Nearbyhosp.OnFragmentInteractionListener,
        Knowmed.OnFragmentInteractionListener,HealthTips.OnFragmentInteractionListener,
        Home.OnFragmentInteractionListener {
    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    FirebaseAuth mAuth;
    TextView tv;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher);

        setTaskDescription( new ActivityManager.TaskDescription("Care For U",bitmap));

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Care for U");

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        mAuth = FirebaseAuth.getInstance();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);


        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

    }
    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser currentUser = mAuth.getCurrentUser();

        if (currentUser == null){

            sendToReg();
        }else{
            NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
            View headerView =navigationView.getHeaderView(0);

            tv=(TextView) headerView.findViewById(R.id.nav_tv);

            tv.setText("Hi, "+ currentUser.getEmail());
        }


    }

    private void sendToReg() {

        Intent toMain = new Intent(MainActivity.this, RegisterActivity.class);
        //flag set to prevent going back to startpage after being logged in
        toMain.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(toMain);
        finish();
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
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        Log.d("Item:",item.toString());
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_medrem) {
            FragmentReminder rm=new FragmentReminder();
            getSupportFragmentManager().beginTransaction().replace(R.id.container,rm).commit();

        } else if (id == R.id.nav_hosp) {
            Nearbyhosp nh=new Nearbyhosp();
            getSupportFragmentManager().beginTransaction().replace(R.id.container,nh).commit();


        } else if (id == R.id.nav_medinfo) {
            Knowmed km=new Knowmed();
            getSupportFragmentManager().beginTransaction().replace(R.id.container,km).commit();


        }
        else if (id == R.id.nav_healthtips) {
            HealthTips ht=new HealthTips();
            getSupportFragmentManager().beginTransaction().replace(R.id.container,ht).commit();


        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}