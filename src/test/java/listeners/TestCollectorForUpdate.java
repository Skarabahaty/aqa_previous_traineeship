package listeners;

import models.TestEntry;
import tests.TestCase2SelectModifyAndDeleteEntries;
import utils.JDBConnector;

import java.util.ArrayList;
import java.util.List;

public class TestCollectorForUpdate {

    public static List<TestEntry> changedTestEntries = new ArrayList<>();

    public static void updateTestEntries() {
        for (TestEntry testEntry : changedTestEntries) {
            JDBConnector.update(testEntry);
        }
    }

    public static boolean isEntriesUpdated() {
        List<TestEntry> testEntriesAfterChangesFromDatabase = new ArrayList<>();
        for (TestEntry changedTestEntry : changedTestEntries) {
            int id = changedTestEntry.getId();
            TestEntry testEntry = JDBConnector.selectByID(id);
            testEntriesAfterChangesFromDatabase.add(testEntry);
        }
        boolean equals = TestCase2SelectModifyAndDeleteEntries.initialEntryList.equals(testEntriesAfterChangesFromDatabase);
        return ! equals;
    }

    public static void deleteTestEntries() {
        for (TestEntry testEntry : changedTestEntries) {
            JDBConnector.delete(testEntry);
        }
    }

    public static boolean isEntriesDeleted() {
        for (TestEntry changedTestEntry : changedTestEntries) {
            int id = changedTestEntry.getId();
            if (JDBConnector.checkIfPresentById(id)) {
                return false;
            }
        }
        return true;
    }
}
