package utils.sandbox;

import models.TestEntry;
import utils.JsonReader;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class TestEntryUtil {

    private static final String TEST_TABLE = JsonReader.getDataFromFile("queries.json").get("table").getAsString();

    public static TestEntry simulateTestRun(TestEntry testEntry) {
        TestEntry clone = testEntry.clone();
        clone.setAuthorId(1);
        clone.setProjectId(7);
        clone.setStatusId(ThreadLocalRandom.current().nextInt(1, 4));
        clone.setStartTime(Calendar.getInstance().getTimeInMillis());
        clone.setEndTime(Calendar.getInstance().getTimeInMillis() + 10000);
        return clone;
    }





    public static List<TestEntry> getTestEntriesBasedOnID(int randomIntFromZeroToNine) {
        String selectCondition = String.format(
                QUERIES.get("select_condition").getAsString(),
                TEST_TABLE,
                randomIntFromZeroToNine,
                randomIntFromZeroToNine);
        try {
            ResultSet resultSet = STATEMENT.executeQuery(selectCondition);
            List<TestEntry> returnList = new ArrayList<>();
            while (resultSet.next()) {
                returnList.add(
                        getTestEntry(resultSet)
                );
            }
            return returnList;
        } catch (SQLException e) {
            System.err.println("Problem with query");
            e.printStackTrace();
        }
        return null;
    }

    public static void insertNeededFieldsInTable() {
        if (isAdditionNeeded()) {
            String insertProjectId = QUERIES.get("insert_project_id").getAsString();
            String insertAuthorId = QUERIES.get("insert_author_id").getAsString();
            String insertSessionId = QUERIES.get("insert_session_id").getAsString();
            try {
                STATEMENT.executeUpdate(insertProjectId);
                STATEMENT.executeUpdate(insertAuthorId);
                STATEMENT.executeUpdate(insertSessionId);
                System.out.println("Pre-condition addition successful");
            } catch (SQLException e) {
                System.err.println("Problem with query in pre-condition");
                e.printStackTrace();
            }
        } else {
            System.out.println("Pre-condition addition not needed");
        }
    }

    public static void update(TestEntry testEntry) {
        String updateQuery = String.format(
                QUERIES.get("update_test_entry").getAsString(),
                TEST_TABLE,
                testEntry.getStatusId(),
                testEntry.getStartTime(),
                testEntry.getEndTime(),
                testEntry.getId());
        try {
            STATEMENT.executeUpdate(updateQuery);
            System.out.println("Row updated");
        } catch (SQLException e) {
            System.err.println("Problem with query");
            e.printStackTrace();
        }
    }

    public static TestEntry selectByID(int id) {
        String getTestEntryDraft = QUERIES.get("select_by_id").getAsString();
        String getQuery = String.format(getTestEntryDraft, TEST_TABLE, id);
        try {
            ResultSet resultSet = STATEMENT.executeQuery(getQuery);
            resultSet.next();
            return getTestEntry(resultSet);
        } catch (SQLException e) {
            System.err.println("Problem with query");
            e.printStackTrace();
        }
        return null;
    }

    public static void delete(TestEntry testEntry) {
        String deleteTestEntryDraft = QUERIES.get("delete_test_entry").getAsString();
        String deleteQuery = String.format(deleteTestEntryDraft, TEST_TABLE, testEntry.getId());
        try {
            STATEMENT.executeUpdate(deleteQuery);
            System.out.println("Row deleted");
        } catch (SQLException e) {
            System.err.println("Problem with query");
            e.printStackTrace();
        }
    }

    public static boolean checkIfPresentById(int id) {
        String getTestEntryDraft = QUERIES.get("select_by_id").getAsString();
        String getQuery = String.format(getTestEntryDraft, TEST_TABLE, id);
        try {
            ResultSet resultSet = STATEMENT.executeQuery(getQuery);
            return resultSet.next();
        } catch (SQLException e) {
            System.err.println("Problem with query");
            e.printStackTrace();
        }
        return false;
    }


    private static boolean isAdditionNeeded() {
        String selectProjectCheck = QUERIES.get("select_project_check").getAsString();
        String selectAuthorCheck = QUERIES.get("select_author_check").getAsString();
        String selectSessionCheck = QUERIES.get("select_session_check").getAsString();
        try {
            ResultSet projectResultSet = STATEMENT.executeQuery(selectProjectCheck);
            boolean projectHasEntries = projectResultSet.next();
            projectResultSet.close();

            ResultSet authorResultSet = STATEMENT.executeQuery(selectAuthorCheck);
            boolean authorHasEntries = authorResultSet.next();
            authorResultSet.close();

            ResultSet sessionResultSet = STATEMENT.executeQuery(selectSessionCheck);
            boolean sessionHasEntries = sessionResultSet.next();
            sessionResultSet.close();

            if (projectHasEntries && authorHasEntries && sessionHasEntries) {
                return false;
            } else {
                System.out.println("Performing pre-condition addition");
                return true;
            }
        } catch (SQLException e) {
            System.err.println("Problem with query in pre-condition");
            e.printStackTrace();
        }
        return false;
    }
}
