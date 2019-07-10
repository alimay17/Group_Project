package com.example.medicationtracker;

import android.annotation.TargetApi;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.DialogFragment;
import org.w3c.dom.Text;

import java.text.DateFormat;
import java.util.Random;

public class SetAlarm extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener {
    public static final String TAG = "SETALARM";
    public static final int SET_ALARM_REQUEST_CODE = 5;
    private int mAlarmID;  // ID used to set/delete alarm
    private int timeHour;
    private int timeMinute;
    private TextView mTextView;

    private String timeText; // alarm time as a string

    private Button button_set_time;

    // these variables what is passed to the notification - they need to be replaced with the medication
    private String notificationTitle = "It's time to take your medication";  // need to look at passing this from newMed to notification
    private String medName = "Need to pass medication to this variable";  // need to look at passing this from newMed to notification
//    private Button buttonChannel1; // temp                                                                *** temp ***

    private NotificationHelper mNotificationHelper;

    Calendar c;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_alarm);
        Log.d(TAG, "onCreate: landed on SetAlarm");

//        setTitle("Set Reminder Time");
        mTextView = findViewById(R.id.textView_time);  //                                                   Do I need here???
        createAlarmID();

        Log.d(TAG, "onCreate: Creating intent");
        Intent incomingIntent = getIntent();
        String medName = incomingIntent.getStringExtra("medName");
        String medDose = incomingIntent.getStringExtra("medDose");

        Button button_set_time = findViewById(R.id.button_set_time);
        mNotificationHelper = new NotificationHelper(this);

        Log.d(TAG, "onCreate: calling TimePicker");
        DialogFragment timePicker = new TimePickerFragment();
        timePicker.show(getSupportFragmentManager(), "time picker");

        Intent resultIntent = new Intent();
        resultIntent.putExtra("alarmID", mAlarmID);  // pass alarmID back thru resultIntent
        resultIntent.putExtra("mTextView", mTextView.getText().toString());

        setResult(RESULT_OK, resultIntent);

//        finish();  //  ***************************** this is being called before time selected in timePicker **************

        button_set_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /*Log.d(TAG, "onCreate: calling TimePicker");
                DialogFragment timePicker = new TimePickerFragment();
                timePicker.show(getSupportFragmentManager(), "time picker");

//                sendOnChannel1(notificationTitle, medName); // not needed??

                Intent resultIntent = new Intent();
                resultIntent.putExtra("alarmID", mAlarmID);

                setResult(RESULT_OK, resultIntent);*/
                Log.d(TAG, "onClick: ******************** Going back to NEWMED*******************************************");

                finish();  //  ***************************** this is being called before time selected in timePicker **************
//                finishActivity(SET_ALARM_REQUEST_CODE); // doesn't work
            }
        });
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        Log.d(TAG, "onTimeSet: loading TimePicker into Calendar C");
        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, hourOfDay);
        c.set(Calendar.MINUTE, minute);
        c.set(Calendar.SECOND, 0);

        updateTimeText(c);
        startAlarm(c);
    }

    /*public void sendOnChannel1(String title, String message){
        NotificationCompat.Builder nb = mNotificationHelper.getChannel1Notification(title, message);
        mNotificationHelper.getManager().notify(1, nb.build()); // the 1 an id for channel1 - should this be alarmID???
    }*/

    // creates the String showing the time the alarm was set for
    private void updateTimeText(Calendar c) {
        String timeText = "Alarm set for: ";
        timeText += DateFormat.getTimeInstance(DateFormat.SHORT).format(c.getTime());
        timeText += ", ID: ";  // ********************************************************************** Delete - test info
        timeText += mAlarmID;  // ********************************************************************** Delete - test info
        mTextView.setText(timeText);
    }

    // starts the alarm
    private void startAlarm(Calendar c){
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, AlertReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, mAlarmID, intent, 0);

        // if time set in past, add a day to set in future
        if (c.before(Calendar.getInstance())){
            Log.d(TAG, "startAlarm: time in past adjustment to future");
            c.add(Calendar.DATE, 1); // add a day
        }

        alarmManager.setExact(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), pendingIntent); // original alarm - non-repeating
//        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);  // repeats daily
//        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), AlarmManager.INTERVAL_FIFTEEN_MINUTES, pendingIntent);  // repeats 15 minutes
    }

    private void cancelAlarm(){
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, AlertReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, mAlarmID, intent, 0);

        alarmManager.cancel(pendingIntent);
    }
    // create a unique alarmID
    public void createAlarmID() {
        Log.d(TAG, "getAlarmID: create/return alarmID");
        int mMin = 100; // min of alarmID range
        int mMax = 999; // max of alarmID range
        Random r = new Random();
        mAlarmID = (r.nextInt(mMax - mMin + 1) + mMin); // create random number from 100 - 999
    }

    // temp method to clear all alarms set during testing
    public void cancelAllAlarms(View view){
        for (int i = 100; i < 1000; i++){
            AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
            Intent intent = new Intent(this, AlertReceiver.class);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(this, i, intent, 0);

            alarmManager.cancel(pendingIntent);
        }
        finishActivity(SET_ALARM_REQUEST_CODE); // doesn't work
        finish();
    }

    /*public void createAlarmID(){
        mAlarmID = c.get(Calendar.YEAR);
        mAlarmID += c.get(Calendar.DAY_OF_YEAR);
        mAlarmID += c.get(Calendar.HOUR_OF_DAY);
        mAlarmID += c.get(Calendar.MINUTE);
    }*/

}
