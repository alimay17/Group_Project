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

  private String dose;


  public Medication(String mName, String dose) {
    this.mName = mName;
    this.dose = dose;
  }


  // getters
  public int getId() { return this.id; }
  public String getName() { return this.mName; }
  public String getDose() { return dose; }

  // setters
  public void setId(int id) { this.id = id; }
  public void setmName(@NonNull String mName) { this.mName = mName; }
  public void setDose(String dose) { this.dose = dose; }
}
