package com.example.medicationtracker;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

/********************************************************************
 * middleware class to help abstract database functionality
 * this is the go between for activity classes and the repository class
 *******************************************************************/
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

  // get full med list
  public LiveData<List<Medication>> getmAllMeds() { return mAllMeds; }

  // insert
  public void insert(Medication med) { mRepository.insert(med); }

  // delete med by name
  public void deleteMed(String med) {mRepository.deleteMed(med);}
}
