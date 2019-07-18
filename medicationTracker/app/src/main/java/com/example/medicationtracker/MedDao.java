package com.example.medicationtracker;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

/********************************************************************
 * This interface defines how the app interacts with the database
 *******************************************************************/
@Dao
public interface MedDao {

  /*********************************************
   * Insert new medication
   * @param med data for insertion
   ********************************************/
  @Insert
  void insert(Medication med);

  /*********************************************
   * deletes all medications from database
   ********************************************/
  @Query("DELETE FROM medication")
  void deleteAll();

  /*********************************************
   * deletes selected med from database
   ********************************************/
  @Delete
  void delete(Medication deleteMed);

  /*******************************************
   * retrieves all medications from database
   * @return medication as a LiveData List
   ********************************************/
  @Query("SELECT * from medication ORDER BY ID DESC")
  LiveData<List<Medication>> getAllMeds();

}
