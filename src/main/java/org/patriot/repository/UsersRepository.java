package org.patriot.repository;

import lombok.SneakyThrows;
import org.patriot.Constants;
import java.sql.*;

public class UsersRepository implements Constants {

    private static final String DB_URL = "jdbc:mysql://" + db_ip + ":" + db_port + "/" + db_name;
    private static final String DB_USERNAME = db_username;
    private static final String DB_PASSWORD = db_password;

    @SneakyThrows
    public UsersRepository() {
        Class.forName("java.sql.DriverManager");
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD)) {

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



}
