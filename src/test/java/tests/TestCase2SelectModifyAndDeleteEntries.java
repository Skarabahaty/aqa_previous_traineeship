package tests;

import models.TestEntry;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utils.Randomizer;
import utils.TestCollectorForUpdate;
import utils.tables_utils.TestTableUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TestCase2SelectModifyAndDeleteEntries {

    private List<TestEntry> initialEntries;
    private final List<TestEntry> changedEntries = new ArrayList<>();
    private HashMap<String, Integer> iDs;

    @DataProvider
    private Object[][] provideData() {
        Object[][] objects = new Object[initialEntries.size()][2];
        for (int i = 0; i < objects.length; i++) {
            objects[i][0] = initialEntries.get(i);
            objects[i][1] = iDs;
        }
        return objects;
    }

    @BeforeClass
    public void setUp() {
        int randomIntFromZeroToNine = Randomizer.getRandomIntFromZeroToNine();
        initialEntries = TestTableUtil.getTestEntriesBasedOnID(randomIntFromZeroToNine);
        iDs = TestTableUtil.initializeDBAndReturnNeededIDs();
    }

    @Test(dataProvider = "provideData")
    public void testImitateTestLauncher(TestEntry testEntry, HashMap<String, Integer> map) {
        TestEntry testEntryAfterChange = TestTableUtil.simulateTestRuns(testEntry, map);
        changedEntries.add(testEntryAfterChange);
    }

    @AfterClass
    public void tearDown() {
        TestCollectorForUpdate.updateTestEntries(changedEntries);
        boolean isUpdated = TestCollectorForUpdate.isEntriesUpdated(changedEntries, initialEntries);
        Assert.assertTrue(isUpdated, "database isn't updated");

        TestCollectorForUpdate.deleteTestEntries(changedEntries);
        boolean isDeleted = TestCollectorForUpdate.isEntriesDeleted(changedEntries);
        Assert.assertTrue(isDeleted, "needed entries aren't deleted");
    }
}
