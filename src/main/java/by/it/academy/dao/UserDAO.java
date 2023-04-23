package by.it.academy.dao;

import by.it.academy.entities.User;
import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.List;

public interface UserDAO {

    void creatUser(HttpServletRequest req) throws SQLException, ClassNotFoundException;

    List<User> readAllUsers(HttpServletRequest req) throws SQLException, ClassNotFoundException;

    void updateUser(HttpServletRequest req) throws SQLException, ClassNotFoundException;

    void deleteUser(HttpServletRequest req) throws SQLException, ClassNotFoundException;
}
