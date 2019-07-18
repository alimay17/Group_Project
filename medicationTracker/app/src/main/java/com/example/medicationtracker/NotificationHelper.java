package com.example.medicationtracker;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.ContextWrapper;
import android.os.Build;
import android.util.Log;
import androidx.core.app.NotificationCompat;

/********************************************************************
 * Class to define notifications for the app
 *******************************************************************/
public class NotificationHelper extends ContextWrapper {
  public static final String TAG = "NotificationHelper";
  public static final String channel1ID = "channel1ID";

  // this is what the user sees in the user settings
  public static final String channel1Name = "Medication Reminder";

  private NotificationManager mManager;

  // constructor
  public NotificationHelper(Context base) {
    super(base);
    if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
      createChannels();
    }
  }

  // create notification settings
  @TargetApi(Build.VERSION_CODES.O)
  public void createChannels(){
    Log.d(TAG, "createChannels: In NotificationHelper");
    NotificationChannel channel1 = new NotificationChannel(channel1ID, channel1Name, NotificationManager.IMPORTANCE_DEFAULT);
    channel1.enableLights(true);
    channel1.enableVibration(true);
    channel1.setLightColor(R.color.colorPrimary);
    channel1.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);

    getManager().createNotificationChannel(channel1);
  }

  // set up management
  public NotificationManager getManager(){
    if (mManager == null) {
      Log.d(TAG, "getManager: mManager was null, creating new mManager");
      mManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
    }
    return mManager;
  }

  // build notifications
  public NotificationCompat.Builder getChannel1Notification(String title, String message){
    Log.d(TAG, "getChannel1Notification: returning new NotificationCompat.Builder");
    return new NotificationCompat.Builder(getApplicationContext(), channel1ID)
            .setContentTitle(title)
            .setContentText(message)
            .setSmallIcon(R.drawable.ic_med);
  }
}
