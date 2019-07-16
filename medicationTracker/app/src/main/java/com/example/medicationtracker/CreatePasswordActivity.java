package com.example.medicationtracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CreatePasswordActivity extends AppCompatActivity {
    public static final String TAG = "CreatePasswordActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creat_password);
    }

    /**
     * This is the application for creating the password. The password is not strong, as it is only
     * on the phone.
     * @param view
     */
    public void createPassword(View view){
        Intent intent = new Intent(this, MainActivity.class);
        EditText newPassword = findViewById(R.id.userPassword);
        EditText newPasswordVerify = findViewById(R.id.userPasswordVerify);
        String newPWD = newPassword.getText().toString();
        String pwdVerify = newPasswordVerify.getText().toString();
        // checking for a new password
        if(newPWD.equals("") || pwdVerify.equals("")){
            //No password
            Toast.makeText(CreatePasswordActivity.this, "No Password Entered", Toast.LENGTH_SHORT).show();
        }
        // New password that matches and is at least 4 characters long
        else if(newPWD.length() < 4) {
                Toast.makeText(this, "Minimum Length is 4 Characters", Toast.LENGTH_SHORT).show();
        }
        // Saving the password
        else{
            if (newPWD.equals(pwdVerify)) {
                // Saving the password to Shared Preferences and going to MainActivity
                SharedPreferences settings = getSharedPreferences("PREFS", 0);
                SharedPreferences.Editor editor = settings.edit();
                editor.putString("password", newPWD);
                editor.apply();
                startActivity(intent);
                finish();
                Log.d(TAG, "createPassword: New Password Set");

            }
            // passwords don't match
            else {
                Toast.makeText(CreatePasswordActivity.this, "Password does not match!", Toast.LENGTH_SHORT).show();
                Log.d(TAG, "createPassword: Passwords Didn't Match");
            }
        }

    }

}
