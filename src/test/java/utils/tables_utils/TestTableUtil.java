package utils.tables_utils;

import models.Author;
import models.Project;
import models.Session;
import models.TestEntry;
import utils.Randomizer;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class TestTableUtil extends TableUtil {

    static {
        TEST_TABLE = "test";
    }

    private static final String TEST_TABLE;

    public static void add(TestEntry testEntry) {
        String queryDraft = QUERIES.get("insert_into_test").getAsString();
        String insertQueryDraft = constructInsertQueryDraft(testEntry, queryDraft);
        String insertQuery = String.format(insertQueryDraft, TEST_TABLE);
        try {
            STATEMENT.executeUpdate(insertQuery);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Problem wit query");
        }
    }

    private static String constructInsertQueryDraft(TestEntry testEntry, String queryDraft) {
        StringBuilder insertQueryDraft = new StringBuilder(queryDraft);
        insertQueryDraft.append("(");
        List<Serializable> fields = testEntry.getFields();
        int size = fields.size();
        for (int i = 0; i < size; i++) {
            Serializable serializable = fields.get(i);
            switch (serializable.getClass().getSimpleName()) {
                case "String":
                case "Timestamp":
                    insertQueryDraft.append("'").append(serializable).append("'");
                    break;
                case "Integer":
                case "Long":
                    insertQueryDraft.append(serializable);
                    break;
            }

            if (size - i > 1) {
                insertQueryDraft.append(", ");
            }
        }
        insertQueryDraft.append(");");
        return insertQueryDraft.toString();
    }

    public static TestEntry checkForPresenceAndReturnEntry(TestEntry testEntry) {
        String checkForPresenceQuery = getCheckForPresenceQueryFromTestEntry(testEntry);
        try {
            ResultSet resultSet = STATEMENT.executeQuery(checkForPresenceQuery);
            resultSet.next();
            return getTestEntry(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Problem wit query");
        }
    }

    private static String getCheckForPresenceQueryFromTestEntry(TestEntry testEntry) {
        return String.format(
                QUERIES.get("check_test_for_presence").getAsString(),
                TEST_TABLE,
                testEntry.getName(),
                testEntry.getStatusId(),
                testEntry.getMethodName(),
                testEntry.getProjectId(),
                testEntry.getSessionId(),
                testEntry.getStartTime(),
                testEntry.getEndTime(),
                testEntry.getEnv(),
                testEntry.getBrowser());
    }

    public static TestEntry getTestEntry(ResultSet resultSet) throws SQLException { //FIXME
        return new TestEntry(
                resultSet.getInt("id"),
                resultSet.getString("name"),
                resultSet.getInt("status_id"),
                resultSet.getString("method_name"),
                resultSet.getInt("project_id"),
                resultSet.getInt("session_id"),
                resultSet.getString("start_time").replaceAll("\\.\\d+", ""),
                resultSet.getString("end_time").replaceAll("\\.\\d+", ""),
                resultSet.getString("env"),
                resultSet.getString("browser"),
                resultSet.getInt("author_id"));
    }

    public static HashMap<String, Integer> initializeDBAndReturnNeededIDs() {
        Session session = SessionTableUtil.getSessionAndAddItInDB();
        Project project = ProjectTableUtil.getProjectAndAddTiInDB();
        Author author = AuthorTableUtil.getAuthorAndAddItInDB();

       return new HashMap<>(
                Map.of("session", session.getId(),
                        "project", project.getId(),
                        "author", author.getId()));
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
            e.printStackTrace();
            throw new RuntimeException("Problem with query");
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
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Problem with query");
        }
    }

    public static TestEntry simulateTestRuns(TestEntry testEntry, HashMap<String, Integer> map) {
        TestEntry clone = testEntry.clone();
        clone.setAuthorId(map.get("author"));
        clone.setProjectId(map.get("project"));
        clone.setSessionId(map.get("session"));
        clone.setStatusId(Randomizer.getRandomIntFromOneToBorder(3));
        clone.setStartTime(Calendar.getInstance().getTimeInMillis());
        clone.setEndTime(Calendar.getInstance().getTimeInMillis() + 10000);
        return clone;
    }


    public static TestEntry selectByID(int id) {
        String getTestEntryDraft = QUERIES.get("select_by_id").getAsString();
        String getQuery = String.format(getTestEntryDraft, TEST_TABLE, id);
        try {
            ResultSet resultSet = STATEMENT.executeQuery(getQuery);
            resultSet.next();
            return getTestEntry(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Problem with query");
        }
    }

    public static void delete(TestEntry testEntry) {
        String deleteTestEntryDraft = QUERIES.get("delete_test_entry").getAsString();
        String deleteQuery = String.format(deleteTestEntryDraft, TEST_TABLE, testEntry.getId());
        try {
            STATEMENT.executeUpdate(deleteQuery);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Problem with query");
        }
    }

    public static boolean checkIfPresentById(int id) {
        String getTestEntryDraft = QUERIES.get("select_by_id").getAsString();
        String getQuery = String.format(getTestEntryDraft, TEST_TABLE, id);
        try {
            ResultSet resultSet = STATEMENT.executeQuery(getQuery);
            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Problem with query");
        }
    }


    public static TestEntry getTestEntry(Session session, Author author, Project project) {
        String browser = TEST_CONFIGS.get("browser").getAsString();
        String env = TEST_CONFIGS.get("env").getAsString();
        String testName = TEST_CONFIGS.get("test_name").getAsString();

        return new TestEntry(testName, session.getId(), project.getId(), author.getId(), browser, env);
    }


}
