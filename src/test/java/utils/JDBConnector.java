package utils;

import com.google.gson.JsonObject;
import models.TestEntry;

import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBConnector {
    
    static{
        CONFIG = JsonReader.getDataFromFile("configs.json");
        QUERIES = JsonReader.getDataFromFile("queries.json");
        CONNECTION_PARAMS_DRAFT = "jdbc:mysql://localhost:%d/%s";
        connection = connectDatabase();
        statement = createStatement();
    }

    private static final JsonObject CONFIG;
    private static final JsonObject QUERIES;
    private static final String CONNECTION_PARAMS_DRAFT;
    private static final Connection connection;
    private static final Statement statement;

    public static void add(TestEntry testEntry) {
        String queryDraft = QUERIES.get("insert").getAsString();
        String insertQueryDraft = TestEntryUtil.constructInsertQueryDraft(testEntry, queryDraft);
        String insertQuery = String.format(insertQueryDraft,
                CONFIG.get("table").getAsString());
        try {
            int executeUpdate = statement.executeUpdate(insertQuery);
            System.out.println("Addition successful, number of added entries: " + executeUpdate);
        } catch (SQLException e) {
            System.err.println("Problem with query");
            e.printStackTrace();
        }
    }

    public static TestEntry checkForPresenceAndReturnEntry(TestEntry testEntry) {
        String checkForPresenceQuery = getCheckForPresenceQueryFromTestEntry(testEntry);
        try {
            ResultSet resultSet = statement.executeQuery(checkForPresenceQuery);
            resultSet.next();
            return TestEntryUtil.getTestEntry(resultSet);
        } catch (SQLException e) {
            System.err.println("Problem with query");
            e.printStackTrace();
        }
        return null;
    }

    public static List<TestEntry> getTestEntriesBasedOnID(int randomIntFromZeroToNine) {
        String selectCondition = String.format(
                QUERIES.get("select_condition").getAsString(),
                CONFIG.get("table").getAsString(),
                randomIntFromZeroToNine,
                randomIntFromZeroToNine);
        try {
            ResultSet resultSet = statement.executeQuery(selectCondition);
            List<TestEntry> returnList = new ArrayList<>();
            while (resultSet.next()) {
                returnList.add(
                        TestEntryUtil.getTestEntry(resultSet)
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
                statement.executeUpdate(insertProjectId);
                statement.executeUpdate(insertAuthorId);
                statement.executeUpdate(insertSessionId);
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
                CONFIG.get("table").getAsString(),
                testEntry.getStatusId(),
                testEntry.getStartTime(),
                testEntry.getEndTime(),
                testEntry.getId());
        try {
            statement.executeUpdate(updateQuery);
            System.out.println("Row updated");
        } catch (SQLException e) {
            System.err.println("Problem with query");
            e.printStackTrace();
        }
    }

    public static TestEntry selectByID(int id) {
        String getTestEntryDraft = QUERIES.get("select_by_id").getAsString();
        String getQuery = String.format(getTestEntryDraft, CONFIG.get("table").getAsString(), id);
        try {
            ResultSet resultSet = statement.executeQuery(getQuery);
            resultSet.next();
            return TestEntryUtil.getTestEntry(resultSet);
        } catch (SQLException e) {
            System.err.println("Problem with query");
            e.printStackTrace();
        }
        return null;
    }

    public static void delete(TestEntry testEntry) {
        String deleteTestEntryDraft = QUERIES.get("delete_test_entry").getAsString();
        String deleteQuery = String.format(deleteTestEntryDraft, CONFIG.get("table").getAsString(), testEntry.getId());
        try {
            statement.executeUpdate(deleteQuery);
            System.out.println("Row deleted");
        } catch (SQLException e) {
            System.err.println("Problem with query");
            e.printStackTrace();
        }
    }

    public static boolean checkIfPresentById(int id) {
        String getTestEntryDraft = QUERIES.get("select_by_id").getAsString();
        String getQuery = String.format(getTestEntryDraft, CONFIG.get("table").getAsString(), id);
        try {
            ResultSet resultSet = statement.executeQuery(getQuery);
            return resultSet.next();
        } catch (SQLException e) {
            System.err.println("Problem with query");
            e.printStackTrace();
        }
        return false;
    }

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

    private static Statement createStatement() {
        try {
            return connection.createStatement();
        } catch (SQLException e) {
            System.err.println("Statement creation problem");
            e.printStackTrace();
        }
        return null;
    }

    private static String getCheckForPresenceQueryFromTestEntry(TestEntry testEntry) {
        return String.format(
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
    }

    private static boolean isAdditionNeeded() {
        String selectProjectCheck = QUERIES.get("select_project_check").getAsString();
        String selectAuthorCheck = QUERIES.get("select_author_check").getAsString();
        String selectSessionCheck = QUERIES.get("select_session_check").getAsString();
        try {
            ResultSet projectResultSet = statement.executeQuery(selectProjectCheck);
            boolean projectHasEntries = projectResultSet.next();
            projectResultSet.close();

            ResultSet authorResultSet = statement.executeQuery(selectAuthorCheck);
            boolean authorHasEntries = authorResultSet.next();
            authorResultSet.close();

            ResultSet sessionResultSet = statement.executeQuery(selectSessionCheck);
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