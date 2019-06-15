package com.example.medicationtracker;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class MedViewModel extends AndroidViewModel {

  // reference repository
  private MedRepository mRepository;

  // cashe list of medications
  private LiveData<List<Medication>> mAllMeds;

  // constructor
  public MedViewModel(Application application) {
    super(application);
    mRepository = new MedRepository(application);
    mAllMeds = mRepository.getAllMeds();
  }

  // getter
  public LiveData<List<Medication>> getmAllMeds() { return mAllMeds; }

  // insert
  public void insert(Medication med) { mRepository.insert(med); }
}
