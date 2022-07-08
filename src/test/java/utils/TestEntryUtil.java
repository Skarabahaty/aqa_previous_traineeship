package utils;

import models.TestEntry;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class TestEntryUtil {

    public static TestEntry simulateTestRun(TestEntry testEntry) {
        TestEntry clone = testEntry.clone();
        clone.setAuthorId(1);
        clone.setProjectId(7);
        clone.setStatusId(ThreadLocalRandom.current().nextInt(1, 4));
        clone.setStartTime(Calendar.getInstance().getTimeInMillis());
        clone.setEndTime(Calendar.getInstance().getTimeInMillis() + 10000);
        return clone;
    }

    public static String constructInsertQueryDraft(TestEntry testEntry, String queryDraft) {
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

    public static TestEntry getTestEntry(ResultSet resultSet) throws SQLException {
        return new TestEntry(
                resultSet.getInt("id"),
                resultSet.getString("name"),
                resultSet.getInt("status_id"),
                resultSet.getString("method_name"),
                resultSet.getInt("project_id"),
                resultSet.getInt("session_id"),
                resultSet.getString("start_time").replaceAll("\\.[0-9]+", ""),
                resultSet.getString("end_time").replaceAll("\\.[0-9]+", ""),
                resultSet.getString("env"),
                resultSet.getString("browser"),
                resultSet.getInt("author_id"));
    }
}
