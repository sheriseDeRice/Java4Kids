package com.example.sherisesinyeelam.java4kids;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.Calendar;

public class NavigationDrawer extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    Toolbar toolbar;
    DrawerLayout drawer;
    ActionBarDrawerToggle toggle;
    NavigationView navigationView;

    Bundle bundle;
    String login_status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_drawer);

        autoChangeBackground(); // change background according to the time.

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        try {
            // the following collect the data that have been passed from login.
            bundle = getIntent().getExtras();
            login_status = bundle.getString("login_status");

            if (login_status.equals("success")) {
                navigationView.getMenu().findItem(R.id.nav_login).setVisible(false);
                navigationView.getMenu().findItem(R.id.nav_logout).setVisible(true);
            }
        } catch (Exception e){}

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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_game) {
            // Handle the game action
            Intent intent = new Intent(NavigationDrawer.this, GameActivity.class);
            try {
                if (login_status.equals("success")) {
                    intent.putExtra("login_status", "success");
                }
            } catch (Exception e) {}
            startActivity(intent);

        } else if (id == R.id.nav_progress) {
            // Handle the progress action

        } else if (id == R.id.nav_friends) {
            // Handle the friends action
            Intent intent = new Intent(NavigationDrawer.this, FriendsActivity.class);
            try {
                if (login_status.equals("success")) {
                    intent.putExtra("login_status", "success");
                }
            } catch (Exception e) {}
            startActivity(intent);

        } else if (id == R.id.nav_settings) {
            // Handle the settings action

        } else if (id == R.id.nav_login) {
            // Handle the login action
            Intent intent = new Intent(NavigationDrawer.this, LoginActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_logout){
            Toast.makeText(this, "Logout success :)", Toast.LENGTH_SHORT).show();
            navigationView.getMenu().findItem(R.id.nav_login).setVisible(true);
            navigationView.getMenu().findItem(R.id.nav_logout).setVisible(false);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return false;
    }

    // auto change background according to the time (day/night). These are default background.
    public void autoChangeBackground(){

        final RelativeLayout layout = (RelativeLayout) findViewById(R.id.default_background);

        Thread t = new Thread() {
            @Override
            public void run() {
                try {
                    while (!isInterrupted()) {

                        NavigationDrawer.super.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Calendar c = Calendar.getInstance();
                                int timeOfDay = c.get(Calendar.HOUR_OF_DAY);
                                if(timeOfDay >= 7 && timeOfDay < 17){
                                    layout.setBackgroundResource(R.color.LightSkyBlue);
                                }
                                else{
                                    layout.setBackgroundResource(R.color.PaleGoldenrod);
                                }
                            }
                        });
                        Thread.sleep(1000);
                    }
                } catch (InterruptedException e) {}
            }
        };
        t.start();

    }
}

// resources used:
// RobinsonProgramming.com (2016). Android Studio - Create Navigation Drawer [online]. Available at https://www.youtube.com/watch?v=ju837bQOBfg [accessed 26/12/2018]
// _____. Open Activity from Menu Click - Android Studio 2.1.2 [online]. Available at https://www.youtube.com/watch?v=a0sHRM54njg [accessed 26/12/2018]
// ravinder ganwal(2017). NAVIGATION DRAWER IN EVERY ACTIVITY - ANDROID DEVELOPMENT [online]. available at http://shockingandroid.blogspot.com/2017/04/navigation-drawer-in-every-activity.html [accessed 27/12/2018]
// Ramkumar N. (2017). Android Changing Menu Items at Run Time [online]. available at https://android.i-visionblog.com/android-changing-menu-items-at-run-time-48970f0cf1b7 [access 02/01/2019]
// stackoverflow (2017). How to send string from one activity to another? [online]. available at https://stackoverflow.com/questions/18146614/how-to-send-string-from-one-activity-to-another [ access 02/01/2019]