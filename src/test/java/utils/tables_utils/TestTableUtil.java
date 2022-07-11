package utils.tables_utils;

import models.TestEntry;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class TestTableUtil extends TableUtil {

    static {
        TEST_TABLE = "test";
    }

    public static void add(TestEntry testEntry) {
        String queryDraft = QUERIES.get("insert_into_test").getAsString();
        String insertQueryDraft = constructInsertQueryDraft(testEntry, queryDraft);
        String insertQuery = String.format(insertQueryDraft, TEST_TABLE);
        try {
            int executeUpdate = STATEMENT.executeUpdate(insertQuery);
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
}
