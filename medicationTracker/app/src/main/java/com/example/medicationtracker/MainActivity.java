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

public class MainActivity extends AppCompatActivity {

  // for debug log
  private static final String TAG = "MAIN";
  public static final int NEW_MED_REQUEST_CODE = 1;
  private MedViewModel mMedViewModel;
  private List<Medication> medList;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    Log.d(TAG, "This is MAIN for Medication Tracker");

    // get view layout
    mMedViewModel = ViewModelProviders.of(this).get(MedViewModel.class);
    if(mMedViewModel != null) {
      medList = mMedViewModel.getmAllMeds().getValue();

      mMedViewModel.getmAllMeds().observe(this, new Observer<List<Medication>>() {
        @Override
        public void onChanged(@Nullable final List<Medication> medications) {
          medList = medications;
          getCurrentMed();
        }
      });
    }

    // button to create new medication
    Button addNew = findViewById(R.id.addNew);
    addNew.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        Intent intent = new Intent(MainActivity.this, NewMed.class);
        startActivityForResult(intent, NEW_MED_REQUEST_CODE);
      }
    });

  }

  /**
   * when new med has been created
   * @param requestCode
   * @param resultCode
   * @param data
   */
  public void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);

    if(requestCode == NEW_MED_REQUEST_CODE && resultCode == RESULT_OK) {
      Medication med = new Medication(data.getStringExtra(NewMed.EXTRA_REPLY));
      mMedViewModel.insert(med);
      Toast.makeText(getApplicationContext(),"Med Added", Toast.LENGTH_LONG).show();
    } else {
      Toast.makeText(
              getApplicationContext(),
              R.string.empty_not_saved,
              Toast.LENGTH_LONG).show();
    }
  }

  /**
   * Gets most recent medication
   */
  public void getCurrentMed() {
    if(medList.size() == 0) {
      TextView name = findViewById(R.id.currentMed);
      name.setText("no Medication");
    } else {
      int position = medList.size() - 1;
      final Medication med = medList.get(position);
      Log.d(TAG, "GetCurrentMed: med: " + med.getName());
      TextView name = findViewById(R.id.currentMed);
      name.setText(med.getName());
    }
  }


  /**
   * to send selected med to history detail activity.
   * @param view
   */
  public void onClick(View view, int position) {
    Log.d(TAG, "onClick: position:" + position);
    Log.d(TAG, "Creating intent for HistoryDetail");

    final List<Medication> meds = mMedViewModel.getmAllMeds().getValue();
    final Medication med = meds.get(position);
    Log.d(TAG, "onClick: med: " + med.getName());


    Intent intent = new Intent(this, HistoryDetail.class);
    intent.putExtra("medication", med.getName());
    startActivity(intent);
  }


  public void getHistory(View view){
    Log.d(TAG, "Creating intent for HistoryMain");
    Intent intent = new Intent(this, HistoryMain.class);
    startActivity(intent);
  }

}
