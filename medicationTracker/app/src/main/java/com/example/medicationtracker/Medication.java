package com.example.medicationtracker;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.annotation.NonNull;

@Entity(tableName = "medication")

public class Medication {
  @PrimaryKey(autoGenerate = true)
  private int id;

  @NonNull
  @ColumnInfo(name = "med_name")
  private String mName;

  public Medication(String med_name) {
    this.mName = med_name;
  }
}
