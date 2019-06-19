package com.example.medicationtracker;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class HistoryDetail extends AppCompatActivity {

  private static final String TAG = "HISTORY_DETAIL";

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_history_detail);
    Log.d(TAG, "This is History Detail");
    getIncomingIntent();
  }

  private void getIncomingIntent() {
    Log.d(TAG, "getIncomingIntent: Getting intent");

    if(getIntent().hasExtra("medication")) {
      Log.d(TAG, "getIncomingIntent: Got intent");
      String medName = getIntent().getStringExtra("medication");
      displayImage(medName);

    }
    else {
      Log.d(TAG, "getIncomingIntent: no Intent");
    }
  }

  private void displayImage(String medName) {
    Log.d(TAG, "displayImage: setting med to display");
    TextView name = findViewById(R.id.medDetail);
    name.setText("Your Selected Medication: " + medName);
  }

}
