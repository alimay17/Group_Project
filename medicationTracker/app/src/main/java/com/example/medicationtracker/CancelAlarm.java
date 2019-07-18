package com.example.medicationtracker;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;
import com.example.medicationtracker.AlertReceiver;

import static androidx.core.content.ContextCompat.getSystemService;

public class CancelAlarm extends AppCompatActivity {
  //public class NewMed extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener
  public static final String TAG = "Cancel_Alarm";

  public void cancelAlarm(int alarmID){
    Log.d(TAG, "cancelAlarm: starting cancelAlarm");
    Log.d(TAG, "cancelAlarm: alarmID = " + alarmID);
    AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
    Intent intent = new Intent(this, AlertReceiver.class);
    PendingIntent pendingIntent = PendingIntent.getBroadcast(this, alarmID, intent, 0);

    alarmManager.cancel(pendingIntent);
  }
}
