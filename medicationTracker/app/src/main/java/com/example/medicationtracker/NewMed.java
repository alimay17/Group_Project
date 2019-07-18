package com.example.medicationtracker;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import java.text.DateFormat;

/******************************************************************
 * activity class to create a new medication to add to database
 * includes functions for alarm reminder creation
 *****************************************************************/
public class NewMed extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener {

  public static final String TAG = "NEW_MED";
  public static final String EXTRA_REPLY = "com.example.android.wordlistsql.REPLY";

  private EditText medName;
  private EditText medDose;
  private TextView mTextView;  // time alarm is set for
  private int alarmID = 0;  // unique alarm ID to set/cancel alarm in system == mAlarmID
//  private String mAlarmID; // unique alarm ID in String format
//  private Button buttonRemind;

  /******************************************************************
   * initialize view, gets user input and sets click listener for alarms
   * and save buttons
   * @param savedInstanceState
   *****************************************************************/
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_new_med);

    Log.d(TAG, "onCreate: this is add new med");

    // get user input
    medName = findViewById(R.id.name);
    medDose = findViewById(R.id.dose);
    mTextView = findViewById(R.id.textView_remindTime);

    // set alarm button with click listener
    Button buttonRemind = (Button) findViewById(R.id.button_setReminder);
    buttonRemind.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        if (medName.getText().toString().equals("")) {
          Toast.makeText(NewMed.this, "Please add a medication", Toast.LENGTH_SHORT).show();
        } else {
          Log.d(TAG, "onClick: Creating Intent before calling SetAlarm");

          // creates the unique ID for each alarm - used to add/delete in system
          createAlarmID();

          // set time fragment
          DialogFragment timePicker = new TimePickerFragment();
          timePicker.show(getSupportFragmentManager(), "time picker");

//          updateTimeText(mCalendar);  // update the alarm time on page
//          startAlarm(mCalendar);  // set the alarm
        }
      }
    });

    // cancels current alarm in NewMed
    Button buttonCancel = findViewById(R.id.button_cancel);
    buttonCancel.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        // call local cancelAlarm
        if (alarmID == 0){
          Toast.makeText(NewMed.this, "No alarm to cancel", Toast.LENGTH_SHORT).show();
        } else {
          Log.d(TAG, "onClick: Canceling alarm");
          cancelAlarm(alarmID);
          alarmID = 0;
          Toast.makeText(NewMed.this, "Alarm canceled", Toast.LENGTH_SHORT).show();
        }

        /*
        // call cancelAlarm from CancelAlarm class
        if (alarmID == 0){
          Toast.makeText(NewMed.this, "No alarm to cancel", Toast.LENGTH_SHORT).show();
        } else {
          Log.d(TAG, "onClick: Canceling alarm");
          CancelAlarm externalCancelAlarm = new CancelAlarm();
          Log.d(TAG, "onClick: calling cancelAlarm from CancelAlarm");
          externalCancelAlarm.cancelAlarm(alarmID);
          Log.d(TAG, "onClick: resetting alarmID to zero");
          alarmID = 0;
          Toast.makeText(NewMed.this, "Alarm canceled", Toast.LENGTH_SHORT).show();
        } */
      }
    });

    // message for alarm
    mTextView.setText("Alarm not set");

    // save med button
    final Button button = findViewById(R.id.button_save);
    button.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {

        // create intent with user input
        Intent replyIntent = new Intent();
        if(TextUtils.isEmpty(medName.getText())) {
          setResult(RESULT_CANCELED, replyIntent);
        } else {
          Log.d(TAG, "onClick: getting user input");
          String med = medName.getText().toString();
          String dose = medDose.getText().toString();

          Log.d(TAG, "onClick: medName: " + med + " medDose: " + dose);

          replyIntent.putExtra(EXTRA_REPLY, med);
          replyIntent.putExtra("dose", dose);
          setResult(RESULT_OK, replyIntent);
        }
        // returns to caller activity to add to db
        Log.d(TAG, "onClick: added new med");
        finish();
      }
    });
  }

  /*****************************************************************
   * create unique alarm id
   ****************************************************************/
  public void createAlarmID() {
    Log.d(TAG, "getAlarmID: create/return alarmID");
    // unique ID using day/timestamp
    Calendar calAlarmID = Calendar.getInstance();
//    int yearID = calAlarmID.get(Calendar.YEAR);
    int dayID = calAlarmID.get(Calendar.DAY_OF_YEAR);
    int hourID = calAlarmID.get(Calendar.HOUR_OF_DAY);
    int minuteID = calAlarmID.get(Calendar.MINUTE);
    int secondID = calAlarmID.get(Calendar.SECOND);

//    int singleDigitYear = (yearID % 10);
//    String alarmIdBuilder = Integer.toString(yearID - 2000);
//    String alarmIdBuilder = Integer.toString(singleDigitYear);
//    alarmIdBuilder += Integer.toString(dayID);
    String alarmIdBuilder = Integer.toString(dayID);
    alarmIdBuilder += Integer.toString(hourID);
    alarmIdBuilder += Integer.toString(minuteID);
    alarmIdBuilder += Integer.toString(secondID);

    alarmID = Integer.parseInt(alarmIdBuilder);  // alarmID as an Integer
//    mAlarmID = alarmIdBuilder;
//    Log.d(TAG, "createAlarmID: alarmID Created: " + alarmID);
    Log.d(TAG, "createAlarmID: alarmIdBuilder Created: " + alarmIdBuilder);
  }

  /*******************************************************************
   * takes time sent from timePicker and assigns it to a Calendar
   * Calendar needed to set the alarm
   * @param view time picker fragment
   * @param hourOfDay hour
   * @param minute minute
   ******************************************************************/
  @Override
  public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
    Log.d(TAG, "onTimeSet: loading TimePicker values into Calendar C");
    Calendar c = Calendar.getInstance();
    c.set(Calendar.HOUR_OF_DAY, hourOfDay);
    c.set(Calendar.MINUTE, minute);
    c.set(Calendar.SECOND, 0);

        updateTimeText(c);  // update the alarm time on page
        startAlarm(c);  // set the alarm
  }

  /*******************************************************************
   * creates the String showing the time the alarm was set for
   * @param c calendar to set alarm
   ******************************************************************/

  private void updateTimeText(Calendar c) {
    Log.d(TAG, "updateTimeText: updating alarm time");
    String timeText = "Alarm set for: ";
    timeText += DateFormat.getTimeInstance(DateFormat.SHORT).format(c.getTime());
    mTextView.setText(timeText);
  }

  /*******************************************************************
   * starts the alarm
   * @param c calendar
   ******************************************************************/
  private void startAlarm(Calendar c){
    Log.d(TAG, "startAlarm: setting alarm");

    String tempNewMed = medName.getText().toString();
    Log.d(TAG, "startAlarm: tempNewMed is " + tempNewMed);

    AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
    Intent intent = new Intent(this, AlertReceiver.class);
    intent.putExtra("medNameKey", tempNewMed);  // **********************************************************************
    PendingIntent pendingIntent = PendingIntent.getBroadcast(this, alarmID, intent, 0);

    // if time set in past, add a day to set in future
    if (c.before(Calendar.getInstance())){
      Log.d(TAG, "startAlarm: time in past adjustment to future");
      c.add(Calendar.DATE, 1); // add a day
    }

    // sends alarm to system - 3 options below: 1 time, repeat every 15 minutes, repeat once a day
    alarmManager.setExact(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), pendingIntent); // original alarm - non-repeating
//        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);  // repeats daily
//        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), AlarmManager.INTERVAL_FIFTEEN_MINUTES, pendingIntent);  // repeats 15 minutes
  }

  /*******************************************************************
   * cancels alarm associated with unique alarmID
   * @param alarmID to delete alarm
   ******************************************************************/
  public void cancelAlarm(int alarmID){
    AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
    Intent intent = new Intent(this, AlertReceiver.class);
    PendingIntent pendingIntent = PendingIntent.getBroadcast(this, alarmID, intent, 0);
    alarmManager.cancel(pendingIntent);
  }
}