package com.example.medicationtracker;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Base64;

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
  public void enterPassword(View view) {

    // get existing password
    SharedPreferences settings = getSharedPreferences("PREFS", 0);
    password = settings.getString("password", "");
    hashSalt = settings.getString("pwdSalt", "");

    // decode from shared preferences back to byte to verify hash and salt
    byte[] salt = Base64.getDecoder().decode(hashSalt);
    byte[] decodePWD = Base64.getDecoder().decode(password);


    // get user input
    EditText userPassword = findViewById(R.id.userPassword);
    String tempPassword = userPassword.getText().toString();

    if(tempPassword.isEmpty()){
      Toast.makeText(EnterPasswordActivity.this, "Please enter your password", Toast.LENGTH_SHORT).show();
      return;
    }

    char[] securePWD = tempPassword.toCharArray();

    // compare passwords
    boolean match = Passwords.isExpectedPassword(securePWD,salt,decodePWD);

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

    // get shared preferences
    SharedPreferences settings = getSharedPreferences("PREFS", 0);
    password = settings.getString("password", "");
    hashSalt = settings.getString("pwdSalt", "");

    Log.d(TAG, "reSetPassword: got shared preferences");

    // get user input
    EditText userPassword = findViewById(R.id.userPassword);
    String tempPassword = userPassword.getText().toString();
    if(tempPassword.isEmpty()){
      Toast.makeText(EnterPasswordActivity.this, "Please enter your password", Toast.LENGTH_SHORT).show();
      return;
    }
    char[] securePWD = tempPassword.toCharArray();
    Log.d(TAG, "reSetPassword: " + tempPassword);

    Log.d(TAG, "reSetPassword: got user input");

    // decode from shared preferences back to byte to verify hash and salt
    byte[] salt = Base64.getDecoder().decode(hashSalt);
    byte[] decodePWD = Base64.getDecoder().decode(password);
    Log.d(TAG, "reSetPassword: decoded hash");


    // compare passwords
    boolean match = Passwords.isExpectedPassword(securePWD,salt,decodePWD);
    Log.d(TAG, "reSetPassword: verifying password");

    // if passwords match send intent to main activity
    if (match){
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
