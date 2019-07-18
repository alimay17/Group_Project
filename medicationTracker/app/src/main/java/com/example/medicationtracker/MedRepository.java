package com.example.medicationtracker;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

/********************************************************************
 * a middleware class to abstract database tasks
 * This is the go-between for the view model class and the DAO class
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
   * calls the delete
   * @param med medication to insert
   *******************************************************************/
  public void delete(Medication med) {
    new deleteAsyncTask(mMedDao).execute(med);
  }

  /********************************************************************
   * Async task class to handle deletion
   *******************************************************************/
  private static class deleteAsyncTask extends AsyncTask<Medication, Void, Void> {
    private MedDao mAsyncTaskDao;

    // delete the new med
    deleteAsyncTask(MedDao dao) {
      mAsyncTaskDao = dao;
    }

    @Override
    protected Void doInBackground(final Medication ... params) {
      mAsyncTaskDao.insert(params[0]);
      return null;
    }
  }

}
