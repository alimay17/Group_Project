package com.example.medicationtracker;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.annotation.NonNull;

@Entity(tableName = "medication")

public class Medication {
  @PrimaryKey(autoGenerate = true)
  private int id;


  @ColumnInfo(name = "med_name")
  private String mName;

  // constructor
  public Medication(int id, String mName) {
    this.id = id;
    this.mName = mName;
  }


  // getters
  public int getId() { return id; }


  public String getName() {
    return mName;
  }

  // setters
  public void setId(int id) { this.id = id; }
  public void setmName(@NonNull String mName) { this.mName = mName; }
}
