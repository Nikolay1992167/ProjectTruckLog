package by.it.academy.database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectorDB {
    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        final String url = "jdbc:postgresql://127.0.0.1:5432/JPA_Truck";
        final String user = "postgres";
        final String password = "87654321";
        return DriverManager.getConnection(url, user, password);
    }
}
