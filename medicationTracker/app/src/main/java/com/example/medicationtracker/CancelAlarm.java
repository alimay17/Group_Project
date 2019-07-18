package com.example.medicationtracker;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

/****************************************************************
 * A class to cancel alarms
 ***************************************************************/
public class CancelAlarm extends AppCompatActivity {
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
