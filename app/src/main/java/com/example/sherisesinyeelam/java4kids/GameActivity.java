package com.example.sherisesinyeelam.java4kids;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;

import com.example.sherisesinyeelam.java4kids.snakegame.SnakeActivity;

public class GameActivity extends NavigationDrawer {

    ImageButton snake_lv1;
    ImageButton chosing_level1;
    ImageButton matching_level1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_game);

        // The navigation bar
        FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.content_frame);
        //Remember this is the FrameLayout area within your activity_main.xml
        getLayoutInflater().inflate(R.layout.activity_game, contentFrameLayout);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.getMenu().getItem(1).setChecked(true);

        snake_lv1 = (ImageButton) findViewById(R.id.snake_level1);
        snake_lv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GameActivity.this, SnakeActivity.class);
                startActivity(intent);
            }
        });

        chosing_level1 = (ImageButton) findViewById(R.id.chosing_level1);
        chosing_level1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO
//                Intent intent = new Intent(GameActivity.this, .class);
//                startActivity(intent);
            }
        });

        matching_level1 = (ImageButton) findViewById(R.id.matching_level1);
        matching_level1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO
//                Intent intent = new Intent(GameActivity.this, .class);
//                startActivity(intent);
            }
        });


    }
}
