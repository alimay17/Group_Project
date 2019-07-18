/*
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

        setTitle("Set Reminder Time");
        mTextView = findViewById(R.id.textView_time);  // shows alarm time on screen
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
//        resultIntent.putExtra("mTextView", mTextView.getText().toString());  // pass the alarm time as a string

        setResult(RESULT_OK, resultIntent);

//        finish();  //  ***************************** this is being called before time selected in timePicker **************

        button_set_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

Log.d(TAG, "onCreate: calling TimePicker");
                DialogFragment timePicker = new TimePickerFragment();
                timePicker.show(getSupportFragmentManager(), "time picker");

//                sendOnChannel1(notificationTitle, medName); // not needed??

                Intent resultIntent = new Intent();
                resultIntent.putExtra("alarmID", mAlarmID);

                setResult(RESULT_OK, resultIntent);

                Log.d(TAG, "onClick: ******************** Going back to NEWMED*******************************************");

                finish();  //  ***************************** this is being called before time selected in timePicker **************
            }
        });
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        Log.d(TAG, "onTimeSet: loading TimePicker values into Calendar C");
        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, hourOfDay);
        c.set(Calendar.MINUTE, minute);
        c.set(Calendar.SECOND, 0);

        updateTimeText(c);
        startAlarm(c);
    }

public void sendOnChannel1(String title, String message){
        NotificationCompat.Builder nb = mNotificationHelper.getChannel1Notification(title, message);
        mNotificationHelper.getManager().notify(1, nb.build()); // the 1 an id for channel1
    }


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

        // sends alarm to system - 3 options below: 1 time, repeat every 15 minutes, repeat once a day
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

    // alarmID using timestamp - need to correct to pull system time
*/
/*public void createAlarmID(){
        mAlarmID = c.get(Calendar.YEAR);
        mAlarmID += c.get(Calendar.DAY_OF_YEAR);
        mAlarmID += c.get(Calendar.HOUR_OF_DAY);
        mAlarmID += c.get(Calendar.MINUTE);
    }*//*



}
*/


// ############################################################################################################
// ############################################################################################################



// $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
// $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$  This section broken from GYAP Going back to above $$$$$$$$$$$$$$$$$$$$$$$$$$$$$

