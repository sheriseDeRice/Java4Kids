package com.example.sherisesinyeelam.java4kids;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class AccRegisterActivity extends AppCompatActivity {

    private EditText firstname, lastname, age, email, password, pwConfirm;
    private RadioGroup check_gender;
    private RadioButton boy, girl;
    private String gender;
    private Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acc_register);

        //add back button on the toolbar.
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        firstname = findViewById(R.id.first_name);
        lastname = findViewById(R.id.last_name);
        age = findViewById(R.id.age);

        check_gender = (RadioGroup) findViewById(R.id.gender);
        boy = (RadioButton) findViewById(R.id.boy);
        girl = (RadioButton) findViewById(R.id.girl);

        email = findViewById(R.id.email);
        password = findViewById(R.id.create_password);
        pwConfirm = findViewById(R.id.confirm_password);
        submitButton = findViewById(R.id.submit_button);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //TODO get gender

                try{
                    final String fname = (String) firstname.getText().toString();
                    final String lname = (String) lastname.getText().toString();
                    final String ages = (String) age.getText().toString();
                    final String mail = (String) email.getText().toString();
                    final String pw = (String) password.getText().toString();
                    final String pwCon = (String) pwConfirm.getText().toString();

                    if(pw.equals(pwCon)) {
                        Response.Listener<String> responseListener = new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                try {
                                    JSONObject jsonObject = new JSONObject(response);
                                    Boolean success = jsonObject.getBoolean("success");

                                    if (success) {
                                        finish();
                                        Intent intent = new Intent(AccRegisterActivity.this, LoginActivity.class);
                                        startActivity(intent);

                                    } else {
                                        AlertDialog.Builder builder = new AlertDialog.Builder(AccRegisterActivity.this);
                                        builder.setMessage("Register fail :(")
                                                .setNegativeButton("Retry", null)
                                                .create()
                                                .show();
                                    }

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        };

                        RegisterAccRequest registerAccRequest = new RegisterAccRequest(fname, lname, ages, mail, pw, responseListener);
                        RequestQueue queue = Volley.newRequestQueue(AccRegisterActivity.this);
                        queue.add(registerAccRequest);
                    }
                } catch (Exception e){}

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

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}


// Kuray Ogun (2016). Android Notes 24 : How to add Back Button at Toolbar [online]. available at https://freakycoder.com/android-notes-24-how-to-add-back-button-at-toolbar-941e6577418e [accessed 27/12/2018]