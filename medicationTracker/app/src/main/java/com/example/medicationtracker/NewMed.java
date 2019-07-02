package com.example.medicationtracker;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;


public class NewMed extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener {

  public static final String TAG = "NewMed";
  public static final String EXTRA_REPLY = "com.example.android.wordlistsql.REPLY";
  private EditText medName;
  private EditText medDose;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_new_med);

    Log.d(TAG, "onCreate: this is add new med");


    medName = findViewById(R.id.name);
    medDose = findViewById(R.id.dose);

    Button buttonRemind = (Button) findViewById(R.id.button_setReminder);
    buttonRemind.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        DialogFragment timePicker = new TimePickerFragment();
        timePicker.show(getSupportFragmentManager(), "time picker");
      }
    });

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
        Log.d(TAG, "onClick: added new med");
        finish();
      }
    });
  }

  @Override
  public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
    TextView textView = (TextView)findViewById(R.id.textView_remindTime);
    textView.setText("Hour " + hourOfDay + " Minute: " + minute);
  }
}
