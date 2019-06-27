package com.example.medicationtracker;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import javax.annotation.Nullable;

public class HistoryMain extends AppCompatActivity implements OnItemClickListener {

  // debug tag, new med request code, med view object
  private static final String TAG = "HISTORY_MAIN";
  public static final int NEW_MED_REQUEST_CODE = 1;
  private MedViewModel mMedViewModel;
  private List<Medication> medications;

  /**
   * When activity is created
   * @param savedInstanceState
   */
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    Log.d(TAG, "This is History MAIN");

    // get view layout
    setContentView(R.layout.activity_history_main);
    mMedViewModel = ViewModelProviders.of(this).get(MedViewModel.class);

    //Log.d(TAG, "onCreate: medications: " + medications);

    RecyclerView recyclerView = findViewById(R.id.rView);


    // set view adapter
    final MedListAdapter adapter = new MedListAdapter(this);
    recyclerView.setAdapter(adapter);
    recyclerView.setLayoutManager(new LinearLayoutManager(this));

    // bind click listener
    adapter.setClickListener(this);

    // listener for db updates
    mMedViewModel.getmAllMeds().observe(this, new Observer<List<Medication>>() {
      @Override
      public void onChanged(@Nullable final List<Medication> medications) {
        adapter.setMeds(medications);
      }
    });

    // button to create new medication
    Button addNew2 = findViewById(R.id.addNew2);
    addNew2.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        Intent intent = new Intent(HistoryMain.this, NewMed.class);
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
   * to send selected med to history detail activity.
   * @param view
   */
  public void onClick(View view, int position) {
    Log.d(TAG, "onClick: position:" + position);
    Log.d(TAG, "Creating intent for HistoryDetail");

    final List<Medication> meds = mMedViewModel.getmAllMeds().getValue();
    final Medication med = meds.get(position);
    Log.d(TAG, "onClick: med: " + med.getId() + "  " + med.getName());


    Intent intent = new Intent(this, HistoryDetail.class);
    intent.putExtra("medication", med.getName());
    startActivity(intent);
  }
}