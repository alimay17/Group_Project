package com.example.medicationtracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Base64;

/**
 * A class to recover passwords in the app. It is weak for now, but it gives
 * the option for password recovery
 */
public class ForgotPassword extends AppCompatActivity {
    public static final String TAG = "ForgotPasswordActivity";
    String answer;
    String hashSalt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
    }
    public void ForgotPassword(View view){
        // get existing answer
        SharedPreferences settings = getSharedPreferences("PREFS", 0);
        answer = settings.getString("answer", "");

        // decode answer for use as byte
        byte[] decodeAnswer = Base64.getDecoder().decode(answer);
        hashSalt = settings.getString("pwdSalt", "");
        byte[] salt = Base64.getDecoder().decode(hashSalt);

        // get input
        EditText forgotAnswer = findViewById(R.id.forgotAnswer);
        String userAnswer = forgotAnswer.getText().toString();

        // check for empty
        if (userAnswer.isEmpty()){
            Toast.makeText(this, "Must Enter Answer", Toast.LENGTH_SHORT).show();
            return;
        }

        char[] secureAnswer = userAnswer.toCharArray();

        // if answers match
        boolean match = Passwords.isExpectedPassword(secureAnswer,salt,decodeAnswer);

        // if answer match send intent to create password
        if (match){
            Intent intent = new Intent(this, CreatePasswordActivity.class);
            startActivity(intent);
            finish();
            Log.d(TAG, "ForgotPassword: Answers Matched");
        }
        // If the answers don't match send toast
        else {
            Toast.makeText(this, "Answers Don't Match", Toast.LENGTH_SHORT).show();
        }
    }

}
