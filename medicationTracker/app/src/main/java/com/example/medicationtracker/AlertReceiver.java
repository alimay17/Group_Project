package com.example.medicationtracker;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.Fragment;

import java.net.URISyntaxException;

public class AlertReceiver extends BroadcastReceiver {

    public static final String TAG = "AlertReceiver";
    private String title = "Medication Reminder!";
    private String message = "It's time to take to take your ";

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "onReceive: starting");

       String medName = intent.getExtras().getString("medNameKey");
        String fullMessage = message + medName;
        Log.d(TAG, "onReceive: Here's the medName: " + fullMessage);

        Vibrator vibrator = (Vibrator)context.getSystemService(Context.VIBRATOR_SERVICE);
        vibrator.vibrate(2000);

        NotificationHelper notificationHelper = new NotificationHelper(context);
        NotificationCompat.Builder nb = notificationHelper.getChannel1Notification(title, fullMessage); // title, message not in the AlarmManager Video - in channel video
        notificationHelper.getManager().notify(1, nb.build());
    }
}
