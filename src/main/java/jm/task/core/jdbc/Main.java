package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserDaoJDBCImpl udj = new UserDaoJDBCImpl();
        udj.createUsersTable();

        udj.saveUser("John", "Samson", (byte) 29);
        udj.saveUser("Mike", "Samson", (byte) 22);
        List<User> users = udj.getAllUsers();
        System.out.println(users);

        udj.removeUserById(1L);
        System.out.println(users);

        udj.dropUsersTable();
        udj.cleanUsersTable();
        System.out.println(users);
//
//
    }
}
