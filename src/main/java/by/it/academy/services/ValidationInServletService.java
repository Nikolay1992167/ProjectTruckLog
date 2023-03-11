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

    private static ValidationInServletService instance;

    private ValidationInServletService() {
    }

    public static ValidationInServletService getInstance() {
        if (instance == null) {
            instance = new ValidationInServletService();
        }
        return instance;
    }

    public void checkingData(HttpSession session, String login, String password) throws SQLException{
        Connection connection = ConnectionPool.getConnection();
        //Connection connection = ConnectorDB.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM USERS WHERE USER_NAME = ? AND USER_PASSWORD = ?");
            statement.setString(1, login);
            statement.setString(2, password);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                UserType userType = UserType.valueOf(rs.getString("user_type"));
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
