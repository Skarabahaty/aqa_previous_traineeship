package utils.sandbox;

import models.TestEntry;

import java.util.ArrayList;
import java.util.List;

public class TestCollectorForUpdate {

    public static List<TestEntry> changedTestEntries = new ArrayList<>();

    public static void updateTestEntries() {
        for (TestEntry testEntry : changedTestEntries) {
            TestEntryUtil.update(testEntry);
        }
    }

    public static boolean isEntriesUpdated() {
        List<TestEntry> testEntriesAfterChangesFromDatabase = new ArrayList<>();
        for (TestEntry changedTestEntry : changedTestEntries) {
            int id = changedTestEntry.getId();
            TestEntry testEntry = TestEntryUtil.selectByID(id);
            testEntriesAfterChangesFromDatabase.add(testEntry);
        }
        boolean equals = TestCase2SelectModifyAndDeleteEntries.initialEntryList.equals(testEntriesAfterChangesFromDatabase);
        return ! equals;
    }

    public static void deleteTestEntries() {
        for (TestEntry testEntry : changedTestEntries) {
            TestEntryUtil.delete(testEntry);
        }
    }

    public static boolean isEntriesDeleted() {
        for (TestEntry changedTestEntry : changedTestEntries) {
            int id = changedTestEntry.getId();
            if (TestEntryUtil.checkIfPresentById(id)) {
                return false;
            }
        }
        return true;
    }
}
