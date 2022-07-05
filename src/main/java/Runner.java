import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Runner {

    public static void main(String[] args) {
        Connection connection = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            System.out.println("Connection successful!");

            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/union_reporting", "root", "31415");

            ResultSet resultSet = connection.createStatement().executeQuery("select * from status");

            while (resultSet.next()) {
                String id = resultSet.getString("id");
                String name = resultSet.getString("name");
                System.out.printf("%s - %s%n", id, name);
            }


        } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException |
                 InvocationTargetException e) {
            System.err.println("Connection failed");
            System.err.println(e);
        } catch (SQLException e) {
            System.err.println("Query problem");
            System.err.println(e);
        }
    }

}
