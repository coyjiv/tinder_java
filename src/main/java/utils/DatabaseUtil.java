package utils;

import io.github.cdimascio.dotenv.Dotenv;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseUtil {
    private static final Dotenv dotenv = Dotenv.load();
    private static final String DB_CONNECTION_URL = dotenv.get("DB_CONNECTION_URL");
    private static final String DB_LOGIN = dotenv.get("DB_LOGIN");
    private static final String DB_PASSWORD = dotenv.get("DB_PASSWORD");

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_CONNECTION_URL, DB_LOGIN, DB_PASSWORD);
    }
}
