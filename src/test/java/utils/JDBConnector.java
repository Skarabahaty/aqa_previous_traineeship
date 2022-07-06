package utils;

import com.google.gson.JsonObject;
import models.TestEntry;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class JDBConnector {
    private static final JsonObject CONFIG = JsonReader.getDataFromFile("configs.json");
    private static final JsonObject QUERIES = JsonReader.getDataFromFile("queries.json");
    private static final String CONNECTION_PARAMS_DRAFT = "jdbc:mysql://localhost:%d/%s";
    private static Connection connection = establishDataBaseConnection();
    private static Statement statement = createStatement(connection);

    public static void addEntity(TestEntry testEntry) {
        List<Serializable> fields = testEntry.getFields();
        StringBuilder insertQueryDraft = new StringBuilder(QUERIES.get("insert").getAsString());

        constructInsertQueryDraft(fields, insertQueryDraft);

        String insertQuery = String.format(insertQueryDraft.toString(),
                CONFIG.get("table").getAsString());

        try {
            int executeUpdate = statement.executeUpdate(insertQuery);
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
}
