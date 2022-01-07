package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    public UserDaoJDBCImpl() {
    }

    Connection connection = null;

    public void createUsersTable() {
        String create = "CREATE TABLE users("
                + "id BIGINT(64) NOT NULL PRIMARY KEY AUTO_INCREMENT,"
                + "name VARCHAR(255),"
                + "lastName VARCHAR (255),"
                + "age TINYINT(3))";
        connection = Util.getConnection();
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(create);
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        String drop = "DROP TABLE users";
        connection = Util.getConnection();
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(drop);
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String insert = "INSERT INTO users(name, lastName, age) VALUES (?, ?, ?);";
        String query = "SELECT * FROM users";
        connection = Util.getConnection();
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
                User user = new User(name1, lastName1, age1);
            }
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        String remove = "DELETE FROM users WHERE id = ?";
        connection = Util.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(remove)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String query = "SELECT * FROM users";
        connection = Util.getConnection();
        try (Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                String name = rs.getString("name");
                String lastName = rs.getString("lastName");
                Byte age = rs.getByte("age");
                users.add(new User(name, lastName, age));
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public void cleanUsersTable() {
        String clean = "DELETE FROM users";
        connection = Util.getConnection();
        try (Statement statement = connection.createStatement()) {
            statement.execute(clean);
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
