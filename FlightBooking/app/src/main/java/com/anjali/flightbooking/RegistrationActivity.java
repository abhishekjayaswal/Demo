package com.anjali.flightbooking;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.anjali.flightbooking.database.Database;
import com.anjali.flightbooking.model.User;

public class RegistrationActivity extends AppCompatActivity implements View.OnClickListener {


    private AppCompatEditText editTextfirstName;
    private AppCompatEditText editTextMiddleName;
    private AppCompatEditText editTextLastName;
    private AppCompatEditText editTextEmailIdReg;
    private AppCompatEditText editTextUserName;
    private AppCompatEditText editTextPasswordReg;
    private AppCompatEditText editTextConfirmPassword;
    private AppCompatEditText editTextMobileNumber;
    private AppCompatButton buttonNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

       editTextfirstName= findViewById(R.id.edit_text_first_name);
       editTextMiddleName= findViewById(R.id.edit_text_middle_name);
        editTextLastName =findViewById(R.id.edit_text_Last_name);
        editTextEmailIdReg=findViewById(R.id.edit_text_Email_id_reg);
        editTextUserName=findViewById(R.id.edit_text_username);
        editTextPasswordReg = findViewById(R.id.edit_text_password_reg);
        editTextConfirmPassword=findViewById(R.id.edit_text_confirm_password);
        editTextMobileNumber=  findViewById(R.id.edit_text_mobile_number);
        buttonNext=findViewById(R.id.button_register);
        buttonNext.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        if (TextUtils.isEmpty(editTextfirstName.getText())) {
            editTextfirstName.setError("Enter First Name");
        } else if (TextUtils.isEmpty(editTextMiddleName.getText())) {
            editTextMiddleName.setError("Enter middle Name");
        } else if (TextUtils.isEmpty(editTextLastName.getText())) {
            editTextLastName.setError("Enter Last Name");
        }else if (TextUtils.isEmpty(editTextEmailIdReg.getText())) {
            editTextEmailIdReg.setError("Enter Email ID");
        }else if (TextUtils.isEmpty(editTextUserName.getText())) {
            editTextUserName.setError("Enter User Name");
        }else if (TextUtils.isEmpty(editTextPasswordReg.getText())) {
            editTextPasswordReg.setError("Enter Password");
        }else if (TextUtils.isEmpty(editTextConfirmPassword.getText())) {
            editTextConfirmPassword.setError("Enter confirm Password");
        }else if (!editTextPasswordReg.getText().toString().equals(editTextConfirmPassword.getText().toString())){
            editTextConfirmPassword.setError("Enter Same Password");
        }
        else if (TextUtils.isEmpty(editTextMobileNumber.getText())) {
            editTextMobileNumber.setError("Enter Mobile Number");
        } else {

            User user = new User();
            user.setFirstName(editTextfirstName.getText().toString());
            user.setMiddleName(editTextMiddleName.getText().toString());
            user.setLastName(editTextLastName.getText().toString());
            user.setUserName(editTextUserName.getText().toString());
            user.setEmailId(editTextEmailIdReg.getText().toString());
            user.setPassword(editTextPasswordReg.getText().toString());
            user.setNumber(editTextMobileNumber.getText().toString());

            Database database= Database.getInstance(this);
            if (database != null) {
                boolean result= database.addUser(user);
                if(result) {
                    AlertDialog.Builder alertDialog1 = new AlertDialog.Builder(
                            RegistrationActivity.this);

                    // Setting Dialog Title
                    alertDialog1.setTitle("Alert Dialog");

                    // Setting Dialog Message
                    alertDialog1.setMessage("Please Login Again");


                    // Setting OK Button
                    alertDialog1.setPositiveButton("OK", new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    });

                    // Showing Alert Message
                    alertDialog1.create().show();
                }else{
                    Toast.makeText(this ,"Something went wrong", Toast.LENGTH_SHORT).show();
                }
            }

        }
    }
}