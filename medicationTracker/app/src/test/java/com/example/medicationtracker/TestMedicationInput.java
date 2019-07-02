package com.example.medicationtracker;

import android.content.Context;
import androidx.lifecycle.LiveData;
import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.io.IOException;
import java.util.List;
import static org.hamcrest.MatcherAssert.assertThat;


public class TestMedicationInput {
  private MedDao medDao;
  private MedRoomDatabase db;

  @Before
  public void createDb() {
    Context context = ApplicationProvider.getApplicationContext();
    db = Room.inMemoryDatabaseBuilder(context, MedRoomDatabase.class).build();
    medDao = db.medDao();
  }

  @After
  public void closeDb() throws IOException {
    db.close();
  }

  @Test
  public void writeUserAndReadInList() {
    Medication med = new Medication("This is your Medication DB", dose);
    medDao.insert(med);
    LiveData<List<Medication>> liveData = medDao.getAllMeds();
    List<Medication> byName = liveData.getValue();

    assertThat(String.valueOf(byName.get(0).getName()), equals(med.getName()));
  }
}


/*public class MedicationInputTest extends junit.framework.TestCase {

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
*/



