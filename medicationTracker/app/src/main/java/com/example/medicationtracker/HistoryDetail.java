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
      String dose = getIntent().getStringExtra("dose");
      displayDetail(medName, dose);

    }
    else {
      Log.d(TAG, "getIncomingIntent: no Intent");
    }
  }

  private void displayDetail(String medName, String medDose) {
    Log.d(TAG, "displayImage: setting med to display");
    TextView name = findViewById(R.id.medDetail);
    TextView dose = findViewById(R.id.dose);
    name.setText(String.format(medName));
    dose.setText(String.format("Dosage: %s", medDose));

  }

}
