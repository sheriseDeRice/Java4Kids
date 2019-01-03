package com.example.sherisesinyeelam.java4kids;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Calendar;

public class StartAppActivity extends AppCompatActivity {

    private Button startApp, login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_app);

        autoChangeBackground();

        startApp = (Button) findViewById(R.id.start_app);
        login = (Button) findViewById(R.id.cover_login);

        startApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StartAppActivity.this, NavigationDrawer.class);
                startActivity(intent);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startApp.setVisibility(View.INVISIBLE);
//                login.setVisibility(View.INVISIBLE);
//
//                login_cancel.setVisibility(View.VISIBLE);
//                login_email.setVisibility(View.VISIBLE);
//                login_password.setVisibility(View.VISIBLE);
//                signin.setVisibility(View.VISIBLE);
//                registerMe.setVisibility(View.VISIBLE);

                Intent intent = new Intent(StartAppActivity.this, StartAppLoginActivity.class);
                startActivity(intent);

            }
        });

    }

    // auto change background according to the time (day/night). These are default background.
    public void autoChangeBackground(){

        final LinearLayout layout = (LinearLayout) findViewById(R.id.startapp_background);

        Thread t = new Thread() {
            @Override
            public void run() {
                try {
                    while (!isInterrupted()) {

                        StartAppActivity.super.runOnUiThread(new Runnable() {
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
