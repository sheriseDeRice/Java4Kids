package com.example.sherisesinyeelam.java4kids;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.sherisesinyeelam.java4kids.mainpages.UserProfileActivity;

public class StartAppActivity extends AppCompatActivity {

    private Button startApp, login;
    TextView appVersion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_app);

        appVersion = (TextView) findViewById(R.id.app_version);
        startApp = (Button) findViewById(R.id.start_app);
        login = (Button) findViewById(R.id.cover_login);

        startApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StartAppActivity.this, UserProfileActivity.class);
                startActivity(intent);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StartAppActivity.this, StartAppLoginActivity.class);
                startActivity(intent);

            }
        });

    }

}
