package com.example.sherisesinyeelam.java4kids.LoginAndRegister;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.sherisesinyeelam.java4kids.NavigationDrawer;
import com.example.sherisesinyeelam.java4kids.ProgressPage.GetUserLessonProgress;
import com.example.sherisesinyeelam.java4kids.R;
import com.example.sherisesinyeelam.java4kids.SharedPrefManager;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;

public class LoginActivity extends AppCompatActivity {

    private Button signin;
    private EditText login_email, login_password;
    private TextView registerLink;

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        autoChangeBackground();

        //add back button on the toolbar.
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        login_email = (EditText) findViewById(R.id.login_email);
        login_password = (EditText) findViewById(R.id.login_password);
        signin = (Button) findViewById(R.id.login_signin);
        registerLink = (TextView) findViewById(R.id.login_registerMe);
        progressDialog = new ProgressDialog(LoginActivity.this);

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (login_email.getText().toString().matches("") && login_password.getText().toString().matches("")){
                    Toast.makeText(LoginActivity.this, "Please fill in the boxes", Toast.LENGTH_SHORT).show();
                } else if(login_email.getText().toString().matches("")){
                    Toast.makeText(LoginActivity.this, "Please enter your email.", Toast.LENGTH_SHORT).show();
                } else if (login_password.getText().toString().matches("")){
                    Toast.makeText(LoginActivity.this, "Please enter your password.", Toast.LENGTH_SHORT).show();
                } else {
                    onLogin(v);
                }
            }
        });
        registerLink.setPaintFlags(registerLink.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        registerLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

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
                        SharedPrefManager.getInstance(LoginActivity.this)
                                .userLogin(jsonResponse.getInt("userID"),
                                            jsonResponse.getString("firstname"),
                                            jsonResponse.getString("email"),
                                            jsonResponse.getString("username"),
                                            jsonResponse.getInt("level"),
                                            jsonResponse.getInt("totalScore"),
                                            jsonResponse.getInt("userIcon"));

                        onCreateFirstTimeProgress(jsonResponse.getInt("userID"));

                        Toast.makeText(LoginActivity.this, "Login success :) Welcome back!", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(LoginActivity.this, NavigationDrawer.class);
                        startActivity(intent);

                    } else {
                        Toast.makeText(LoginActivity.this, jsonResponse.getString("message"), Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(LoginActivity.this, error.getMessage(), Toast.LENGTH_LONG).show();;
            }
        };
        // cancel the progress dialog if it runs overtime.
        Runnable progressRunnable = new Runnable() {
            @Override
            public void run() {
                progressDialog.cancel();
            }
        };
        Handler pdCanceller = new Handler();
        pdCanceller.postDelayed(progressRunnable, 10000);

        LoginRequest loginRequest = new LoginRequest(mail, pw, responseListener, errorListener);
        RequestHandler.getInstance(LoginActivity.this).addToRequestQueue(loginRequest);
    }

    public void onCreateFirstTimeProgress(final int userID){
        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    //Toast.makeText(LoginActivity.this, jsonObject.getString("message"), Toast.LENGTH_SHORT).show();

                    if (!jsonObject.getBoolean("error")) {
                        //onSupportNavigateUp();
                    } else {
                        //Toast.makeText(LoginActivity.this, jsonObject.getString("message"), Toast.LENGTH_LONG).show();
                        getUserProgress(userID);
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
                Toast.makeText(LoginActivity.this, error.getMessage(), Toast.LENGTH_LONG).show();;
            }
        };

        CreateFirstTimeProgressRequest createFirstTimeProgressRequest = new CreateFirstTimeProgressRequest(userID, responseListener, errorListener);
        RequestHandler.getInstance(LoginActivity.this).addToRequestQueue(createFirstTimeProgressRequest);
    }

    public void getUserProgress(final int userID){
        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    if (!jsonObject.getBoolean("error")) {
                        //Toast.makeText(LoginActivity.this, jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                        SharedPrefManager.getInstance(LoginActivity.this)
                                .lesson1ProgressUpdate(jsonObject.getString("lesson1"));
                        SharedPrefManager.getInstance(LoginActivity.this)
                                .lesson2ProgressUpdate(jsonObject.getString("lesson2"));
                        onSupportNavigateUp();

                    } else {
                        Toast.makeText(LoginActivity.this, jsonObject.getString("message"), Toast.LENGTH_LONG).show();
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
                Toast.makeText(LoginActivity.this, error.getMessage(), Toast.LENGTH_LONG).show();;
                // Log.e(RegisterActivity.class.getSimpleName(), "Error at response : " + error.getMessage());
            }
        };

        GetUserLessonProgress getUserLessonProgress = new GetUserLessonProgress(userID, responseListener, errorListener);
        RequestHandler.getInstance(LoginActivity.this).addToRequestQueue(getUserLessonProgress);
    }

    // auto change background according to the time (day/night). These are default background.
    public void autoChangeBackground(){

        final LinearLayout layout = (LinearLayout) findViewById(R.id.login_default_background);

        Thread t = new Thread() {
            @Override
            public void run() {
                try {
                    while (!isInterrupted()) {

                        LoginActivity.super.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Calendar c = Calendar.getInstance();
                                int timeOfDay = c.get(Calendar.HOUR_OF_DAY);
                                if(timeOfDay >= 3 && timeOfDay < 7){
                                    layout.setBackgroundResource(R.mipmap.a1bg_3amto7am);
                                } else if (timeOfDay >= 7 && timeOfDay < 8){
                                    layout.setBackgroundResource(R.mipmap.a2bg_7amto8am);
                                } else if (timeOfDay >= 8 && timeOfDay < 16){
                                    layout.setBackgroundResource(R.mipmap.a3bg_8amto4pm);
                                } else if (timeOfDay >= 16 && timeOfDay < 17){
                                    layout.setBackgroundResource(R.mipmap.a4bg_4pmto5pm);
                                } else if (timeOfDay >= 17 && timeOfDay < 20){
                                    layout.setBackgroundResource(R.mipmap.a5bg_5pmto8pm);
                                } else {
                                    layout.setBackgroundResource(R.mipmap.a6bg_8pmto3am);
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

    // return to the previous page
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
