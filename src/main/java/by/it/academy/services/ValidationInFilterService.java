package by.it.academy.services;
import by.it.academy.database.ConnectorDB;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static by.it.academy.entities.Constants.*;

public class ValidationInFilterService {
    private static ValidationInFilterService instance;

    private ValidationInFilterService() {
    }

    public static ValidationInFilterService getInstance() {
        if (instance == null) {
            instance = new ValidationInFilterService();
        }
        return instance;
    }



    public void checkStringsForDuplicates(HttpServletRequest req, HttpServletResponse res) throws SQLException, ClassNotFoundException {
        Connection connection = ConnectorDB.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM USERS WHERE COMPANY_NAME = ? OR EMAIL = ? OR USER_NAME = ? OR USER_PASSWORD = ?");
            statement.setString(1, req.getParameter("nameCompany"));
            statement.setString(2, req.getParameter("email"));
            statement.setString(3, req.getParameter("userName"));
            statement.setString(4, req.getParameter("password"));
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                req.getRequestDispatcher(INDEX_PAGE).forward(req, res);
                return;
            }
            statement.close();
        } catch (SQLException | ServletException | IOException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }
}
