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
 *
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
     *
     * @param view
     */
    public void enterPassword(View view){
        SharedPreferences settings = getSharedPreferences("PREFS", 0);
        password = settings.getString("password", "");
        Intent intent = new Intent(this, MainActivity.class);
        EditText userPassword = findViewById(R.id.userPassword);
        String tempPassword = userPassword.getText().toString();
        if (tempPassword.equals(password)){
            startActivity(intent);
            finish();
            Log.d(TAG, "enterPassword: Password Matched");
        }
        else {
            Toast.makeText(EnterPasswordActivity.this, "Password does not match!", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     *
     * @param view
     */
    public void reSetPassword(View view){
        SharedPreferences settings = getSharedPreferences("PREFS", 0);
        password = settings.getString("password", "");
        Intent intent = new Intent(this, CreatePasswordActivity.class);
        EditText userPassword = findViewById(R.id.userPassword);
        String tempPassword = userPassword.getText().toString();
        if (tempPassword.equals(password)){
            startActivity(intent);
            finish();
            Log.d(TAG, "enterPassword: Password Matched now re-setting password");
        }
        else {
            Toast.makeText(EnterPasswordActivity.this, "Password does not match!", Toast.LENGTH_SHORT).show();
        }

    }

}
