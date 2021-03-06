package com.example.medicationtracker;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.sql.Timestamp;
import java.util.Date;

/********************************************************************
 * A class to define a medication.
 * Member variables are id, name, dose, date created, and list of alarms
 * annotations are for db functionality
 *******************************************************************/
@Entity(tableName = "medication")
public class Medication {

  // auto created by the db for tracking purposes
  @PrimaryKey(autoGenerate = true)
  private int id;

  // medication name
  @ColumnInfo(name = "med_name")
  private String mName;

  // dosage
  private String dose;

  // date created
  @TypeConverters(Converters.class)
  private Date created;

  // id for notifications
  private int alarmID;

  // constructor
  public Medication(String mName, String dose, int alarmID) {
    this.mName = mName;
    this.dose = dose;
    this.alarmID = alarmID;
    Timestamp ts = new Timestamp(System.currentTimeMillis());
    created = ts;
  }

  // getters
  public int getId() { return this.id; }
  public String getName() { return this.mName; }
  public String getDose() { return dose; }
  public Date getCreated() { return created; }
  public int getAlarmID() { return alarmID; }

  // setters
  public void setId(int id) { this.id = id; }
  public void setmName(@NonNull String mName) { this.mName = mName; }
  public void setDose(String dose) { this.dose = dose; }
  public void setCreated(Date created) { this.created = created; }
  public void setAlarmID(int alarmID) { this.alarmID = alarmID; }
}
