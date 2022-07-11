package utils;

import models.TestEntry;
import utils.tables_utils.TestTableUtil;

import java.util.ArrayList;
import java.util.List;

public class TestCollectorForUpdate {

    public static void updateTestEntries(List<TestEntry> changedEntries) {
        for (TestEntry testEntry : changedEntries) {
            TestTableUtil.update(testEntry);
        }
    }

    public static boolean isEntriesUpdated(List<TestEntry> changedEntries, List<TestEntry> initialEntries) {
        List<TestEntry> testEntriesAfterChangesFromDatabase = new ArrayList<>();
        for (TestEntry changedTestEntry : changedEntries) {
            int id = changedTestEntry.getId();
            TestEntry testEntry = TestTableUtil.selectByID(id);
            testEntriesAfterChangesFromDatabase.add(testEntry);
        }
        boolean equals = initialEntries.equals(testEntriesAfterChangesFromDatabase);
        return ! equals;
    }

    public static void deleteTestEntries(List<TestEntry> changedEntries) {
        for (TestEntry testEntry : changedEntries) {
            TestTableUtil.delete(testEntry);
        }
    }

    public static boolean isEntriesDeleted(List<TestEntry> changedEntries) {
        for (TestEntry changedTestEntry : changedEntries) {
            int id = changedTestEntry.getId();
            if (TestTableUtil.checkIfPresentById(id)) {
                return false;
            }
        }
        return true;
    }
}
