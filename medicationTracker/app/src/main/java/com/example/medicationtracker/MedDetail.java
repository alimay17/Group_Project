package com.example.medicationtracker;

import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import java.sql.Timestamp;

/********************************************************************
 * Class to define the medication detail activity
 *******************************************************************/
public class MedDetail extends AppCompatActivity {

  // for debug
  private static final String TAG = "MEDICATION_DETAIL";

  // db interface
  private MedViewModel mMedViewModel;

  /********************************************************************
   * initializes the display and catches the incoming intent
   * @param savedInstanceState
   *******************************************************************/
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_med_detail);

    // get database
    mMedViewModel = ViewModelProviders.of(this).get(MedViewModel.class);
    Log.d(TAG, "This is History Detail");
    getIncomingIntent();
  }

  /********************************************************************
   * parses the incoming intent for display
   *******************************************************************/
  private void getIncomingIntent() {
    Log.d(TAG, "getIncomingIntent: Getting intent");

    if(getIntent().hasExtra("medication")) {
      Log.d(TAG, "getIncomingIntent: Got intent");
      String medName = getIntent().getStringExtra("medication");
      String dose = getIntent().getStringExtra("dose");
      Long date = getIntent().getLongExtra("date", 0);
      Timestamp parsedDate = new Timestamp(date);
      displayDetail(medName, dose, parsedDate);
    }
    else {
      Log.d(TAG, "getIncomingIntent: no Intent");
    }
  }

  /********************************************************************
   * displays the selected medication details
   * @param medName name of selected med
   * @param medDose dosage
   * @param medDate timestamp med was created
   ********************************************************************/
  private void displayDetail(String medName, String medDose, Timestamp medDate) {
    Log.d(TAG, "displayImage: setting med to display");
    TextView name = findViewById(R.id.medDetail);
    TextView dose = findViewById(R.id.dose);
    TextView date = findViewById(R.id.created);
    name.setText(String.format(medName));
    dose.setText(String.format("Dosage: %s", medDose));;
    date.setText("Date Created: " + DateFormat.format("dd-MM-yyyy hh:mm", medDate).toString());
  }

  /********************************************************************
   * deletes the selected medication
   * @param view for intent
   *******************************************************************/
  public void deleteMed(View view) {
    String dMed = getIntent().getStringExtra("medication");
    Log.d(TAG, "deleteMed: about to delete medication: " + dMed);
    mMedViewModel.deleteMed(dMed);
    Intent intent = new Intent(this, MedListFull.class);
    startActivity(intent);
  }
}
