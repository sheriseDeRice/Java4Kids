package com.example.sherisesinyeelam.java4kids.mainpages.friendspage;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.widget.FrameLayout;

import com.example.sherisesinyeelam.java4kids.NavigationDrawer;
import com.example.sherisesinyeelam.java4kids.R;

public class FriendsActivity extends NavigationDrawer {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_friends);

        FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.content_frame);
        //Remember this is the FrameLayout area within your activity_main.xml
        getLayoutInflater().inflate(R.layout.activity_friends, contentFrameLayout);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.getMenu().getItem(3).setChecked(true);

        // todo show a list of friends added here.

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                Intent intent = new Intent(FriendsActivity.this, SearchForFriendsEngine.class);
                startActivity(intent);
            }
        });
    }
}

// stackoverflow(2014). This Activity already has an action bar supplied by the window decor [online]. Available at https://stackoverflow.com/questions/26515058/this-activity-already-has-an-action-bar-supplied-by-the-window-decor [accessed 27/12/2018]