package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserServiceImpl userService = new UserServiceImpl();
        userService.createUsersTable();

        userService.saveUser("John", "Samson", (byte) 29);
        userService.saveUser("Mike", "Samson", (byte) 22);
        List<User> users = userService.getAllUsers();
        userService.removeUserById(1L);
        System.out.println(users);
        userService.dropUsersTable();
        userService.cleanUsersTable();
        System.out.println(users);
    }
}
