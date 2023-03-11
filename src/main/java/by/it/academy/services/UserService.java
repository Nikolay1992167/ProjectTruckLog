package by.it.academy.services;

import by.it.academy.dao.UserDAO;
import by.it.academy.database.ConnectionPool;
import by.it.academy.database.ConnectorDB;
import by.it.academy.entities.User;
import by.it.academy.entities.UserType;

import javax.servlet.http.HttpServletRequest;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserService implements UserDAO {
    private static UserService instance;

    private UserService() {
    }

    public static UserService getInstance() {
        if (instance == null) {
            instance = new UserService();
        }
        return instance;
    }

    @Override
    public void creatUser(HttpServletRequest req) throws SQLException{
        Connection connection = ConnectionPool.getConnection();
        //Connection connection = ConnectorDB.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO USERS(COMPANY_NAME, LOC, EMAIL, USER_NAME, USER_PASSWORD, USER_TYPE) VALUES (?, ?, ?, ?, ?, ?)");
            statement.setString(1, req.getParameter("nameCompany"));
            statement.setString(2, req.getParameter("location"));
            statement.setString(3, req.getParameter("email"));
            statement.setString(4, req.getParameter("userName"));
            statement.setString(5, req.getParameter("password"));
            statement.setString(6, req.getParameter("userType"));
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

    @Override
    public List<User> readAllUsers(HttpServletRequest req) throws SQLException{
        Connection connection = ConnectionPool.getConnection();
        //Connection connection = ConnectorDB.getConnection();
        List<User> users = new ArrayList<>();

        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM USERS");
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt(1));
                user.setNameCompany(resultSet.getString(2));
                user.setLocation(resultSet.getString(3));
                user.setEmail(resultSet.getString(4));
                user.setUserName(resultSet.getString(5));
                user.setPassword(resultSet.getString(6));
                user.setUserType(UserType.valueOf(resultSet.getString(7)));
                users.add(user);
                req.setAttribute("users", users);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return users;
    }

    @Override
    public void updateUser(HttpServletRequest req) throws SQLException {
        Connection connection = ConnectionPool.getConnection();
        //Connection connection = ConnectorDB.getConnection();

        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE USERS SET COMPANY_NAME=?, LOC=?, EMAIL=?, USER_NAME=?, USER_PASSWORD=?, USER_TYPE=? WHERE USER_ID=?");
            statement.setString(1, req.getParameter("nameCompany"));
            statement.setString(2, req.getParameter("location"));
            statement.setString(3, req.getParameter("email"));
            statement.setString(4, req.getParameter("userName"));
            statement.setString(5, req.getParameter("password"));
            statement.setString(6, req.getParameter("userType"));
            statement.setInt(7, Integer.parseInt(req.getParameter("id")));
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

    @Override
    public void deleteUser(HttpServletRequest req) throws SQLException{
        Connection connection = ConnectionPool.getConnection();
        //Connection connection = ConnectorDB.getConnection();
        int idUser = Integer.parseInt(req.getParameter("id"));

        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM USERS WHERE USER_ID = ?");

            statement.setInt(1, idUser);

            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }
}
