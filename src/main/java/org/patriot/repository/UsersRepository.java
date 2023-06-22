package org.patriot.repository;

import lombok.SneakyThrows;
import org.patriot.Constants;
import org.patriot.repository.exception.UserNotFoundException;
import java.sql.*;

public class UsersRepository implements Constants {

    private static final String DB_URL = "jdbc:mysql://" + db_ip + ":" + db_port + "/" + db_name;
    private static final String DB_USERNAME = db_username;
    private static final String DB_PASSWORD = db_password;

    @SneakyThrows
    public UsersRepository() {
        Class.forName("java.sql.DriverManager");
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD)) {
            createTableIfNotExists(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createUser(PatriotUser user) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
             Statement statement = connection.createStatement()) {
            createTableIfNotExists(connection);

            String id = user.getId();
            int money = user.getMoney();

            String query = String.format("INSERT INTO users (id, money) VALUES ('%s', %d)", id, money);
            statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public PatriotUser getUser(String id) throws UserNotFoundException {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
             Statement statement = connection.createStatement()) {
             createTableIfNotExists(connection);

             String query = String.format("SELECT * FROM users WHERE id = '%s'", id);
             ResultSet resultSet = statement.executeQuery(query);

            if (resultSet.next()) {
                int money = resultSet.getInt("money");
                return new PatriotUser(id, money);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        throw new UserNotFoundException(id);
    }

    public void addUserMoney(String id, int money) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
             Statement statement = connection.createStatement()) {
             createTableIfNotExists(connection);

             String query = String.format("UPDATE users SET money = money + %d WHERE id = '%s'", money, id);
             statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean isUserExistsInDatabase(String id) {
        try {
            getUser(id);
            return true;
        } catch (UserNotFoundException e) {
            return false;
        }
    }

    private void createTableIfNotExists(Connection connection) throws SQLException {
        String query = "CREATE TABLE IF NOT EXISTS users (id VARCHAR(255) PRIMARY KEY, money INT)";
        Statement statement = connection.createStatement();
        statement.executeUpdate(query);
    }
}
