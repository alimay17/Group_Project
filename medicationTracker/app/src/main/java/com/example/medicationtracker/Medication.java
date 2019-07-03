package com.example.medicationtracker;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

// Annotations are for the db functionality.
@Entity(tableName = "medication")
public class Medication {

  @PrimaryKey(autoGenerate = true)
  private int id;


  @ColumnInfo(name = "med_name")
  private String mName;
  private String dose;

  @TypeConverters(Converters.class)
  private Date created;

  @TypeConverters(Converters.class)
  private List<Integer> medAlarms;


  public Medication(String mName, String dose) {
    this.mName = mName;
    this.dose = dose;
    Timestamp ts = new Timestamp(System.currentTimeMillis());
    created = ts;
  }


  // getters
  public int getId() { return this.id; }
  public String getName() { return this.mName; }
  public String getDose() { return dose; }
  public Date getCreated() { return created; }
  public List<Integer> getMedAlarms() { return medAlarms; }

  // setters
  public void setId(int id) { this.id = id; }
  public void setmName(@NonNull String mName) { this.mName = mName; }
  public void setDose(String dose) { this.dose = dose; }
  public void setCreated(Date created) { this.created = created; }
  public void setMedAlarms(List<Integer> medAlarms) { this.medAlarms = medAlarms; }
}
