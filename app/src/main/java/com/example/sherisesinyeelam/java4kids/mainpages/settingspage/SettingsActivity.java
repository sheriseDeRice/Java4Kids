package com.example.sherisesinyeelam.java4kids.mainpages.settingspage;

import com.example.sherisesinyeelam.java4kids.NavigationDrawer;
import com.example.sherisesinyeelam.java4kids.R;
import com.example.sherisesinyeelam.java4kids.StartAppActivity;
import com.example.sherisesinyeelam.java4kids.loginregister.LoginActivity;
import com.example.sherisesinyeelam.java4kids.loginregister.LoginActivityPro;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

public class SettingsActivity extends Fragment {

    Bundle bundle;
    String login_status;

    TextView edit_name, edit_icon, edit_background, qAndA, go_login, go_logout;

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        //setContentView(R.layout.activity_settings);
//
////        FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.content_frame);
////        //Remember this is the FrameLayout area within your activity_main.xml
////        getLayoutInflater().inflate(R.layout.activity_settings, contentFrameLayout);
////        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
////        navigationView.getMenu().getItem(4).setChecked(true);
//
//        edit_name = (TextView) findViewById(R.id.edit_name);
//        edit_icon = (TextView) findViewById(R.id.edit_icon);
//        edit_background = (TextView) findViewById(R.id.edit_background);
//        qAndA = (TextView) findViewById(R.id.q_and_a);
//        go_login = (TextView) findViewById(R.id.go_login);
//        go_logout = (TextView) findViewById(R.id.go_logout);
//
//        try {
//            // the following collect the data that have been passed from login.
//            bundle = getIntent().getExtras();
//            login_status = bundle.getString("login_status");
//
//            if (login_status.equals("success")) {
//                go_login.setVisibility(View.GONE);
//                go_logout.setVisibility(View.VISIBLE);
//            }
//        } catch (Exception e){}
//
//
//        edit_name.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //TODO: maybe a popup that allows user to edit name
//                Toast.makeText(SettingsActivity.this, "pressed", Toast.LENGTH_SHORT).show();
//            }
//        });
//
//        edit_icon.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //TODO: future expectation
//                Toast.makeText(SettingsActivity.this, "pressed", Toast.LENGTH_SHORT).show();
//            }
//        });
//
//        edit_background.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //TODO: future expectation
//                Toast.makeText(SettingsActivity.this, "pressed", Toast.LENGTH_SHORT).show();
//            }
//        });
//
//        qAndA.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //TODO: open a new window with questions and answer
//                Toast.makeText(SettingsActivity.this, "pressed", Toast.LENGTH_SHORT).show();
//            }
//        });
//
//        go_login.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(SettingsActivity.this, LoginActivityPro.class);
//                startActivity(intent);
//            }
//        });
//
//        go_logout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(SettingsActivity.this, "Logout success :)", Toast.LENGTH_SHORT).show();
//                go_login.setVisibility(View.VISIBLE);
//                go_logout.setVisibility(View.INVISIBLE);
//                finish();
//                Intent intent = new Intent(SettingsActivity.this, StartAppActivity.class);
//                startActivity(intent);
//            }
//        });
//    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_settings, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getActivity().setTitle("Settings");

        edit_name = (TextView) getView().findViewById(R.id.edit_name);
        edit_icon = (TextView) getView().findViewById(R.id.edit_icon);
        edit_background = (TextView) getView().findViewById(R.id.edit_background);
        qAndA = (TextView) getView().findViewById(R.id.q_and_a);
        go_login = (TextView) getView().findViewById(R.id.go_login);
        go_logout = (TextView) getView().findViewById(R.id.go_logout);

        try {
            // the following collect the data that have been passed from login.
            bundle = getArguments();

            if(bundle != null){
                login_status = bundle.getString("login_status");
                if (login_status.equals("success")) {
                    go_login.setVisibility(View.GONE);
                    go_logout.setVisibility(View.VISIBLE);
                }
            }

        } catch (Exception e){}


        edit_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: maybe a popup that allows user to edit name
                Toast.makeText(getActivity(), "pressed", Toast.LENGTH_SHORT).show();
            }
        });

        edit_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: future expectation
                Toast.makeText(getActivity(), "pressed", Toast.LENGTH_SHORT).show();
            }
        });

        edit_background.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: future expectation
                Toast.makeText(getActivity(), "pressed", Toast.LENGTH_SHORT).show();
            }
        });

        qAndA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: open a new window with questions and answer
                Toast.makeText(getActivity(), "pressed", Toast.LENGTH_SHORT).show();
            }
        });

        go_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
            }
        });

        go_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Logout success :)", Toast.LENGTH_SHORT).show();
                go_login.setVisibility(View.VISIBLE);
                go_logout.setVisibility(View.INVISIBLE);
                getActivity().finish();
                Intent intent = new Intent(getActivity(), StartAppActivity.class);
                startActivity(intent);
            }
        });

    }
}
