package com.example.medicationtracker;

import androidx.room.TypeConverter;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/****************************************************************
 * converter class to convert data / timestamp and list / json string
 * used to convert data for db storage
 ***************************************************************/
public class Converters {

  // timestamp to date
  @TypeConverter
  public static Date fromTimestamp(Long value) {
    return value == null ? null : new Date(value);
  }

  // date to timestamp
  @TypeConverter
  public static Long dateToTimestamp(Date date) {
    return date == null ? null : date.getTime();
  }

  // for json string conversion
  static Gson gson = new Gson();

  // json string to object
  @TypeConverter
  public static List<Integer> stringToSomeObjectList(String data) {
    if (data == null) {
      return Collections.emptyList();
    }
    Type listType = new TypeToken<List<Integer>>() {}.getType();
    return gson.fromJson(data, listType);
  }

  // object to json string
  @TypeConverter
  public static String someObjectListToString(List<Integer> someObjects) {
    return gson.toJson(someObjects);
  }
}
