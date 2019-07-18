package com.example.medicationtracker;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

/********************************************************************
 * a middleware class to abstract database tasks
 * This is called by the repository class and implements the DAO interface
 *******************************************************************/
public class MedRepository {
  private MedDao mMedDao;
  private LiveData<List<Medication>> mAllMeds;

  // constructor
  MedRepository(Application application) {
    MedRoomDatabase db = MedRoomDatabase.getDatabase(application);
    mMedDao = db.medDao();
    mAllMeds = mMedDao.getAllMeds();
  }

  /********************************************************************
   * @return a list of all medications
   *******************************************************************/
  LiveData<List<Medication>> getAllMeds() {
    return mAllMeds;
  }

  /********************************************************************
   * calls the insert function
   * @param med medication to insert
   *******************************************************************/
  public void insert(Medication med) {
    new insertAsyncTask(mMedDao).execute(med);
  }

  /********************************************************************
   * Async task class to handle insertion
   *******************************************************************/
  private static class insertAsyncTask extends android.os.AsyncTask<Medication, Void, Void> {
    private MedDao mAsyncTaskDao;

    // insert the new med
    insertAsyncTask(MedDao dao) {
      mAsyncTaskDao = dao;
    }

    @Override
    protected Void doInBackground(final Medication ... params) {
      mAsyncTaskDao.insert(params[0]);
      return null;
    }
  }


  /********************************************************************
   * calls the delete med by name function
   * @param medName medication to delete
   *******************************************************************/
  public void deleteMed(String medName) {
    new deleteMedAsyncTask(mMedDao).execute(medName);
  }

  /********************************************************************
   * Async task class to handle deletion
   *******************************************************************/
  private static class deleteMedAsyncTask extends AsyncTask<String, Void, Void> {
    private MedDao mAsyncTaskDao;

    // delete the med
    deleteMedAsyncTask(MedDao dao) {
      mAsyncTaskDao = dao;
    }

    @Override
    protected Void doInBackground(final String ... params) {
      mAsyncTaskDao.deleteMed(params[0]);
      return null;
    }
  }

}
