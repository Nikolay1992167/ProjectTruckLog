package by.it.academy.services;

import by.it.academy.database.ConnectionPool;
import by.it.academy.database.ConnectorDB;
import by.it.academy.entities.UserType;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static by.it.academy.entities.Constants.*;

public class ValidationInServletService {

    public boolean checkingEmptyValuesLogin(String login, String password) {
        return (login.equals("") || password.equals(""));
    }

    public void checkingData(HttpSession session, String login, String password) throws SQLException, ClassNotFoundException {
        //Connection connection = ConnectionPool.getInstance().getConnection();
        Connection connection = ConnectorDB.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE username = ? AND password_user = ?");
            statement.setString(1, login);
            statement.setString(2, password);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                UserType userType = UserType.valueOf(rs.getString("usertype"));
                session.setAttribute("userName", login);
                session.setAttribute("password", password);
                session.setAttribute("userType", userType);
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

    public void assignActionsByUserType(HttpSession session, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (null != session.getAttribute("userType")) {
            UserType userType = (UserType) session.getAttribute("userType");
            if (userType == UserType.SUPPLIER) {
                req.getRequestDispatcher(READ_PRODUCTS).forward(req, resp);
            } else if (userType == UserType.ADMIN) {
                req.getRequestDispatcher(READ_USERS).forward(req, resp);
            } else if (userType == UserType.CARRIER) {
                req.getRequestDispatcher(READ_PRODUCTS).forward(req, resp);
            } else {
                req.getRequestDispatcher(INDEX_PAGE).forward(req, resp);
            }
        }
    }

    public boolean checkingEmptyValuesCreatUser(HttpServletRequest req) {
        return (req.getParameter("nameCompany").equals("") || req.getParameter("location").equals("") || req.getParameter("email").equals("") || req.getParameter("userName").equals("") || req.getParameter("password").equals("") || req.getParameter("userType").equals(""));
    }

    public void selectPageForType(HttpSession session, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        switch ((UserType) session.getAttribute("userType")) {
            case ADMIN:
                req.getRequestDispatcher(ADMIN_PAGE).forward(req, resp);
                break;
            case CARRIER:
                req.getRequestDispatcher(CARRIER_PAGE).forward(req, resp);
                break;
            case SUPPLIER:
                req.getRequestDispatcher(SUPPLIER_PAGE).forward(req, resp);
                break;
            default:
                req.getRequestDispatcher(ERROR_PAGE).forward(req, resp);
        }
    }
}
