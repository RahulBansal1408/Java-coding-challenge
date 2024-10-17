package util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class DBPropertyUtil {
    public static String getPropertyString(String fileName) {
        Properties prop = new Properties();
        try (FileInputStream input = new FileInputStream(fileName)) {
            prop.load(input);
            String host = prop.getProperty("hostname");
            String dbName = prop.getProperty("dbname");
            String username = prop.getProperty("username");
            String password = prop.getProperty("password");
            String port = prop.getProperty("port");
            return "jdbc:mysql://" + host + ":" + port + "/" + dbName + "?user=" + username + "&password=" + password;
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
