package com.example.medicationtracker;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Medication.class}, version = 1)
public abstract class MedRoomDatabase extends RoomDatabase {
  public abstract MedDao medDao();

  private static volatile MedRoomDatabase INSTANCE;

  static MedRoomDatabase getDatabase(final Context context) {
    if (INSTANCE == null) {
      synchronized (MedRoomDatabase.class) {
        if(INSTANCE == null) {
          INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                  MedRoomDatabase.class, "med_database").build();
        }
      }
    }
    return INSTANCE;
  }

}