/*package com.example.medicationtracker;

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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import org.w3c.dom.Text;

import java.text.DateFormat;
import java.util.Date;
import java.util.Random;
import java.util.Set;


public class SetAlarm extends DialogFragment implements TimePickerDialog.OnTimeSetListener { // replaced AppCompatActivity with Fragment

    private EditText editText;                                                  // used for testing - Frag to Frag Demo ***

    TimePicker timePicker;
    TextView textView;

    int mHour;
    int mMinute;

    public static final String TAG = "SETALARM";
    public static final int SET_ALARM_REQUEST_CODE = 5;
    private int mAlarmID;  // ID used to set/delete alarm
    private CharSequence alarmID;
    private int intAlarmID;
    private int timeHour;
    private int timeMinute;
    private TextView mTextView;

    private String timeText; // alarm time as a string

    private EditText tempMedName;
    private EditText editText_time;
    private FragmentSetAlarmListener listener;
    private Button button_set_time;

    private Context mContext; // trying to solve getSystemService error in AlarmManager = alarmManager

    // these variables what is passed to the notification - they need to be replaced with the medication
    private String notificationTitle = "It's time to take your medication";  // need to look at passing this from newMed to notification
    private String medName = "Need to pass medication to this variable";  // need to look at passing this from newMed to notification
//    private Button buttonChannel1; // temp                                                                *** temp ***

    private NotificationHelper mNotificationHelper;

    Calendar c;

    public interface FragmentSetAlarmListener {
        void saveTime(CharSequence input);  //can I add additional info here, i.e. (CharSequence input, int alarmID)?
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_set_alarm, container, false);
        Log.d(TAG, "onCreateView: Creating fragment view");

        Button button_set_time = (Button) v.findViewById(R.id.button_set_time);
//        editText = v.findViewById(R.id.edit_text);
//        button_set_time = v.findViewById(R.id.button_set_time); // going back to 1st timePicker

        createAlarmID();

//        Button button = (Button) v.findViewById(R.id.button);
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                DialogFragment timePicker = new TimePickerFragment();
//                timePicker.show(getChildFragmentManager(), "time picker");  // used getChildFragmentManager instead of getSupportFragmentManager
//            }
//        });

        timePicker = v.findViewById(R.id.timePicker); // going back to 1st timePicker
        textView = v.findViewById(R.id.textView_time); // shows alarm time on screen // going back to 1st timePicker
        button_set_time = v.findViewById(R.id.button_set_time); // going back to 1st timePicker

        // Working ^^^




        // going back to 1st timePicker
        *//*timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {

                mHour = hourOfDay;
                mMinute = minute;
                textView.setText(textView.getText().toString()+ " " );

            }
        });*//*

        button_set_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: button_set_time selected");
                CharSequence input = editText_time.getText();  // this should be the alarmID first then the editText_time info, if possible
                listener.saveTime(input);
                listener.saveTime(alarmID); //can I add additional info here, i.e. (input, alarmID)?
            }
        });


        //~~~~~~~~~~~~^ 07/11/19 EM ^~~~~~~~~~~~~~
        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

        *//*createAlarmID();*//**//*



        mNotificationHelper = new NotificationHelper(mContext);  // mContext was this

        // running timePicker at startup
        Log.d(TAG, "onCreate: calling TimePicker");
        DialogFragment timePicker = new TimePickerFragment();
//        timePicker.show(getSupportFragmentManager(), "time picker");
        timePicker.show(getChildFragmentManager(), "time picker"); // don't know if this will work.**********************
*//*
        return v; // sets this layout as fragment layout
    }

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    //~~~~~~~~~~~~~ 07/11/19 EM ~~~~~~~~~~~~~~

    @Override
    public void onAttach(@NonNull Context context) { // called when Fragment attached to activity (NewMed)
        super.onAttach(context);
        if (context instanceof FragmentSetAlarmListener){ // implements interface into activity
            listener = (FragmentSetAlarmListener) context;
        } else {
            throw new RuntimeException(context.toString() + "must implement FragmentSetAlarmListener");
        }
    }

    @Override
    public void onDetach() { // when Fragment removed from listener
        super.onDetach();
        listener = null;
    }

    public void updateNewMed(CharSequence newMedText){ // converts the newMed name to a String when passed from NewMed activity
        tempMedName.setText(newMedText);
        medName = tempMedName.toString();
    }


    public void setTimer(View v, Context context){

//        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        AlarmManager alarmManager = (AlarmManager) getActivity().getSystemService(Context.ALARM_SERVICE);  // Don't know if this will work**************
        Date date = new Date();

        Calendar cal_alarm = Calendar.getInstance();
        Calendar cal_now = Calendar.getInstance();

        cal_now.setTime(date);
        cal_alarm.setTime(date);


        Log.d(TAG, "onCreateView: loading TimePicker values into Calendar C");
        cal_alarm.set(Calendar.HOUR_OF_DAY, mHour);
        cal_alarm.set(Calendar.MINUTE, mMinute);
        cal_alarm.set(Calendar.SECOND, 0);

        // if time set in past, add a day to set in future
        if (cal_alarm.before(Calendar.getInstance())){
            Log.d(TAG, "startAlarm: time in past adjustment to future");
            cal_alarm.add(Calendar.DATE, 1); // add a day
        }

        startAlarm(cal_alarm);
        updateTimeText(cal_alarm);

    }

    // starts the alarm
    private void startAlarm(Calendar c){
//        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        AlarmManager alarmManager = (AlarmManager) getActivity().getSystemService(Context.ALARM_SERVICE);
//        Intent intent = new Intent(this, AlertReceiver.class);
        Intent intent = new Intent(getActivity(), AlertReceiver.class);  // Don't know if this will work ******************************
//        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, mAlarmID, intent, 0);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getActivity(), mAlarmID, intent, 0); // Don't know if this will work

       *//* // if time set in past, add a day to set in future
        if (c.before(Calendar.getInstance())){
            Log.d(TAG, "startAlarm: time in past adjustment to future");
            c.add(Calendar.DATE, 1); // add a day
        }*//*

        // sends alarm to system - 3 options below: 1 time, repeat every 15 minutes, repeat once a day
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), pendingIntent); // original alarm - non-repeating
//        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);  // repeats daily
//        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), AlarmManager.INTERVAL_FIFTEEN_MINUTES, pendingIntent);  // repeats 15 minutes
    }

    // create a unique alarmID
    public void createAlarmID() {
        *//*Log.d(TAG, "getAlarmID: create/return alarmID");
        int mMin = 100; // min of alarmID range
        int mMax = 999; // max of alarmID range
        Random r = new Random();
//        mAlarmID = (r.nextInt(mMax - mMin + 1) + mMin); // create random number from 100 - 999
        alarmID = Integer.toString(r.nextInt(mMax - mMin + 1) + mMin); // create random number from 100 - 999*//*

        // unique ID using timestamp
        Calendar calAlarmID = Calendar.getInstance();
        int yearID = calAlarmID.get(Calendar.YEAR);
        int dayID = calAlarmID.get(Calendar.DAY_OF_YEAR);
        int hourID = calAlarmID.get(Calendar.HOUR_OF_DAY);
        int minuteID = calAlarmID.get(Calendar.MINUTE);
        int secondID = calAlarmID.get(Calendar.SECOND);

        String alarmIdBuilder = Integer.toString(yearID - 2000);
        alarmIdBuilder += Integer.toString(dayID);
        alarmIdBuilder += Integer.toString(hourID);
        alarmIdBuilder += Integer.toString(minuteID);
        alarmIdBuilder += Integer.toString(secondID);

        alarmID = alarmIdBuilder;
        Log.d(TAG, "createAlarmID: alarmID Created: " + alarmID);
    }

    // creates the String showing the time the alarm was set for
    private void updateTimeText(Calendar c) {
        String timeText = "Alarm set for: ";
        timeText += DateFormat.getTimeInstance(DateFormat.SHORT).format(c.getTime());
        timeText += ", ID: ";  // ********************************************************************** Delete - test info
        timeText += mAlarmID;  // ********************************************************************** Delete - test info
        mTextView.setText(timeText);
    }



    //~~~~~~~~~~~~^ 07/11/19 EM ^~~~~~~~~~~~~~
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~







// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
//    @Override
//    protected void onCreate(Bundle savedInstanceState){


//        mNotificationHelper = new NotificationHelper(this);

//        Log.d(TAG, "onCreate: calling TimePicker");
//        DialogFragment timePicker = new TimePickerFragment();
//        timePicker.show(getSupportFragmentManager(), "time picker");

//        Intent resultIntent = new Intent();
//        resultIntent.putExtra("alarmID", mAlarmID);  // pass alarmID back thru resultIntent
//        resultIntent.putExtra("mTextView", mTextView.getText().toString());  // pass the alarm time as a string

//        setResult(RESULT_OK, resultIntent);

//        finish();  //  ***************************** this is being called before time selected in timePicker **************

//        button_set_time.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                Log.d(TAG, "onCreate: calling TimePicker");
//                DialogFragment timePicker = new TimePickerFragment();
//                timePicker.show(getSupportFragmentManager(), "time picker");
//
////                sendOnChannel1(notificationTitle, medName); // not needed??
//
//                Intent resultIntent = new Intent();
//                resultIntent.putExtra("alarmID", mAlarmID);
//
//                setResult(RESULT_OK, resultIntent);
//
//                Log.d(TAG, "onClick: ******************** Going back to NEWMED*******************************************");
//
//                finish();  //  ***************************** this is being called before time selected in timePicker **************
//            }
//        });
//    }

    // using with 1st timePicker
    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        Log.d(TAG, "onTimeSet: loading TimePicker values into Calendar C");
        // changing to 1st timePicker
        *//*Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, hourOfDay);
        c.set(Calendar.MINUTE, minute);
        c.set(Calendar.SECOND, 0);

        updateTimeText(c);
        startAlarm(c);*//*

        TextView textView = getActivity().findViewById(R.id.textView_time);
        textView.setText("Hour: " + hourOfDay + " Minute: " + minute);

        // Not sure if this will set the alarm or not.  ************************************ Turn on after initial test
        *//*Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, hourOfDay);
        c.set(Calendar.MINUTE, minute);
        c.set(Calendar.SECOND, 0);

        updateTimeText(c);
        startAlarm(c);*//*

    }

    public void sendOnChannel1(String title, String message){
        NotificationCompat.Builder nb = mNotificationHelper.getChannel1Notification(title, message);
        mNotificationHelper.getManager().notify(1, nb.build()); // the 1 an id for channel1
    }






   *//* private void cancelAlarm(){
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, AlertReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, mAlarmID, intent, 0);

        alarmManager.cancel(pendingIntent);
    }


    // temp method to clear all alarms set during testing
    public void cancelAllAlarms(View view){
        for (int i = 100; i < 1000; i++){
            AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
            Intent intent = new Intent(this, AlertReceiver.class);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(this, i, intent, 0);

            alarmManager.cancel(pendingIntent);
        }
    }*//*
}*/

