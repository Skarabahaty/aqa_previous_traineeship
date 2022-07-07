package utils;

import com.google.gson.JsonObject;
import models.TestEntry;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.List;
import java.util.Objects;

public class JDBConnector {
    private static final JsonObject CONFIG = JsonReader.getDataFromFile("configs.json");
    private static final JsonObject QUERIES = JsonReader.getDataFromFile("queries.json");
    private static final String CONNECTION_PARAMS_DRAFT = "jdbc:mysql://localhost:%d/%s";
    private static Connection connection = establishDataBaseConnection();
    private static final Statement statement = createStatement(connection);

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

    private static Connection establishDataBaseConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            System.out.println("Connection successful!");

            String connectionParams = String.format(CONNECTION_PARAMS_DRAFT,
                    CONFIG.get("port").getAsInt(),
                    CONFIG.get("schema").getAsString());

            connection = DriverManager.getConnection(connectionParams,
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
            return new TestEntry(
                    resultSet.getString("name"),
                    resultSet.getInt("status_id"),
                    resultSet.getString("method_name"),
                    resultSet.getInt("project_id"),
                    resultSet.getInt("session_id"),
                    resultSet.getString("start_time").replaceAll("\\.[0-9]+", ""),
                    resultSet.getString("end_time").replaceAll("\\.[0-9]+", ""),
                    resultSet.getString("env"),
                    resultSet.getString("browser"));
        } catch (SQLException e) {
            System.err.println("Problem with query");
            e.printStackTrace();
        }
        return null;
    }
}
