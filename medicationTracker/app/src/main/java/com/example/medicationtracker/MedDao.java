package com.example.medicationtracker;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface MedDao {

  @Insert
  void insert(Medication med);

  @Query("DELETE FROM medication")
  void deleteAll();

  @Query("SELECT * from medication ORDER BY ID DESC")
  LiveData<List<Medication>> getAllMeds();

}
