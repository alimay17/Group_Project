package com.example.medicationtracker;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import javax.annotation.Nullable;

public class HistoryMain extends AppCompatActivity {
  private static final String TAG = "HISTORY_MAIN";

  public static final int NEW_MED_REQUEST_CODE = 1;
  private MedViewModel mMedViewModel;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_history_main);
    Log.d(TAG, "This is History MAIN");

    mMedViewModel = ViewModelProviders.of(this).get(MedViewModel.class);

    RecyclerView recyclerView = findViewById(R.id.rView);
    final MedListAdapter adapter = new MedListAdapter(this);
    recyclerView.setAdapter(adapter);
    recyclerView.setLayoutManager(new LinearLayoutManager(this));

    mMedViewModel.getmAllMeds().observe(this, new Observer<List<Medication>>() {
      @Override
      public void onChanged(@Nullable final List<Medication> medications) {
        adapter.setMeds(medications);
      }
    });

    FloatingActionButton fab = findViewById(R.id.fab);
    fab.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        Intent intent = new Intent(HistoryMain.this, NewMed.class);
        startActivityForResult(intent, NEW_MED_REQUEST_CODE);
      }
    });
  }

  public void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);

    if(requestCode == NEW_MED_REQUEST_CODE && resultCode == RESULT_OK) {
      Medication med = new Medication(data.getStringExtra(NewMed.EXTRA_REPLY));
      mMedViewModel.insert(med);
     } else {
      Toast.makeText(
              getApplicationContext(),
              R.string.empty_not_saved,
              Toast.LENGTH_LONG).show();
    }
  }

  public void getHistoryDetail(View view) {
    Log.d(TAG, "Creating intent for HistoryDetail");
    Intent intent = new Intent(this, HistoryDetail.class);
    startActivity(intent);
  }
}