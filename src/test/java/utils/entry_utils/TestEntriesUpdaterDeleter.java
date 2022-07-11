package utils.entry_utils;

import models.TestEntry;
import utils.tables_utils.TestTableUtil;

import java.util.ArrayList;
import java.util.List;

public class TestEntriesUpdaterDeleter {

    public static void updateTestEntries(List<TestEntry> changedEntries) {
        changedEntries.forEach(TestTableUtil::update);
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
        changedEntries.forEach(TestTableUtil::delete);
    }

    public static boolean isEntriesDeleted(List<TestEntry> changedEntries) {
        return changedEntries.stream()
                .mapToInt(TestEntry::getId)
                .noneMatch(TestTableUtil::checkIfPresentById);
    }
}
