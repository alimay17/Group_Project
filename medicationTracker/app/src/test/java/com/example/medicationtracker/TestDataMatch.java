package com.example.medicationtracker;

import org.junit.Test;

public class TestDataMatch extends junit.framework.TestCase {

  @Test
  public void testGetData() {
    Medication fakeMed = new Medication("test");

    RetrieveMed medDB = new RetrieveMed();
    Medication testMed = medDB.getMedFromDb();

    assertSame(fakeMed, testMed);
  }
}
