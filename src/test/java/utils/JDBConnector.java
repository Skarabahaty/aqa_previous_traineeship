package utils;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBConnector {

    private Connection connection = null;
    private final String CONNECTION_PARAMS_DRAFT = "jdbc:mysql://localhost:%d/%s";

    private Connection establishDataBaseConnection() {
        if (connection == null) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
                System.out.println("Connection successful!");
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/union_reporting", "root", "31415");
            } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException |
                     InvocationTargetException e) {
                System.err.println("Connection failed");
                System.err.println(e);
            } catch (
                    SQLException e) {
                System.err.println("Query problem");
                System.err.println(e);
            }
        }
        return connection;
    }

}
