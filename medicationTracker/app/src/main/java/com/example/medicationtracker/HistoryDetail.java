package com.example.medicationtracker;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

public class HistoryDetail extends AppCompatActivity {

  private static final String TAG = "HISTORY_DETAIL";

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_history_detail);
    Log.d(TAG, "This is History Detail");
  }

}
