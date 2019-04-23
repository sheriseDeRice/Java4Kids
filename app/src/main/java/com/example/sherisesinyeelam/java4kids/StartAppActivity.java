package com.example.sherisesinyeelam.java4kids;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.sherisesinyeelam.java4kids.LoginAndRegister.RegisterActivity;
import com.example.sherisesinyeelam.java4kids.LoginAndRegister.LoginRequest;
import com.example.sherisesinyeelam.java4kids.LoginAndRegister.RequestHandler;
import com.example.sherisesinyeelam.java4kids.ProgressPage.GetUserLessonProgress;

import org.json.JSONException;
import org.json.JSONObject;

public class StartAppActivity extends AppCompatActivity {

    private Button startApp, login;
    private TextView appVersion;
    private View startApp_default, startApp_login_form;

    private Button signin, login_cancel;
    private EditText login_email, login_password;
    private TextView registerLink;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_app);
        //handleIntent();
        startAppContent();
    }

//    @Override
//    protected void onNewIntent(Intent intent) {
//        super.onNewIntent(intent);
//        setIntent(intent);
//        handleIntent();
//    }
//
//    private void handleIntent(){
//        // ATTENTION: This was auto-generated to handle app links.
//        Intent appLinkIntent = getIntent();
//        String appLinkAction = appLinkIntent.getAction();
//        Uri appLinkData = appLinkIntent.getData();
//        if(appLinkAction != null){
//            startAppContent();
//        }
//    }

    public void startAppContent(){
        progressDialog = new ProgressDialog(StartAppActivity.this);
        appVersion = (TextView) findViewById(R.id.app_version);
        startApp = (Button) findViewById(R.id.start_app);
        login = (Button) findViewById(R.id.cover_login);
        startApp_default = (LinearLayout) findViewById(R.id.start_app_default);
        startApp_login_form = (LinearLayout) findViewById(R.id.start_app_login);

        appVersion.setText("version 0.0.3");

        if (SharedPrefManager.getInstance(StartAppActivity.this).checkLoginOrNot()) {
            //if user already logged in.
            login.setVisibility(View.INVISIBLE);
        }

        startApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkInternet() != false) {
                    Intent intent = new Intent(StartAppActivity.this, NavigationDrawer.class);
                    // check if user is first time enter the app.
                    if (!LocalSharedPrefManager.getInstance(StartAppActivity.this).checkLoginOrNot()) {
                        setNickname(intent);
                    } else {
                        // used for testing when error
                        //LocalSharedPrefManager.getInstance(StartAppActivity.this).prefReset();
                        Toast.makeText(StartAppActivity.this, "Welcome back! :)", Toast.LENGTH_SHORT).show();
                        startActivity(intent);
                    }
                } else {
                    Toast.makeText(StartAppActivity.this, "Please connect to the internet", Toast.LENGTH_SHORT).show();
                }
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkInternet() != false) {
                    startApp_default.setVisibility(View.GONE);
                    startApp_login_form.setVisibility(View.VISIBLE);
                    startAppLoginActivity();
                } else {
                    Toast.makeText(StartAppActivity.this, "Please connect to the internet", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    // alert dialog that ask user an input as their nickname and stores it locally.
    public void setNickname(final Intent intent){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Your nickname");

        final EditText input = new EditText(this);
        // specifying input type
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(input);
        // button response
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //if(LocalSharedPrefManager.getInstance(StartAppActivity.this).checkLoginOrNot()) {
                if(!input.getText().toString().equals("")) {
                    LocalSharedPrefManager.getInstance(StartAppActivity.this)
                            .guestLogin(0, "Guest", 1, 0, R.drawable.default_icon_foreground, 1);
                    LocalSharedPrefManager.getInstance(StartAppActivity.this).setNickname(input.getText().toString());
                    Toast.makeText(StartAppActivity.this, "Welcome! :)", Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                } else {
                    Toast.makeText(StartAppActivity.this, "Please enter your nickname", Toast.LENGTH_LONG).show();
                }
                //}
            }
        });
        builder.show();
    }

    // handle login from starting page.
    public void startAppLoginActivity(){
        login_cancel = (Button) findViewById(R.id.cover_login_cancel);
        login_email = (EditText) findViewById(R.id.cover_login_email);
        login_password = (EditText) findViewById(R.id.cover_login_password);
        signin = (Button) findViewById(R.id.cover_login_signin);
        registerLink = (TextView) findViewById(R.id.cover_login_registerMe);

        login_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startApp_default.setVisibility(View.VISIBLE);
                startApp_login_form.setVisibility(View.GONE);
            }
        });
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (login_email.getText().toString().matches("") && login_password.getText().toString().matches("")){
                    Toast.makeText(StartAppActivity.this, "Please fill in the boxes", Toast.LENGTH_SHORT).show();
                } else if(login_email.getText().toString().matches("")){
                    Toast.makeText(StartAppActivity.this, "Please enter your email.", Toast.LENGTH_SHORT).show();
                } else if (login_password.getText().toString().matches("")){
                    Toast.makeText(StartAppActivity.this, "Please enter your password.", Toast.LENGTH_SHORT).show();
                } else {
                    onLogin(v);
                }
            }
        });
        registerLink.setPaintFlags(registerLink.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        registerLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StartAppActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

    }

    // connect to the database to check if user exist and if password correct.
    public void onLogin(View v){
        final String mail = login_email.getText().toString();
        final String pw = login_password.getText().toString();

        progressDialog.setMessage("Logging in...");
        progressDialog.show();

        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
                try{
                    JSONObject jsonResponse = new JSONObject(response);
                    if(!jsonResponse.getBoolean("error")){
                        // if error is false mean user successfully logged in
                        SharedPrefManager.getInstance(StartAppActivity.this)
                                .userLogin(jsonResponse.getInt("userID"),
                                        jsonResponse.getString("firstname"),
                                        jsonResponse.getString("email"),
                                        jsonResponse.getString("username"),
                                        jsonResponse.getInt("level"),
                                        jsonResponse.getInt("totalScore"),
                                        jsonResponse.getInt("userIcon"));
                        // get user's lesson progress from the database.
                        getUserProgress(jsonResponse.getInt("userID"));
                        Toast.makeText(StartAppActivity.this, "Login success :) Welcome back!", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(StartAppActivity.this, NavigationDrawer.class);
                        startActivity(intent);

                    } else {
                        //Toast.makeText(StartAppActivity.this, jsonResponse.getString("message"), Toast.LENGTH_LONG).show();
                        Toast.makeText(StartAppActivity.this, "fail to login", Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) { e.printStackTrace(); }
            }
        };
        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(StartAppActivity.this, error.getMessage(), Toast.LENGTH_LONG).show();;
            }
        };
        // cancel the progress dialog if it runs overtime.
        Runnable progressRunnable = new Runnable() {
            @Override
            public void run() {
                //Toast.makeText(StartAppActivity.this, "Error occur, Please try again.", Toast.LENGTH_SHORT).show();
                progressDialog.cancel();
            }
        };
        Handler pdCanceller = new Handler();
        pdCanceller.postDelayed(progressRunnable, 10000);

        LoginRequest loginRequest = new LoginRequest(mail, pw, responseListener, errorListener);
        RequestHandler.getInstance(StartAppActivity.this).addToRequestQueue(loginRequest);
    }

    // connect to the database and get user progress.
    public void getUserProgress(final int userID){
        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    //Toast.makeText(StartAppActivity.this, jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                    if (!jsonObject.getBoolean("error")) {
                        SharedPrefManager.getInstance(StartAppActivity.this)
                                .lesson1ProgressUpdate(jsonObject.getString("lesson1"));
                        SharedPrefManager.getInstance(StartAppActivity.this)
                                .lesson2ProgressUpdate(jsonObject.getString("lesson2"));
                    } else {
                        Toast.makeText(StartAppActivity.this, jsonObject.getString("message"), Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.hide();
                Toast.makeText(StartAppActivity.this, error.getMessage(), Toast.LENGTH_LONG).show();;
            }
        };
        GetUserLessonProgress getUserLessonProgress = new GetUserLessonProgress(userID, responseListener, errorListener);
        RequestHandler.getInstance(StartAppActivity.this).addToRequestQueue(getUserLessonProgress);
    }

    // check if user connect to the internet.
    public boolean checkInternet(){
        boolean connected = false;
        ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
            //we are connected to a network
            connected = true;
        } else {
            connected = false;
        }
        return connected;
    }
}
