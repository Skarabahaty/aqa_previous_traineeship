package utils.tables_utils;

import models.Session;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class SessionTableUtil extends TableUtil {

    static {
        TEST_TABLE = "session";
    }
    private static final String TEST_TABLE;

    public static void add(Session session) {
        String queryDraft = QUERIES.get("insert_into_session").getAsString();
        String insertQueryDraft = constructInsertQueryDraft(session, queryDraft);
        String insertQuery = String.format(insertQueryDraft, TEST_TABLE);
        try {
            STATEMENT.executeUpdate(insertQuery);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Problem wit query");
        }
    }

    private static String constructInsertQueryDraft(Session session, String queryDraft) {
        StringBuilder insertQueryDraft = new StringBuilder(queryDraft);
        insertQueryDraft.append("(");
        List<Serializable> fields = session.getFields();
        int size = fields.size();
        for (int i = 0; i < size; i++) {
            Serializable value = fields.get(i);
            switch (value.getClass().getSimpleName()) {
                case "String":
                    insertQueryDraft.append("'").append(value).append("'");
                    break;
                case "Integer":
                    insertQueryDraft.append(value);
                    break;
            }

            if (size - i > 1) {
                insertQueryDraft.append(", ");
            }
        }
        insertQueryDraft.append(")");
        return insertQueryDraft.toString();
    }

    public static void checkForPresenceAndSetID(Session session) {
        String checkForPresenceQuery = getCheckForPresenceQueryFromSession(session);
        try {
            ResultSet resultSet = STATEMENT.executeQuery(checkForPresenceQuery);
            resultSet.next();
            session.setId(resultSet.getInt("id"));
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Problem wit query");
        }
    }

    /*private static Session getSession(ResultSet resultSet) {
        return new Session(
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
    }*/

    private static String getCheckForPresenceQueryFromSession(Session session) {
        return String.format(
                QUERIES.get("check_session_for_presence").getAsString(),
                TEST_TABLE,
                session.getSessionKey(),
                session.getCreatedTime(),
                session.getBuildNumber());
    }

    public static Session getSessionAndAddItInDB() {
        int anInt = TEST_CONFIGS.get("session_random_number_border").getAsInt();
        Session session = new Session(anInt);
        add(session);
        checkForPresenceAndSetID(session);
        return session;
    }
}
