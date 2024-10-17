package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnUtil {
    private static Connection connection;

    public static Connection getConnection() {
        if (connection == null) {
            String connString = DBPropertyUtil.getPropertyString("db.properties");
            try {
                connection = DriverManager.getConnection(connString);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }
}
