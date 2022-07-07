package utils;

import com.google.gson.JsonObject;
import models.TestEntry;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class JDBConnector {
    private static final JsonObject CONFIG = JsonReader.getDataFromFile("configs.json");
    private static final JsonObject QUERIES = JsonReader.getDataFromFile("queries.json");
    private static final String CONNECTION_PARAMS_DRAFT = "jdbc:mysql://localhost:%d/%s";
    private static final Connection connection = connectDatabase();
    private static final Statement statement = createStatement(connection);

    private static Connection connectDatabase() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            System.out.println("Connection successful!");

            String connectionParams = String.format(CONNECTION_PARAMS_DRAFT,
                    CONFIG.get("port").getAsInt(),
                    CONFIG.get("schema").getAsString());

            return DriverManager.getConnection(connectionParams,
                    CONFIG.get("user").getAsString(),
                    CONFIG.get("password").getAsString());

        } catch (SQLException e) {
            System.err.println("Query problem");
            e.printStackTrace();

        } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException |
                 IllegalAccessException | InvocationTargetException e) {
            System.err.println("Connection failed");
            e.printStackTrace();

        }
        return connection;
    }

    private static Statement createStatement(Connection connection) {
        try {
            return connection.createStatement();
        } catch (SQLException e) {
            System.err.println("Statement creation problem");
            e.printStackTrace();
        }
        return null;
    }

    private static void constructInsertQueryDraft(List<Serializable> fields, StringBuilder insertQueryDraft) {
        insertQueryDraft.append("(");
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
    }

    public static void addEntity(TestEntry testEntry) {
        List<Serializable> fields = testEntry.getFields();
        StringBuilder insertQueryDraft = new StringBuilder(QUERIES.get("insert").getAsString());

        constructInsertQueryDraft(fields, insertQueryDraft);

        String insertQuery = String.format(insertQueryDraft.toString(),
                CONFIG.get("table").getAsString());

        try {
            int executeUpdate = Objects.requireNonNull(statement).executeUpdate(insertQuery);
            System.out.println("Addition successful, number of added entries: " + executeUpdate);
        } catch (SQLException e) {
            System.err.println("Problem with query");
            e.printStackTrace();
        }
    }

    public static TestEntry checkForPresenceAndReturnEntry(TestEntry testEntry) {
        String checkForPresenceQuery = String.format(
                QUERIES.get("check_for_presence").getAsString(),
                CONFIG.get("table").getAsString(),
                testEntry.getName(),
                testEntry.getStatusId(),
                testEntry.getMethodName(),
                testEntry.getProjectId(),
                testEntry.getSessionId(),
                testEntry.getStartTime(),
                testEntry.getEndTime(),
                testEntry.getEnv(),
                testEntry.getBrowser());

        try {
            ResultSet resultSet = Objects.requireNonNull(statement).executeQuery(checkForPresenceQuery);
            resultSet.next();
            return getTestEntry(resultSet);
        } catch (SQLException e) {
            System.err.println("Problem with query");
            e.printStackTrace();
        }
        return null;
    }

    private static TestEntry getTestEntry(ResultSet resultSet) throws SQLException {
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
                resultSet.getString("browser"));
    }


    public static List<TestEntry> getTestEntriesBasedOnID(int randomIntFromZeroToNine) {
        List<TestEntry> returnList = new ArrayList<>();

        String selectCondition = String.format(
                QUERIES.get("select_condition").getAsString(),
                CONFIG.get("table").getAsString(),
                randomIntFromZeroToNine,
                randomIntFromZeroToNine);

        try {
            ResultSet resultSet = Objects.requireNonNull(statement).executeQuery(selectCondition);
            while(resultSet.next()) {
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

    private static boolean isAdditionNeeded() {
        String selectProjectCheck = QUERIES.get("select_project_check").getAsString();
        String selectAuthorCheck = QUERIES.get("select_author_check").getAsString();
        String selectSessionCheck = QUERIES.get("select_session_check").getAsString();
        try {
            ResultSet projectResultSet = Objects.requireNonNull(statement).executeQuery(selectProjectCheck);
            boolean projectHasEntries = projectResultSet.next();
            projectResultSet.close();

            ResultSet authorResultSet = Objects.requireNonNull(statement).executeQuery(selectAuthorCheck);
            boolean authorHasEntries = authorResultSet.next();
            authorResultSet.close();

            ResultSet sessionResultSet = Objects.requireNonNull(statement).executeQuery(selectSessionCheck);
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

    public static void insertNeededFieldsInTable() {
        if (isAdditionNeeded()) {
            String insertProjectId = QUERIES.get("insert_project_id").getAsString();
            String insertAuthorId = QUERIES.get("insert_author_id").getAsString();
            String insertSessionId = QUERIES.get("insert_session_id").getAsString();
            try {
                Objects.requireNonNull(statement).executeUpdate(insertProjectId);
                Objects.requireNonNull(statement).executeUpdate(insertAuthorId);
                Objects.requireNonNull(statement).executeUpdate(insertSessionId);
                System.out.println("Pre-condition addition successful");
            } catch (SQLException e) {
                System.err.println("Problem with query in pre-condition");
                e.printStackTrace();
            }
        } else {
            System.out.println("Pre-condition addition not needed");
        }
    }
}
