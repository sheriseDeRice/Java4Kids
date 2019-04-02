package com.example.sherisesinyeelam.java4kids.LoginAndRegister;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.sherisesinyeelam.java4kids.R;

import java.util.Calendar;


public class RegisterActivity extends AppCompatActivity {

    private EditText firstname, lastname, age, email, username, password, pwConfirm;
    private RadioGroup check_gender;
    private RadioButton boy, girl;
    private String gender;
    private CheckBox checkBox;
    private Button submitButton;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        autoChangeBackground();

        //add back button on the toolbar.
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        progressDialog = new ProgressDialog(this);

        firstname = (EditText) findViewById(R.id.first_name);
        lastname = (EditText) findViewById(R.id.last_name);
        age = (EditText) findViewById(R.id.age);
        check_gender = (RadioGroup) findViewById(R.id.gender);
        boy = (RadioButton) findViewById(R.id.boy);
        girl = (RadioButton) findViewById(R.id.girl);
        email = (EditText) findViewById(R.id.email_address);
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.create_password);
        pwConfirm = (EditText) findViewById(R.id.confirm_password);
        checkBox = (CheckBox) findViewById(R.id.checkBox_agreement);
        submitButton = (Button) findViewById(R.id.submit_button);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String pw = password.getText().toString();
                final String pwCon = pwConfirm.getText().toString();

                if(firstname.getText().toString().matches("") || lastname.getText().toString().matches("")||
                        age.getText().toString().matches("") || email.getText().toString().matches("")||
                        username.getText().toString().matches("") || password.getText().toString().matches("")||
                        pwConfirm.getText().toString().matches("")){
                    Toast.makeText(RegisterActivity.this, "Please fill in all the boxes", Toast.LENGTH_SHORT).show();
                } else if (check_gender.getCheckedRadioButtonId() == -1){
                    // no radio button is checked.
                    Toast.makeText(RegisterActivity.this, "Please select your gender", Toast.LENGTH_SHORT).show();
                } else {
                    if (checkBox.isChecked()) {
                        if (pwCon.equals(pw)) {
                            onRegister(view, firstname, lastname, age, email, username, pw);
                        } else {
                            Toast.makeText(RegisterActivity.this, "Please double check you password", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(RegisterActivity.this, "Please tick the checkbox", Toast.LENGTH_SHORT).show();
                    }
                }
                return;
            }
        });
    }

    // gender check
    public void GenderRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        // get selected radio button from radioGroup
        int selectedId = check_gender.getCheckedRadioButtonId();
        // find the radiobutton by returned id
        switch (selectedId) {
            case R.id.boy:
                if (checked) {
                    gender = "boy";
                } else {
                    boy.setChecked(false);
                }
                break;
            case R.id.girl:
                if (checked) {
                    gender = "girl";
                } else {
                    girl.setChecked(false);
                }
                break;
        }
    }

    public void onRegister(View v, EditText firstname, EditText lastname, EditText age, EditText email, EditText username, String pw){
        final String fname = firstname.getText().toString();
        final String lname = lastname.getText().toString();
        final String ages = age.getText().toString();
        final String mail = email.getText().toString();
        final String uname = username.getText().toString();

        progressDialog.setMessage("Registering user...");
        progressDialog.show();

        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
                try {
                    JSONObject jsonObject = new JSONObject(response);

                    Toast.makeText(RegisterActivity.this, jsonObject.getString("message"), Toast.LENGTH_SHORT).show();

                    if (!jsonObject.getBoolean("error")) {
                        onSupportNavigateUp();
                    } else {
                        AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                        builder.setMessage("Register fail :(")
                                .setNegativeButton("Retry", null)
                                .create()
                                .show();
                    }
                } catch (JSONException e) { e.printStackTrace(); }
            }
        };
        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.hide();
                Toast.makeText(RegisterActivity.this, error.getMessage(), Toast.LENGTH_LONG).show();;
               // Log.e(RegisterActivity.class.getSimpleName(), "Error at response : " + error.getMessage());
            }
        };
        RegisterRequest registerRequest = new RegisterRequest(fname, lname, ages, gender, mail, uname, pw, responseListener, errorListener);
        RequestHandler.getInstance(RegisterActivity.this).addToRequestQueue(registerRequest);
    }

    // auto change background according to the time (day/night). These are default background.
    public void autoChangeBackground(){

        final android.support.constraint.ConstraintLayout layout = (android.support.constraint.ConstraintLayout) findViewById(R.id.registerPage_default_bg);

        Thread t = new Thread() {
            @Override
            public void run() {
                try {
                    while (!isInterrupted()) {

                        RegisterActivity.super.runOnUiThread(new Runnable() {
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

    // return to the previous page
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}


// Kuray Ogun (2016). Android Notes 24 : How to add Back Button at Toolbar [online]. available at https://freakycoder.com/android-notes-24-how-to-add-back-button-at-toolbar-941e6577418e [accessed 27/12/2018]
// Tonikami TV (2016). Android Studio Tutorial - NEW Login Register #3 - Server & Database [online]. https://www.youtube.com/watch?v=JQXfIidfFMo [ access 03/01/2019]