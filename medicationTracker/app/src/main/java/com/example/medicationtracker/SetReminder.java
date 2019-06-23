package com.example.medicationtracker;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

public class SetReminder extends AppCompatActivity {
  private int timeHour;
  private int timeMinute;

  Button button;
  TimePicker timePicker;

  @RequiresApi(api = Build.VERSION_CODES.M)
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_set_reminder);
    timeHour = timePicker.getHour();
    timeMinute = timePicker.getMinute();
    button = (Button) findViewById(R.id.button_setReminder);

    button.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {

      }
    });
  }


}
