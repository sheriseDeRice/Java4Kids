package com.example.sherisesinyeelam.java4kids.mainpages.gamespage;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;

import com.example.sherisesinyeelam.java4kids.NavigationDrawer;
import com.example.sherisesinyeelam.java4kids.R;
import com.example.sherisesinyeelam.java4kids.mainpages.gamespage.chosinggame.TheChosingGameActivity;
import com.example.sherisesinyeelam.java4kids.mainpages.gamespage.draganddropgame.TheDragAndDropGameActivity;
import com.example.sherisesinyeelam.java4kids.mainpages.gamespage.matchingame.TheMatchingGameActivity;

public class GameActivity extends NavigationDrawer {

    ImageButton snake_lv1, chosing_level1, matching_level1, linking_level1, dragNdrop_level1;


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

        // todo, set new images for all the game

//        linking_level1 = (ImageButton) findViewById(R.id.linking_level1);
//        linking_level1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(GameActivity.this, TheLinkingGameActivity.class);
//                startActivity(intent);
//            }
//        });

//        snake_lv1 = (ImageButton) findViewById(R.id.snake_level1);
//        snake_lv1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(GameActivity.this, SnakeActivity.class);
//                startActivity(intent);
//            }
//        });

        chosing_level1 = (ImageButton) findViewById(R.id.chosing_level1);
        chosing_level1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GameActivity.this, TheChosingGameActivity.class);
                startActivity(intent);
            }
        });

        dragNdrop_level1 = (ImageButton) findViewById(R.id.drag_and_drop_level1);
        dragNdrop_level1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GameActivity.this, TheDragAndDropGameActivity.class);
                startActivity(intent);
            }
        });

        matching_level1 = (ImageButton) findViewById(R.id.matching_level1);
        matching_level1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GameActivity.this, TheMatchingGameActivity.class);
                startActivity(intent);
            }
        });



    }
}
