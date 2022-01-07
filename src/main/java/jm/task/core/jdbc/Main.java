package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserServiceImpl udj = new UserServiceImpl();
        udj.createUsersTable();

        udj.saveUser("John", "Samson", (byte) 29);
        udj.saveUser("Mike", "Samson", (byte) 22);
        List<User> users = udj.getAllUsers();
//        udj.removeUserById(1L);
//        System.out.println(users);
//        udj.dropUsersTable();
//        udj.cleanUsersTable();
        System.out.println(users);
    }
}
