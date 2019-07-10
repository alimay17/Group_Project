package com.example.medicationtracker;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.*;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import org.w3c.dom.Text;


public class NewMed extends AppCompatActivity /*implements TimePickerDialog.OnTimeSetListener*/ {

  public static final String TAG = "NEW_MED";
  public static final String EXTRA_REPLY = "com.example.android.wordlistsql.REPLY";
  public static final int SET_ALARM_REQUEST_CODE = 5;
  private EditText medName;
  private EditText medDose;

  public void setTextView(String textView) {
//    mTextView = textView;
    mTextView.setText(textView);
  }

  private TextView mTextView;

  public void setAlarmID(int alarmID) {
    this.alarmID = alarmID;
  }

  private int alarmID;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_new_med);

    Log.d(TAG, "onCreate: this is add new med");


    medName = findViewById(R.id.name);
    medDose = findViewById(R.id.dose);
    mTextView = findViewById(R.id.textView_remindTime);

    Button buttonRemind = (Button) findViewById(R.id.button_setReminder);
    buttonRemind.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        if (medName.getText().toString().equals("")) {
          Toast.makeText(NewMed.this, "Please add a medication", Toast.LENGTH_SHORT).show();
        } else {
          Log.d(TAG, "onClick: Creating Intent before calling SetAlarm");
          Intent intent = new Intent(NewMed.this, SetAlarm.class);

          //add cancel info ID here as:
          intent.putExtra("medName", medName.getText().toString());
          intent.putExtra("medDose", medDose.getText().toString());
          //intent.putExtra(alarmID, alarmID);

          Log.d(TAG, "onClick: Intent created, calling startActivityForResult");
          startActivityForResult(intent, SET_ALARM_REQUEST_CODE);  // 1 should be replaced with a constant

          // catching returned data
          Intent incomingIntent = getIntent();
          setAlarmID(Integer.parseInt(incomingIntent.getStringExtra("medName"))); // convert alarmID string to int
          setTextView(incomingIntent.getStringExtra("mTextView"));
        }
      }
    });
    mTextView.setText("test");

    final Button button = findViewById(R.id.button_save);
    button.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
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
        Log.d(TAG, "onClick: *****************added new med**************************************************");
        finish();
      }
    });
  }

  @Override
  protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
    super.onActivityResult(requestCode, resultCode, data);

    if (requestCode == 1) {
      if (resultCode == RESULT_OK){
        alarmID = data.getIntExtra("alarmID", 0);
      }
      if (resultCode == RESULT_CANCELED){
        alarmID = 0;
      }
    }
  }

  /*@Override
  public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
    TextView textView = (TextView)findViewById(R.id.textView_remindTime);
    textView.setText("Hour " + hourOfDay + " Minute: " + minute);
  }*/
}