// $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$  This section broken from GYAP Going back to above $$$$$$$$$$$$$$$$$$$$$$$$$$$$$
// $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$

package com.example.medicationtracker;

import android.annotation.TargetApi;
import android.app.*;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.DialogFragment;
import org.w3c.dom.Text;

import java.sql.Time;
import java.text.DateFormat;
import java.util.Random;

public class SetAlarm extends AppCompatDialogFragment implements TimePickerDialog.OnTimeSetListener {
    public static final String TAG = "SETALARM";
    public static final int SET_ALARM_REQUEST_CODE = 5;
    private int mAlarmID;  // ID used to set/delete alarm
    private String alarmID;
    private int mHour; // hour alarm is set for
    private int mMinute; // minute alarm is set for
    private TextView mTextView_time;

    private String timeText; // alarm time as a string

    private Button button_set_time;

    // these variables what is passed to the notification - they need to be replaced with the medication
    private String notificationTitle = "It's time to take your medication";  // need to look at passing this from newMed to notification
    private String medName = "Need to pass medication to this variable";  // need to look at passing this from newMed to notification
//    private Button buttonChannel1; // temp                                                                *** temp ***

    private NotificationHelper mNotificationHelper;

    TimePicker timePicker;

    Calendar c;

    SetAlarm mSetAlarm;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreateDialog: Starting SetAlarm");

