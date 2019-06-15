package com.example.medicationtracker;

import android.app.Application;
import android.os.AsyncTask;
import androidx.lifecycle.LiveData;
import java.util.List;


public class MedRepository {
  private MedDao mMedDao;
  private LiveData<List<Medication>> mAllMeds;

  MedRepository(Application application) {
    MedRoomDatabase db = MedRoomDatabase.getDatabase(application);
    mMedDao = db.medDao();
    mAllMeds = mMedDao.getAllMeds();
  }

  LiveData<List<Medication>> getAllMeds() {
    return mAllMeds;
  }

  public void insert(Medication med) {
    new insertAsyncTask(mMedDao).execute(med);
  }

  private static class insertAsyncTask extends AsyncTask<Medication, Void, Void> {
    private MedDao mAsyncTaskDao;

    insertAsyncTask(MedDao dao) {
      mAsyncTaskDao = dao;
    }

    @Override
    protected Void doInBackground(final Medication ... params) {
      mAsyncTaskDao.insert(params[0]);
      return null;
    }
  }

}
