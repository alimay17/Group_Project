package com.example.medicationtracker;

import android.os.AsyncTask;

public class PopulateDbAsync extends AsyncTask<Void, Void, Void> {
  private final MedDao mDao;

  PopulateDbAsync(MedRoomDatabase db) {
    mDao = db.medDao();
  }

  @Override
  protected Void doInBackground(final  Void... params) {
      //mDao.deleteAll();
      //Medication med = new Medication("This is your Medication DB");
      //mDao.insert(med);
    return null;
  }

}