        DialogFragment timePicker = new TimePickerFragment();
        timePicker.show(getFragmentManager(), "time picker");





















       /* AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.activity_set_alarm, null);

        *//*timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                mHour = hourOfDay;  // may not need - see mTextView_time below
                mMinute = minute;  // may not need - see mTextView_time below
                mTextView_time.setText("Alarm set for: " + hourOfDay + ":" + minute);
                Log.d(TAG, "onTimeChanged: Time being passed back - \" + mTextView_time");
            }
        });*//*

        builder.setView(view)
            .setTitle("Set Alarm Time")
            .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            })
            .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    

                }
            });
        return builder.create();*/
        return null;    // ***************************************************************************** this should not be null... need to return the string value for the alarm time
    }

    // takes time sent from timePicker and assigns it to a Calendar - Calendar needed to set the alarm
    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        Log.d(TAG, "onTimeSet: loading TimePicker values into Calendar C");
        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, hourOfDay);
        c.set(Calendar.MINUTE, minute);
        c.set(Calendar.SECOND, 0);

//        updateTimeText(c);
//        startAlarm(c);
    }


    // takes time from timePicker - assigning values to string to pass back to NewMed
   /* @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        *//*mHour = hourOfDay;  // may not need - see mTextView_time below
        mMinute = minute;  // may not need - see mTextView_time below
        mTextView_time.setText("Alarm set for: " + hourOfDay + ":" + minute);
        Log.d(TAG, "onTimeSet: Time being passed back - " + mTextView_time);*//*

        Log.d(TAG, "onTimeSet: loading TimePicker values into Calendar C");
        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, hourOfDay);
        c.set(Calendar.MINUTE, minute);
        c.set(Calendar.SECOND, 0);

        updateTimeText(c);
//        startAlarm(c);
    }*/

