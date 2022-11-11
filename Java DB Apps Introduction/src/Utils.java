package src;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import static src.Constants.*;

enum Utils {
    ;
static  Connection getSQLConnection() throws SQLException {
    Properties properties = new Properties();
    properties.setProperty(USER_KEY, USER_VALUE);
    properties.setProperty(PASSWORD_KEY, PASSWORD_VALUE);
    return DriverManager.getConnection(JDBC_URL, properties);
}
}
