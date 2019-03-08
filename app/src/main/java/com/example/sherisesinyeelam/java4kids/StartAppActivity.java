package com.example.sherisesinyeelam.java4kids;

import android.content.Intent;
import android.graphics.Paint;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.sherisesinyeelam.java4kids.loginregister.AccRegisterActivity;
import com.example.sherisesinyeelam.java4kids.loginregister.LoginRequest;
import com.example.sherisesinyeelam.java4kids.mainpages.UserProfileActivity;

import org.json.JSONException;
import org.json.JSONObject;

public class StartAppActivity extends AppCompatActivity {

    private Button startApp, login;
    TextView appVersion;
    LinearLayout startApp_default, startApp_login;

    private Button signin, login_cancel;
    private EditText login_email, login_password;
    private TextView registerLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_app);

        appVersion = (TextView) findViewById(R.id.app_version);
        startApp = (Button) findViewById(R.id.start_app);
        login = (Button) findViewById(R.id.cover_login);

        startApp_default = (LinearLayout) findViewById(R.id.start_app_default);
        startApp_login = (LinearLayout) findViewById(R.id.start_app_login);

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
//                Intent intent = new Intent(StartAppActivity.this, StartAppLoginActivity.class);
//                startActivity(intent);

                startApp_default.setVisibility(View.GONE);
                startApp_login.setVisibility(View.VISIBLE);
            }
        });

        startAppLoginActivity(savedInstanceState);

    }

    public void startAppLoginActivity(final Bundle bundle){
        login_cancel = (Button) findViewById(R.id.cover_login_cancel);
        login_email = (EditText) findViewById(R.id.cover_login_email);
        login_password = (EditText) findViewById(R.id.cover_login_password);
        signin = (Button) findViewById(R.id.cover_login_signin);
        registerLink = (TextView) findViewById(R.id.cover_login_registerMe);

        login_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startApp_default.setVisibility(View.VISIBLE);
                startApp_login.setVisibility(View.GONE);
            }
        });

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String mail = login_email.getText().toString();
                final String pw = login_password.getText().toString();

                if(mail.length() == 0 || pw.length() == 0){
                    Toast.makeText(StartAppActivity.this, "The box is empty, try again.", Toast.LENGTH_SHORT).show();

                } else {
                    Response.Listener<String> responseListener = new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try{
                                JSONObject jsonResponse = new JSONObject(response);
                                boolean success = jsonResponse.getBoolean("success");
                                if(success){
                                    Intent intent = new Intent(StartAppActivity.this, NavigationDrawer.class);
                                    intent.putExtra("login_status", "success");
//                                intent.putExtra("userID", userID);
//                                intent.putExtra("firstname", firstname);
//                                intent.putExtra("lastname", lastname);
//                                intent.putExtra("age", age + "");
//                                intent.putExtra("email", email);
                                    startActivity(intent);

                                    getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, new UserProfileActivity()).commit();
                                    bundle.putString("login_status", "success");

                                } else {
                                    AlertDialog.Builder builder = new AlertDialog.Builder(StartAppActivity.this);
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
                    RequestQueue queue = Volley.newRequestQueue(StartAppActivity.this);
                    queue.add(loginAccRequest);
                }
            }
        });

        registerLink.setPaintFlags(registerLink.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        registerLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StartAppActivity.this, AccRegisterActivity.class);
                startActivity(intent);
            }
        });
    }

}
