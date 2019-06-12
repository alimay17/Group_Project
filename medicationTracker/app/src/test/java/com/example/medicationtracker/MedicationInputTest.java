package com.example.medicationtracker;

import org.junit.Test;

public class MedicationInputTest extends junit.framework.TestCase {

  String FAKE_STRING = "MED";

  @Test
  public void testName() {

    String FAKE_STRING = "MED";
    AddNewMed testAddnewMedString = new AddNewMed();
    String result = testAddnewMedString.getMedName();
    assertEquals(result, FAKE_STRING);
  }

  @Test
  public void testDate() {

    String FAKE_STRING = "MED";
    AddNewMed testAddnewMedString = new AddNewMed();
    String result = testAddnewMedString.getMedName();
    assertEquals(result, FAKE_STRING);
  }

  @Test
  public void testQuantity() {

    String FAKE_STRING = "MED";
    AddNewMed testAddnewMedString = new AddNewMed();
    String result = testAddnewMedString.getMedName();
    assertEquals(result, FAKE_STRING);
  }
}



