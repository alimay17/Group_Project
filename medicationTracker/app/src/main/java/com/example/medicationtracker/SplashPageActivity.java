package com.example.medicationtracker;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

/********************************************************************
 * Activity class to determine whether a user is new or returning
 * This is the entry point of the application
 * @author Rich Terry, Alice Smith, Eric Mott
 * @version 1.0
 *******************************************************************/
public class SplashPageActivity extends AppCompatActivity {
  String password;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_splash_page);

    //checks for user in shared preferences
    SharedPreferences settings = getSharedPreferences("PREFS", 0);
    password = settings.getString("password", "");

    Handler handler = new Handler();
    handler.postDelayed(new Runnable(){
      @Override
      public void run() {

        // if no user exists send to create password
        if (password.equals("")) {
          // If no password exist
          Intent intent = new Intent(getApplicationContext(), CreatePasswordActivity.class);
          startActivity(intent);
          finish();
        }

        // if user exists send to login activity
        else {
          Intent intent = new Intent(getApplicationContext(), EnterPasswordActivity.class);
          startActivity(intent);
          finish();
        }
      }
    }, 2000);
  }
}
