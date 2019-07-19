package com.example.medicationtracker;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.UnsupportedEncodingException;

/********************************************************************
 * Class to define user login with password.
 * It has option to re-set the password with a valid password.
 * This is the default enter activity to prevent unauthorized access
 *******************************************************************/
public class EnterPasswordActivity extends AppCompatActivity {
  public static final String TAG = "EnterPasswordActivity";
  String password;
  String hashSalt;

  /********************************************************************
   * Initializes view for activity
   * @param savedInstanceState
   *******************************************************************/
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_enter_password);
  }

  /********************************************************************
   * Gets and validates user password. If valid redirects to the Home
   * @param view for intent
   *******************************************************************/
  public void enterPassword(View view) throws UnsupportedEncodingException {

    // get existing password
    SharedPreferences settings = getSharedPreferences("PREFS", 0);
    password = settings.getString("password", "");
    hashSalt = settings.getString("pwdSalt", "");
    byte[] salt = hashSalt.getBytes("UTF-8");

    // get user input
    EditText userPassword = findViewById(R.id.userPassword);
    String tempPassword = userPassword.getText().toString();
    char[] securePWD = tempPassword.toCharArray();
    Log.d(TAG, "enterPassword: " + securePWD.toString() + password);
    Log.d(TAG, "enterPassword: " + hashSalt + " " + salt);
    //byte[] salt = Passwords.getNextSalt();
    boolean match = Passwords.isExpectedPassword(securePWD,salt,password.getBytes());

    // if passwords match send intent to main activity
    if (match){
      Intent intent = new Intent(this, MainActivity.class);
      startActivity(intent);
      finish();
      Log.d(TAG, "enterPassword: Password Matched");
    }
    // if passwords don't match send toast
    else {
      Toast.makeText(EnterPasswordActivity.this, "Password does not match!", Toast.LENGTH_SHORT).show();
    }
  }

  /**********************************************************************
   * handles reset password. Check for a valid password before redirecting
   * to create new password page
   * @param view for intent
   *********************************************************************/
  public void reSetPassword(View view){

    // get existing password
    SharedPreferences settings = getSharedPreferences("PREFS", 0);
    password = settings.getString("password", "");

    // get user input
    EditText userPassword = findViewById(R.id.userPassword);
    String tempPassword = userPassword.getText().toString();

    // if passwords match send intent to change password activity
    if (tempPassword.equals(password)){
      Intent intent = new Intent(this, CreatePasswordActivity.class);
      startActivity(intent);
      finish();
      Log.d(TAG, "enterPassword: Password Matched now re-setting password");
    }
    // If the passwords don't match send toast
    else {
      Toast.makeText(EnterPasswordActivity.this, "Password does not match!", Toast.LENGTH_SHORT).show();
    }
  }
}
