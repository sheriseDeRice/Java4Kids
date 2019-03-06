package com.example.sherisesinyeelam.java4kids;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.sherisesinyeelam.java4kids.loginregister.AccRegisterActivity;
import com.example.sherisesinyeelam.java4kids.loginregister.LoginRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class StartAppLoginActivity extends AppCompatActivity {

    private Button signin, login_cancel;
    private EditText login_email, login_password;
    private TextView registerLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_app_login);

        login_cancel = (Button) findViewById(R.id.cover_login_cancel);
        login_email = (EditText) findViewById(R.id.cover_login_email);
        login_password = (EditText) findViewById(R.id.cover_login_password);
        signin = (Button) findViewById(R.id.cover_login_signin);
        registerLink = (TextView) findViewById(R.id.login_registerMe);

        login_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent intent = new Intent(StartAppLoginActivity.this, StartAppActivity.class);
                intent.putExtra("login_status", "fail");
                startActivity(intent);
            }
        });

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // button tester
//                AlertDialog.Builder builder = new AlertDialog.Builder(StartAppLoginActivity.this);
//                builder.setMessage("This is working")
//                        .setNegativeButton("Success", null)
//                        .create()
//                        .show();
                final String mail = login_email.getText().toString();
                final String pw = login_password.getText().toString();

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            if(success){
                                //TODO solve this - pass data to another class
                                // todo, password encryption and decryption.
//                                String userID = jsonResponse.getString("userID");
//                                String firstname = jsonResponse.getString("firstname");
//                                String lastname = jsonResponse.getString("lastname");
//                                int age = Integer.parseInt(jsonResponse.getString("age"));
//                                //String gender = jsonResponse.getString("gender");
//                                String email = jsonResponse.getString("email");
                                Intent intent = new Intent(StartAppLoginActivity.this, NavigationDrawer.class);
                                intent.putExtra("login_status", "success");
//                                intent.putExtra("userID", userID);
//                                intent.putExtra("firstname", firstname);
//                                intent.putExtra("lastname", lastname);
//                                intent.putExtra("age", age + "");
//                                intent.putExtra("email", email);
                                startActivity(intent);
                            } else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(StartAppLoginActivity.this);
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
                RequestQueue queue = Volley.newRequestQueue(StartAppLoginActivity.this);
                queue.add(loginAccRequest);
            }
        });

        registerLink.setPaintFlags(registerLink.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        registerLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StartAppLoginActivity.this, AccRegisterActivity.class);
                startActivity(intent);
            }
        });

    }
}
