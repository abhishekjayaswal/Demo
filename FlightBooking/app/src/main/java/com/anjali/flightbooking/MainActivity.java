package com.anjali.flightbooking;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.anjali.flightbooking.database.Database;
import com.anjali.flightbooking.model.User;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private AppCompatEditText editTextEmailId;
    private AppCompatEditText editTextPassword;
    private AppCompatButton buttonSignIn;
    private AppCompatButton buttonSignUp;
    Database db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTextEmailId = findViewById(R.id.edit_text_email_id);
        editTextPassword = findViewById(R.id.edit_text_password);
        buttonSignIn = findViewById(R.id.button_sing_in);
        buttonSignUp = findViewById(R.id.button_sing_up);

        buttonSignIn.setOnClickListener(this);
        buttonSignUp.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_sing_in:
                 processSignIn();

                break;

            case R.id.button_sing_up:
                processSignUp();
                break;


        }
    }


    private void processSignIn() {

        if (TextUtils.isEmpty(editTextEmailId.getText()) && TextUtils.isEmpty(editTextPassword.getText())) {

            Toast.makeText(this, "please enter details", Toast.LENGTH_SHORT).show();

        } else if (TextUtils.isEmpty(editTextEmailId.getText())) {
            Toast.makeText(this, "please enter Email Id", Toast.LENGTH_SHORT).show();

        } else if (TextUtils.isEmpty(editTextPassword.getText())) {

            Toast.makeText(this, "please enter Password", Toast.LENGTH_SHORT).show();
        } else {

            User user=Database.getInstance(this).getUserDetails(editTextEmailId.getText().toString(), editTextPassword.getText().toString());

              if ( user!= null && user.getEmailId().equalsIgnoreCase(editTextEmailId.getText().toString())) {
                    Intent intent= new Intent(this, HomeActivity.class);
                    startActivity(intent);

                } else {
                    Toast.makeText(this, "please enter correct details", Toast.LENGTH_SHORT).show();

            }
        }
    }

    private void processSignUp() {

        Intent intent= new Intent(this, RegistrationActivity.class);
        startActivity(intent);
    }
}