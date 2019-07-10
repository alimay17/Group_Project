package com.example.medicationtracker;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import androidx.core.app.NotificationCompat;

public class AlertReceiver extends BroadcastReceiver {

    public static final String TAG = "AlertReceiver";
    private String title = "Medication Reminder!";
    private String message = "It's time to take your medication";
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "onReceive: starting");
        NotificationHelper notificationHelper = new NotificationHelper(context);
        NotificationCompat.Builder nb = notificationHelper.getChannel1Notification(title, message); // title, message not in the AlarmManager Video - in channel video
        notificationHelper.getManager().notify(1, nb.build());  // does the 1 need to be the alarmID???
    }
}
