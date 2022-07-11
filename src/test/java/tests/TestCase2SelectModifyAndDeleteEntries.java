package tests;

import models.TestEntry;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utils.Randomizer;
import utils.entry_utils.TestEntriesUpdaterDeleter;
import utils.entry_utils.TestRunSimulator;
import utils.tables_utils.CommonUtil;
import utils.tables_utils.TestTableUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TestCase2SelectModifyAndDeleteEntries {

    {
        changedEntries = new ArrayList<>();
    }

    private List<TestEntry> initialEntries;
    private final List<TestEntry> changedEntries;
    private Map<String, Integer> iDs;

    @BeforeClass
    public void setUp() {
        do {
            int randomIntFromZeroToNine = Randomizer.getRandomIntFromZeroToNine();
            initialEntries = TestTableUtil.getTestEntriesBasedOnID(randomIntFromZeroToNine);
        } while (initialEntries.size() == 0);

        iDs = CommonUtil.initializeDatabaseAndReturnNeededIDs();
    }

    @DataProvider
    private Object[][] provideData() {
        Object[][] objects = new Object[initialEntries.size()][2];
        for (int i = 0; i < objects.length; i++) {
            objects[i][0] = initialEntries.get(i);
            objects[i][1] = iDs;
        }
        return objects;
    }

    @Test(dataProvider = "provideData")
    public void testImitateTestLauncher(TestEntry testEntry, Map<String, Integer> map) {
        TestEntry testEntryAfterChange = TestRunSimulator.simulateTestRuns(testEntry, map);
        changedEntries.add(testEntryAfterChange);
    }

    @AfterClass
    public void tearDown() {
        TestEntriesUpdaterDeleter.updateTestEntries(changedEntries);
        boolean isUpdated = TestEntriesUpdaterDeleter.isEntriesUpdated(changedEntries, initialEntries);
        Assert.assertTrue(isUpdated, "database isn't updated");

        TestEntriesUpdaterDeleter.deleteTestEntries(changedEntries);
        boolean isDeleted = TestEntriesUpdaterDeleter.isEntriesDeleted(changedEntries);
        Assert.assertTrue(isDeleted, "needed entries aren't deleted");
    }
}
