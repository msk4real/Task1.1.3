package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    public UserDaoJDBCImpl() {
    }

    Connection connection = Util.getConnection();

    public void createUsersTable() {
        String create = "CREATE TABLE users("
                + "id BIGINT(64) NOT NULL PRIMARY KEY AUTO_INCREMENT,"
                + "name VARCHAR(255),"
                + "lastName VARCHAR (255),"
                + "age TINYINT(3))";
        try (Statement statement =
                     connection.createStatement()) {
            statement.executeUpdate(create);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        String drop = "DROP TABLE users";
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(drop);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        List<User> users = new ArrayList<>();
        String insert = "INSERT INTO users(name, lastName, age) VALUES (?, ?, ?);";
        String query = "SELECT * FROM users";
        try (PreparedStatement preparedStatement = connection.prepareStatement(insert)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
            PreparedStatement allUsers = connection.prepareStatement(query);
            ResultSet rs = allUsers.executeQuery();
            while (rs.next()) {
                String name1 = rs.getString("name");
                String lastName1 = rs.getString("lastName");
                Byte age1 = rs.getByte("age");
                users.add(new User(name1, lastName1, age1));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        String remove = "DELETE FROM users WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(remove)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String query = "SELECT * FROM users";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String name = rs.getString("name");
                String lastName = rs.getString("lastName");
                Byte age = rs.getByte("age");
                users.add(new User(name, lastName, age));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public void cleanUsersTable() {
        String clean = "DELETE FROM users";
        try (Statement statement = connection.createStatement()) {
            statement.execute(clean);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
