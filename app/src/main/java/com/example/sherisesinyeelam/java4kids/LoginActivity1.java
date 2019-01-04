package com.example.sherisesinyeelam.java4kids;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;

public class LoginActivity1 extends AppCompatActivity {

    private EditText email, password;
    private Button signin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login1);

        autoChangeBackground();

        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        signin = (Button) findViewById(R.id.signin);

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String mail = email.getText().toString();
                final String pw = password.getText().toString();

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");

                            if(success){

                                String userID = jsonResponse.getString("userID");
                                String firstname = jsonResponse.getString("firstname");
                                String lastname = jsonResponse.getString("lastname");
                                int age = Integer.parseInt(jsonResponse.getString("age"));
                                //String gender = jsonResponse.getString("gender");
                                String email = jsonResponse.getString("email");

                                Intent intent = new Intent(LoginActivity1.this, NavigationDrawer.class);
                                intent.putExtra("login_status", "success");
                                intent.putExtra("userID", userID);
                                intent.putExtra("firstname", firstname);
                                intent.putExtra("lastname", lastname);
                                intent.putExtra("age", age + "");
                                intent.putExtra("email", email);
                                startActivity(intent);

                            } else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity1.this);
                                builder.setMessage("Login fail :(")
                                        .setNegativeButton("Retry", null)
                                        .create()
                                        .show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };

                LoginRequest loginAccRequest = new LoginRequest(mail, pw, responseListener);
                RequestQueue queue = Volley.newRequestQueue(LoginActivity1.this);
                queue.add(loginAccRequest);
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

                        LoginActivity1.super.runOnUiThread(new Runnable() {
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
