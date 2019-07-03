package com.example.medicationtracker;

import androidx.room.TypeConverter;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class Converters {
  @TypeConverter
  public static Date fromTimestamp(Long value) {
    return value == null ? null : new Date(value);
  }

  @TypeConverter
  public static Long dateToTimestamp(Date date) {
    return date == null ? null : date.getTime();
  }

  static Gson gson = new Gson();

  @TypeConverter
  public static List<Integer> stringToSomeObjectList(String data) {
    if (data == null) {
      return Collections.emptyList();
    }

    Type listType = new TypeToken<List<Integer>>() {}.getType();

    return gson.fromJson(data, listType);
  }

  @TypeConverter
  public static String someObjectListToString(List<Integer> someObjects) {
    return gson.toJson(someObjects);
  }
}
