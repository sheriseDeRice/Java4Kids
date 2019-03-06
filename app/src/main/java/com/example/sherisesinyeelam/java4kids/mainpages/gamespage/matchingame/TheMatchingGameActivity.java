package com.example.sherisesinyeelam.java4kids.mainpages.gamespage.matchingame;

import com.example.sherisesinyeelam.java4kids.R;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Calendar;

public class TheMatchingGameActivity extends AppCompatActivity {

    Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_the_matching_game);
        autoChangeBackground();

        dialog = new Dialog(this);

        popUpOpen();

        // todo, match super class to subclass pairs.
    }

    // todo, also apply this on the drop down menu inside the game.
    public void popUpOpen(){
        dialog.setContentView(R.layout.custompopup_lesson_description); // todo

        TextView closePopUp = (TextView) dialog.findViewById(R.id.close_popup);
        Button okay_btn = (Button) dialog.findViewById(R.id.okay_button);

        closePopUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        // todo, set textview - lesson description here.

        okay_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }

    // auto change background according to the time (day/night). These are default background.
    public void autoChangeBackground(){

        final LinearLayout layout = (LinearLayout) findViewById(R.id.the_matching_game_default_background);

        Thread t = new Thread() {
            @Override
            public void run() {
                try {
                    while (!isInterrupted()) {

                        TheMatchingGameActivity.super.runOnUiThread(new Runnable() {
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
