package com.example.sherisesinyeelam.java4kids.loginregister;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import org.json.JSONException;
import org.json.JSONObject;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.example.sherisesinyeelam.java4kids.NavigationDrawer;
import com.example.sherisesinyeelam.java4kids.R;


public class AccRegisterActivity extends AppCompatActivity {

    private RadioGroup check_gender;
    private RadioButton boy, girl;
    private String gender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acc_register);

        //Allowing to access the network
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        //add back button on the toolbar.
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final EditText firstname = (EditText) findViewById(R.id.first_name);
        final EditText lastname = (EditText) findViewById(R.id.last_name);
        final EditText age = (EditText) findViewById(R.id.age);

        check_gender = (RadioGroup) findViewById(R.id.gender);
        boy = (RadioButton) findViewById(R.id.boy);
        girl = (RadioButton) findViewById(R.id.girl);

        final EditText email = (EditText) findViewById(R.id.email_address);
        final EditText password = (EditText) findViewById(R.id.create_password);
        final EditText pwConfirm = (EditText) findViewById(R.id.confirm_password);
        final Button submitButton = findViewById(R.id.submit_button);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String fname = firstname.getText().toString();
                final String lname = lastname.getText().toString();
                final int ages = Integer.parseInt(age.getText().toString());
                final String mail = email.getText().toString();
                final String pw = password.getText().toString();
                final String pwCon = pwConfirm.getText().toString();

                if(pwCon.equals(pw)) {
                    // used to test button
//                    AlertDialog.Builder builder = new AlertDialog.Builder(AccRegisterActivity.this);
//                    builder.setMessage("Button clicked")
//                            .create()
//                            .show();

                    Response.Listener<String> responseListener = new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            //Log.e(AccRegisterActivity.class.getSimpleName(), "in onResponse" + response.toString());
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                boolean success = jsonObject.getBoolean("success");
                                if (success) {
                                    AlertDialog.Builder builder = new AlertDialog.Builder(AccRegisterActivity.this);
                                    builder.setMessage("Register Successful!")
                                            .setPositiveButton("Login", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {
                                                    AlertDialog.Builder builder = new AlertDialog.Builder(AccRegisterActivity.this);
                                                    builder.setMessage("Login success").create().show();
                                                    Intent intent = new Intent(AccRegisterActivity.this, NavigationDrawer.class);
//                                                    // passing the login status to the main page.
//                                                    intent.putExtra("login_status", "success");
//                                                    intent.putExtra("firstname", fname);
//                                                    intent.putExtra("lastname", lname);
//                                                    intent.putExtra("age", ages);
//                                                    intent.putExtra("email", mail);
                                                    startActivity(intent);
                                                }
                                            })
                                            .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {
                                                    //  Action for 'Cancel' Button
                                                    dialog.cancel();
                                                }
                                            })
                                            .create()
                                            .show();

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

                    Response.ErrorListener errorListener = new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.e(AccRegisterActivity.class.getSimpleName(), "Error at response : " + error.getMessage());

                        }
                    };

                    AccRegisterRequest accRegisterRequest = new AccRegisterRequest(fname, lname, ages, gender, mail, pw, responseListener, errorListener);
                    RequestQueue queue = Volley.newRequestQueue(AccRegisterActivity.this);
                    queue.add(accRegisterRequest);
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

    // return to the previous page
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}


// Kuray Ogun (2016). Android Notes 24 : How to add Back Button at Toolbar [online]. available at https://freakycoder.com/android-notes-24-how-to-add-back-button-at-toolbar-941e6577418e [accessed 27/12/2018]
// Tonikami TV (2016). Android Studio Tutorial - NEW Login Register #3 - Server & Database [online]. https://www.youtube.com/watch?v=JQXfIidfFMo [ access 03/01/2019]