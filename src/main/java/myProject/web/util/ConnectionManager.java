package myProject.web.util;

import lombok.experimental.UtilityClass;
import myProject.web.model.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@UtilityClass
public class ConnectionManager {
    private static final String URL_PATH = "db.url";
    private static final String USER_PATH = "db.user";
    private static final String PASSWORD_PATH = "db.password";

    static {
        try {
            loadDriver();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadDriver() throws ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                PropertiesUtil.getByKey(URL_PATH),
                PropertiesUtil.getByKey(USER_PATH),
                PropertiesUtil.getByKey(PASSWORD_PATH)
        );
    }

}
