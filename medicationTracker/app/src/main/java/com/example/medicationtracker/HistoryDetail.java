package com.example.medicationtracker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class HistoryDetail extends AppCompatActivity {

  private static final String TAG = "HISTORY_DETAIL";

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_history_detail);
    Log.d(TAG, "This is History Detail");
  }
}
