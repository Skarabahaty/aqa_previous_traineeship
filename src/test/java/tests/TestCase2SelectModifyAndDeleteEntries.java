package tests;

import listeners.TestCollectorForUpdate;
import models.TestEntry;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utils.JDBConnector;
import utils.Randomizer;
import utils.TestEntryUtil;

import java.util.List;

public class TestCase2SelectModifyAndDeleteEntries {

    public static List<TestEntry> initialEntryList;

    @DataProvider
    private Object[][] provideData() {
        Object[][] objects = new Object[initialEntryList.size()][1];
        for (int i = 0; i < objects.length; i++) {
            objects[i][0] = initialEntryList.get(i);
        }
        return objects;
    }

    @BeforeClass
    public void setUp() {
        JDBConnector.insertNeededFieldsInTable();
        int randomIntFromZeroToNine = Randomizer.getRandomIntFromZeroToNine();
        initialEntryList = JDBConnector.getTestEntriesBasedOnID(randomIntFromZeroToNine);
    }

    @Test(dataProvider = "provideData")
    public void testImitateTestLauncher(TestEntry testEntry) {
        TestEntry testEntryAfterChange = TestEntryUtil.simulateTestRun(testEntry);
        TestCollectorForUpdate.changedTestEntries.add(testEntryAfterChange);
    }

    @AfterClass
    public void tearDown() {
        TestCollectorForUpdate.updateTestEntries();
        boolean isUpdated = TestCollectorForUpdate.isEntriesUpdated();
        Assert.assertTrue(isUpdated, "database isn't updated");

        TestCollectorForUpdate.deleteTestEntries();
        boolean isDeleted = TestCollectorForUpdate.isEntriesDeleted();
        Assert.assertTrue(isDeleted, "needed entries aren't deleted");
    }
}
