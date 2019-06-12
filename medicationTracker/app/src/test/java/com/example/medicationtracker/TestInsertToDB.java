package com.example.medicationtracker;

import org.junit.Test;

public class TestInsertToDB extends junit.framework.TestCase {
  String FAKE_STRING = "MED";

  @Test
  public void test1() {

    boolean FAKE = true;

    AddNewMed testAddnewMedString = new AddNewMed();

    boolean result = testAddnewMedString.addToDB();
    assertEquals(result, FAKE);
  }
}
