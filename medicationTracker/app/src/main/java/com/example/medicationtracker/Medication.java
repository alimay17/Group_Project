package com.example.medicationtracker;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.annotation.NonNull;

// Annotations are for the db functionality.
@Entity(tableName = "medication")
public class Medication {

  @PrimaryKey(autoGenerate = true)
  private int id;


  @ColumnInfo(name = "med_name")
  private String mName;



  public Medication(String mName) {
    this.mName = mName;
  }


  // getters
  public int getId() { return this.id; }


  public String getName() {
    return this.mName;
  }

  // setters
  public void setId(int id) { this.id = id; }
  public void setmName(@NonNull String mName) { this.mName = mName; }
}
