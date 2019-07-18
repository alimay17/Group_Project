package com.example.medicationtracker;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

/********************************************************************
 * class to define a database using the Room persistence library
 * annotations are for database functionality
 *******************************************************************/
@Database(entities = {Medication.class}, version = 1)
@TypeConverters({Converters.class})
public abstract class MedRoomDatabase extends RoomDatabase {

  public abstract MedDao medDao();
  private static volatile MedRoomDatabase INSTANCE;

  // create new database
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

  // populate database after creation
  private static RoomDatabase.Callback sRoomDatabaseCallback =
          new RoomDatabase.Callback() {
            @Override
            public void onOpen(@NonNull SupportSQLiteDatabase db) {
              super.onOpen(db);
              new PopulateDbAsync(INSTANCE).execute();
            }
          };

}
