package com.example.sherisesinyeelam.java4kids.mainpages;

import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sherisesinyeelam.java4kids.NavigationDrawer;
import com.example.sherisesinyeelam.java4kids.R;

public class UserProfileActivity extends NavigationDrawer {

    ImageView user_icon;
    TextView user_name, user_level, user_point, user_number_of_friends, user_ranking;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_user_profile);

        FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.content_frame);
        //Remember this is the FrameLayout area within your activity_main.xml
        getLayoutInflater().inflate(R.layout.activity_user_profile, contentFrameLayout);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.getMenu().getItem(0).setChecked(true);

        // default profile page
        user_icon = (ImageView) findViewById(R.id.user_icon);
        user_name = (TextView) findViewById(R.id.user_name);
        user_level = (TextView) findViewById(R.id.user_level);
        user_point = (TextView) findViewById(R.id.user_point);
        user_number_of_friends = (TextView) findViewById(R.id.user_number_of_friends);
        user_ranking = (TextView) findViewById(R.id.user_ranking);

        // update user information by taking data from the database.
        // todo, connect to database
        String name = "Noname";
        int lv = 100;
        int points = 10000;
        int numOfFd = 0; // todo, check the size or length of friends table for the user.
        int ranks = 1;
        user_name.setText("Name: " + name);
        user_level.setText("Level: " + lv);
        user_point.setText("Current points: " + points);
        user_number_of_friends.setText("Number of friends: " + numOfFd);
        user_ranking.setText("Ranking: " + ranks);

    }
}
