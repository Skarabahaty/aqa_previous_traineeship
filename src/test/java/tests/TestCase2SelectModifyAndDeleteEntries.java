package tests;

import models.TestEntry;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utils.JDBConnector;
import utils.Randomizer;

import java.util.List;

public class TestCase2SelectModifyAndDeleteEntries {

    private List<TestEntry> entryList;

    @DataProvider
    private Object[][] provideData() {
        Object[][] objects = new Object[entryList.size()][1];
        for (int i = 0; i < objects.length; i++) {
            objects[i][1] = entryList.get(i);
        }
        return objects;
    }

    @BeforeMethod
    public void setUp() {
        int randomIntFromZeroToNine = Randomizer.getRandomIntFromZeroToNine();
        entryList = JDBConnector.getTestEntriesBasedOnID(randomIntFromZeroToNine);
    }

    @Test(dataProvider = "provideData")
    public void testImitateTestLauncher(TestEntry testEntry) {

    }
}