/*
    // creates the String showing the time the alarm was set for
    private void updateTimeText(Calendar c) {
        Log.d(TAG, "updateTimeText: Setting text string with alarm time");
        String timeText = "Alarm set for: ";
        timeText += DateFormat.getTimeInstance(DateFormat.SHORT).format(c.getTime());
        timeText += ", ID: ";  // ********************************************************************** Delete - test info
        timeText += mAlarmID;  // ********************************************************************** Delete - test info
        mTextView_time.setText(timeText);
    }
*/


    /*public interface SetAlarmListener{
        void applyTime(int mAlarmID, String timeText);
    }*/
}





/*


    @Override
//    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_alarm);
        Log.d(TAG, "onCreate: landed on SetAlarm");

        // set clock to current time
        java.util.Calendar c = java.util.Calendar.getInstance();
        hour = c.get(java.util.Calendar.HOUR_OF_DAY);
        minute = c.get(java.util.Calendar.MINUTE);


        setTitle("Set Reminder Time");
        mTextView = findViewById(R.id.textView_time);  // shows alarm time on screen
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
//        resultIntent.putExtra("mTextView", mTextView.getText().toString());  // pass the alarm time as a string

        setResult(RESULT_OK, resultIntent);

//        finish();  //  ***************************** this is being called before time selected in timePicker **************

        button_set_time.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

//Log.d(TAG, "onCreate: calling TimePicker");
//                DialogFragment timePicker = new TimePickerFragment();
//                timePicker.show(getSupportFragmentManager(), "time picker");

//                sendOnChannel1(notificationTitle, medName); // not needed??

                Intent resultIntent = new Intent();
                resultIntent.putExtra("alarmID", mAlarmID);

                setResult(RESULT_OK, resultIntent);

                Log.d(TAG, "onClick: ******************** Going back to NEWMED*******************************************");

//                finish();  //  ***************************** this is being called before time selected in timePicker **************
            }
        });
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        Log.d(TAG, "onTimeSet: loading TimePicker values into Calendar C");
        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, hourOfDay);
        c.set(Calendar.MINUTE, minute);
        c.set(Calendar.SECOND, 0);

        updateTimeText(c);
        startAlarm(c);
    }

public void sendOnChannel1(String title, String message){
        NotificationCompat.Builder nb = mNotificationHelper.getChannel1Notification(title, message);
        mNotificationHelper.getManager().notify(1, nb.build()); // the 1 an id for channel1
    }


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

        // sends alarm to system - 3 options below: 1 time, repeat every 15 minutes, repeat once a day
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
        // unique ID using timestamp
        Calendar calAlarmID = Calendar.getInstance();
        int yearID = calAlarmID.get(Calendar.YEAR);
        int dayID = calAlarmID.get(Calendar.DAY_OF_YEAR);
        int hourID = calAlarmID.get(Calendar.HOUR_OF_DAY);
        int minuteID = calAlarmID.get(Calendar.MINUTE);
        int secondID = calAlarmID.get(Calendar.SECOND);

        String alarmIdBuilder = Integer.toString(yearID - 2000);
        alarmIdBuilder += Integer.toString(dayID);
        alarmIdBuilder += Integer.toString(hourID);
        alarmIdBuilder += Integer.toString(minuteID);
        alarmIdBuilder += Integer.toString(secondID);

        mAlarmID = Integer.parseInt(alarmIdBuilder);  // alarmID as an Integer
        alarmID = alarmIdBuilder;
        Log.d(TAG, "createAlarmID: alarmID Created: " + alarmID);
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

}
*/
