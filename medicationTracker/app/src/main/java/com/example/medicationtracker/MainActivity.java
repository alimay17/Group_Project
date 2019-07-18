package com.example.medicationtracker;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import java.util.List;

import javax.annotation.Nullable;

/********************************************************************
 * This is the Main Activity for the Medication Tracker.
 * This class can add a new medication, displays the most recent medication
 * and has links to history page and the medication details page.
 * @author Rich Terry, Alice Smith, Eric Mott
 * @version 1.0
 *******************************************************************/
public class MainActivity extends AppCompatActivity {

  // member variables for debug tag, new med request code, med view object
  private static final String TAG = "MAIN";
  public static final int NEW_MED_REQUEST_CODE = 1;
  private MedViewModel mMedViewModel;
  private List<Medication> medList;

  /********************************************************************
   * onCreate for Main Activity
   * Initializes the display and access to the db.
   * @param savedInstanceState
   *******************************************************************/
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    Log.d(TAG, "This is MAIN for Medication Tracker");

    // get view layout for most recent med display
    mMedViewModel = ViewModelProviders.of(this).get(MedViewModel.class);
    if(mMedViewModel != null) {
      medList = mMedViewModel.getmAllMeds().getValue();
      mMedViewModel.getmAllMeds().observe(this, new Observer<List<Medication>>() {
        @Override
        public void onChanged(@Nullable final List<Medication> medications) {
          medList = medications;
          displayCurrentMed();
        }
      });
    }

    // button and function to create new medication
    Button addNew = findViewById(R.id.addNew);
    addNew.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        Intent intent = new Intent(MainActivity.this, NewMedActivity.class);
        startActivityForResult(intent, NEW_MED_REQUEST_CODE);
      }
    });

  }

  /********************************************************************
   * Handles the results of the new med intent
   * @param requestCode to verify med creation
   * @param resultCode result of the new med intent
   * @param data user data from the new med class / activity
   *******************************************************************/
  public void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);

    if(requestCode == NEW_MED_REQUEST_CODE && resultCode == RESULT_OK) {
      Medication med = new Medication(data.getStringExtra(NewMedActivity.EXTRA_REPLY), data.getStringExtra("dose"));
      mMedViewModel.insert(med);
      Toast.makeText(getApplicationContext(),"Med Added", Toast.LENGTH_LONG).show();
    } else {
      Toast.makeText(
              getApplicationContext(),
              R.string.empty_not_saved,
              Toast.LENGTH_LONG).show();
    }
  }

  /********************************************************************
   * displays the most recent medication.
   *******************************************************************/
  public void displayCurrentMed() {
    if(medList.size() == 0) {
      TextView name = findViewById(R.id.currentMed);
      name.setText("please add a medication");
    } else {
      int position = 0; // index of most recent medication
      final Medication med = medList.get(position);
      Log.d(TAG, "GetCurrentMed: med: " + med.getId() + "  " + med.getName() + " " + med.getDose());
      TextView name = findViewById(R.id.currentMed);
      name.setText(med.getName());
    }
  }


  /********************************************************************
   * sends selected med to medication detail activity.
   * @param view to create intent
   *******************************************************************/
  public void getDetail(View view) {
    Log.d(TAG, "Creating intent for medDetail");

    int position = 0; // index of medication
    final Medication med = medList.get(position);
    Log.d(TAG, "onClick: med: " + med.getName());

    // set intent with med details.
    Intent intent = new Intent(this, MedDetailActivity.class);
    intent.putExtra("medication", med.getName());
    intent.putExtra("dose", med.getDose());
    intent.putExtra("date", med.getCreated().getTime());
    startActivity(intent);
  }

  /********************************************************************
   * reroutes user to the medication list page.
   * @param view for intent
   *******************************************************************/
  public void getHistory(View view){
    Log.d(TAG, "Creating intent for MedListFull");
    Intent intent = new Intent(this, MedListFullActivity.class);
    startActivity(intent);
  }

}
