package utils;

import com.google.gson.JsonObject;

import java.lang.reflect.InvocationTargetException;
import java.sql.*;

public class JDBConnector {
    
    static {
        CONFIG = JsonReader.getDataFromFile("database_configs.json");
    }

    private static final JsonObject CONFIG;
    private static final String CONNECTION_PARAMS_DRAFT = CONFIG.get("connection_params_draft").getAsString();

    public static Statement getStatement() {
        Connection connection = connectDatabase();
        return createStatement(connection);
    }

    private static Connection connectDatabase() {
        try {
            String driver = CONFIG.get("driver").getAsString();
            Class.forName(driver).getDeclaredConstructor().newInstance();

            String connectionType = CONFIG.get("connection_type").getAsString();
            String address = CONFIG.get("address").getAsString();
            int port = CONFIG.get("port").getAsInt();
            String schema = CONFIG.get("schema").getAsString();

            String connectionParams = String.format(CONNECTION_PARAMS_DRAFT, connectionType, address, port, schema);

            String userName = CONFIG.get("user").getAsString();
            String password = CONFIG.get("password").getAsString();
            return DriverManager.getConnection(connectionParams, userName, password);

        } catch (SQLException | ClassNotFoundException | NoSuchMethodException |
                 InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
            throw new RuntimeException("Problems with connection with database");
        }
    }

    private static Statement createStatement(Connection connection) {
        try {
            return connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Problems with statement creation");
        }
    }

}