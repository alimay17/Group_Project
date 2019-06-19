package com.example.medicationtracker;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Medication.class}, version = 1)
public abstract class MedRoomDatabase extends RoomDatabase {

  public abstract MedDao medDao();

  private static volatile MedRoomDatabase INSTANCE;

  static MedRoomDatabase getDatabase(final Context context) {
    if (INSTANCE == null) {
      synchronized (MedRoomDatabase.class) {
        if(INSTANCE == null) {
          INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                  MedRoomDatabase.class, "med_database")
                  .addCallback(sRoomDatabaseCallback)
                  .build();
        }
      }
    }
    return INSTANCE;
  }

  private static RoomDatabase.Callback sRoomDatabaseCallback =
          new RoomDatabase.Callback() {
            @Override
            public void onOpen(@NonNull SupportSQLiteDatabase db) {
              super.onOpen(db);
              new PopulateDbAsync(INSTANCE).execute();
            }
          };

}
