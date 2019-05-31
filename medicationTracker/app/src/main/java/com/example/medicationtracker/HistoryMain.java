package com.example.medicationtracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class HistoryMain extends AppCompatActivity {
  private static final String TAG = "HISTORY_MAIN";

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_history_main);
    Log.d(TAG, "This is History MAIN");
  }

  public void getHistoryDetail(View view) {
    Log.d(TAG, "Creating intent for HistoryDetail");
    Intent intent = new Intent(this, HistoryDetail.class);
    startActivity(intent);
  }
}
