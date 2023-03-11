package by.it.academy.database;

import org.apache.commons.dbcp2.BasicDataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionPool {

        private static final BasicDataSource ds = new BasicDataSource();

        static {
            ds.setUrl("jdbc:postgresql://127.0.0.1:5432/company");
            ds.setUsername("postgres");
            ds.setPassword("87654321");
            ds.setMinIdle(5);
            ds.setMaxIdle(10);
            ds.setMaxOpenPreparedStatements(100);
        }

        public static Connection getConnection() throws SQLException {
            return ds.getConnection();
        }

        private ConnectionPool(){ }
}
