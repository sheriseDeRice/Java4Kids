package com.example.sherisesinyeelam.java4kids;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sherisesinyeelam.java4kids.ProgressPage.ShowProgressActivity;
import com.example.sherisesinyeelam.java4kids.SettingsPage.SettingsActivity;
import com.example.sherisesinyeelam.java4kids.FriendsPage.FriendsActivity;
import com.example.sherisesinyeelam.java4kids.GamesPage.GameActivity;
import com.example.sherisesinyeelam.java4kids.TheWorldLeaderBoard.LeaderBoardActivity;
import com.example.sherisesinyeelam.java4kids.UserProfilePage.UserProfileActivity;

import java.util.Calendar;

public class NavigationDrawer extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    Toolbar toolbar;
    DrawerLayout drawer;
    ActionBarDrawerToggle toggle;
    NavigationView navigationView;

    ImageView icon;
    TextView username, greetings;

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

        // Change the header
        View hView =  navigationView.getHeaderView(0);
        icon = (ImageView) hView.findViewById(R.id.imageView);
        username = (TextView)hView.findViewById(R.id.nav_user_name);
        greetings = (TextView) hView.findViewById(R.id.nav_greetings);
        greetings.setText("How are you doing?");

        if(SharedPrefManager.getInstance(NavigationDrawer.this).checkLoginOrNot()){
            //if user already logged in.
            int iconID = SharedPrefManager.getInstance(this).getUserIcon();
            icon.setImageResource(iconID);
            String usernameGreet = "Hi! " + SharedPrefManager.getInstance(this).getUsername();
            username.setText(usernameGreet);
        } else {
            if(LocalSharedPrefManager.getInstance(NavigationDrawer.this).checkLoginOrNot()){
                int iconID = LocalSharedPrefManager.getInstance(this).getUserIcon();
                icon.setImageResource(iconID);
                String nicknameGreet = "Hi! " + LocalSharedPrefManager.getInstance(this).getNickname();
                username.setText(nicknameGreet);
            }
            //username.setText("Hi!");
        }
        checkLoginBonus();
        getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, new ShowProgressActivity()).commit();
        getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, new UserProfileActivity()).commit();
    }

    // here to give login bonus
    public void checkLoginBonus(){
        // check login date
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        String todayDate = year + "" + month + "" + day;

        if(LocalSharedPrefManager.getInstance(NavigationDrawer.this).checkLoginOrNot()) {
            // if logged in as a guest
            boolean rewarded = LocalSharedPrefManager.getInstance(NavigationDrawer.this).getLoginTrend(todayDate);
            if (rewarded == false) {
                // give bonus
                Toast.makeText(NavigationDrawer.this, "Daily reward! Login Bonus Added!", Toast.LENGTH_SHORT).show();
            }
        } else if (SharedPrefManager.getInstance(NavigationDrawer.this).checkLoginOrNot()){
            // if logged as registered user
            boolean rewarded = SharedPrefManager.getInstance(NavigationDrawer.this).getLoginTrend(todayDate);
            if (rewarded == false) {
                // give reward
                Toast.makeText(NavigationDrawer.this, "Daily reward! Login Bonus Added!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_profile) {
            // Handle the game action
            UserProfileActivity userProfileActivity = new UserProfileActivity();
            getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, userProfileActivity).commit();

        } else if (id == R.id.nav_game) {
            // Handle the game action
            GameActivity gameActivity = new GameActivity();
            getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, gameActivity).commit();

        } else if (id == R.id.nav_progress) {
            // Handle the progress action
            ShowProgressActivity showProgressActivity = new ShowProgressActivity();
            getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, showProgressActivity).commit();

        } else if (id == R.id.nav_leading_board) {
            // Handle the friends action
            LeaderBoardActivity leaderBoardActivity = new LeaderBoardActivity();
            getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, leaderBoardActivity).commit();

        } else if (id == R.id.nav_friends) {
            // Handle the friends action
            FriendsActivity friendsActivity = new FriendsActivity();
            getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, friendsActivity).commit();

        } else if (id == R.id.nav_settings) {
            // Handle the settings action
            SettingsActivity settingsActivity = new SettingsActivity();
            getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, settingsActivity).commit();

        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
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
                                if(timeOfDay >= 3 && timeOfDay < 7){
                                    layout.setBackgroundResource(R.mipmap.a1bg_3amto7am);
                                } else if (timeOfDay >= 7 && timeOfDay < 8){
                                    layout.setBackgroundResource(R.mipmap.a2bg_7amto8am);
                                } else if (timeOfDay >= 8 && timeOfDay < 16){
                                    layout.setBackgroundResource(R.mipmap.a3bg_8amto4pm);
                                } else if (timeOfDay >= 16 && timeOfDay < 17){
                                    layout.setBackgroundResource(R.mipmap.a4bg_4pmto5pm);
                                } else if (timeOfDay >= 17 && timeOfDay < 20){
                                    layout.setBackgroundResource(R.mipmap.a5bg_5pmto8pm);
                                } else {
                                    layout.setBackgroundResource(R.mipmap.a6bg_8pmto3am);
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