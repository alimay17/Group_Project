package com.example.medicationtracker;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class MedViewModel extends AndroidViewModel {
  private MedRepository mRepository;
  private LiveData<List<Medication>> mAllMeds;

  public MedViewModel(Application application) {
    super(application);
    mRepository = new MedRepository(application);
    mAllMeds = mRepository.getAllMeds();
  }

  public LiveData<List<Medication>> getmAllMeds() { return mAllMeds; }

  public void insert(Medication med) { mRepository.insert(med); }
}
