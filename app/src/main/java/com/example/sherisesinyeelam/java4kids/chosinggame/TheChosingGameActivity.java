package com.example.sherisesinyeelam.java4kids.chosinggame;

import com.example.sherisesinyeelam.java4kids.NavigationDrawer;
import com.example.sherisesinyeelam.java4kids.R;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RelativeLayout;

import java.util.Calendar;

public class TheChosingGameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_the_chosing_game);
        autoChangeBackground();

        // todo, when this activity is opened, have a popup window teaching lessons. such as what is inheritance.
        // todo, question change and answer change when user click a correct or wrong answer.
        // todo, explain why wrong, and give score when right

    }

    // auto change background according to the time (day/night). These are default background.
    public void autoChangeBackground(){

        final RelativeLayout layout = (RelativeLayout) findViewById(R.id.default_background);

        Thread t = new Thread() {
            @Override
            public void run() {
                try {
                    while (!isInterrupted()) {

                        TheChosingGameActivity.super.runOnUiThread(new Runnable() {
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
