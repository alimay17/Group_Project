package com.example.medicationtracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;
import android.view.View;
import android.util.Log;

/**
 * This is the enter password activity. It has the option to re-set the password with a valid
 * password. It will either take the user to the Main activity or create password depending on
 * what button is pushed.
 */
public class EnterPasswordActivity extends AppCompatActivity {
    public static final String TAG = "EnterPasswordActivity";
    String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_password);
    }

    /**
     * This is to check password and go to the MainActivity
     * @param view
     */
    public void enterPassword(View view){
        SharedPreferences settings = getSharedPreferences("PREFS", 0);
        password = settings.getString("password", "");
        Intent intent = new Intent(this, MainActivity.class);
        EditText userPassword = findViewById(R.id.userPassword);
        String tempPassword = userPassword.getText().toString();
        // this if statement
        if (tempPassword.equals(password)){
            startActivity(intent);
            finish();
            Log.d(TAG, "enterPassword: Password Matched");
        }
        // if the passwords don't match
        else {
            Toast.makeText(EnterPasswordActivity.this, "Password does not match!", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * This is for the re-set password. It checks for a correct password and goes to Create Password
     * Activity
     * @param view
     */
    public void reSetPassword(View view){
        SharedPreferences settings = getSharedPreferences("PREFS", 0);
        password = settings.getString("password", "");
        Intent intent = new Intent(this, CreatePasswordActivity.class);
        EditText userPassword = findViewById(R.id.userPassword);
        String tempPassword = userPassword.getText().toString();
       // Password matched and going to change password activity
        if (tempPassword.equals(password)){
            startActivity(intent);
            finish();
            Log.d(TAG, "enterPassword: Password Matched now re-setting password");
        }
        // If the passwords don't match
        else {
            Toast.makeText(EnterPasswordActivity.this, "Password does not match!", Toast.LENGTH_SHORT).show();
        }

    }

}
