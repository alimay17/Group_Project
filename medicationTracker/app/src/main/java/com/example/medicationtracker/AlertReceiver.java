package com.example.medicationtracker;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Vibrator;
import android.util.Log;

import androidx.core.app.NotificationCompat;

/****************************************************************
 * a class to handle alarm alerts
 ***************************************************************/
public class AlertReceiver extends BroadcastReceiver {

    public static final String TAG = "AlertReceiver";
    private String title = "Medication Reminder!";
    private String message = "It's time to take to take your ";

    @Override
    public void onReceive(Context context, Intent intent) {

      // get intent with medication name
      Log.d(TAG, "onReceive: starting");
      String medName = intent.getExtras().getString("medNameKey");
      String fullMessage = message + medName;
      Log.d(TAG, "onReceive: Here's the medName: " + fullMessage);

      // set vibration
      Vibrator vibrator = (Vibrator)context.getSystemService(Context.VIBRATOR_SERVICE);
      vibrator.vibrate(2000);

      // send notification
      NotificationHelper notificationHelper = new NotificationHelper(context);
      NotificationCompat.Builder nb = notificationHelper.getChannel1Notification(title, fullMessage);
      notificationHelper.getManager().notify(1, nb.build());
    }
}
