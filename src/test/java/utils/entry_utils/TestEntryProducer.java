package utils.entry_utils;

import models.TestEntry;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TestEntryProducer {

    private TestEntryProducer() {
    }

    public static TestEntry getTestEntryFromResultSet(ResultSet resultSet) throws SQLException {
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
