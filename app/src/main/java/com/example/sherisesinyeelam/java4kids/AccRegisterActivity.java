package com.example.sherisesinyeelam.java4kids;

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

public class AccRegisterActivity extends AppCompatActivity {

    private EditText firstname, lastname, age, email, password, pwConfirm;
    private RadioGroup check_gender;
    private RadioButton gender_id;
    private CharSequence gender;
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
        // get selected radio button from radioGroup
        int selectedId = check_gender.getCheckedRadioButtonId();
        // find the radiobutton by returned id
        gender_id = (RadioButton) findViewById(selectedId);

        email = findViewById(R.id.email);
        password = findViewById(R.id.create_password);
        pwConfirm = findViewById(R.id.confirm_password);
        submitButton = findViewById(R.id.submit_button);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO get gender

                return;
            }
        });

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}


// Kuray Ogun (2016). Android Notes 24 : How to add Back Button at Toolbar [online]. available at https://freakycoder.com/android-notes-24-how-to-add-back-button-at-toolbar-941e6577418e [accessed 27/12/2018]