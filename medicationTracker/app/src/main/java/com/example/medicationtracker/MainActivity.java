package com.example.medicationtracker;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

  // for debug log
  private static final String TAG = "MAIN";

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    Log.d(TAG, "This is MAIN for Medication Tracker");
  }

  public void getHistory(View view){
    Log.d(TAG, "Creating intent for HistoryMain");
    Intent intent = new Intent(this, HistoryMain.class);
    startActivity(intent);
  }

}
