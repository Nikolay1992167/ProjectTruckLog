package by.it.academy.controllers;

import by.it.academy.database.ConnectorDB;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet(urlPatterns = {"/create"}, loadOnStartup = 0)
public class CreateServlet extends HttpServlet {
    private static final String LOGIN_PAGE = "/login.jsp";
    private static final String INDEX_PAGE = "/index.jsp";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String nameCompany = req.getParameter("nameCompany");
        String location = req.getParameter("location");
        String email = req.getParameter("email");
        String userName = req.getParameter("userName");
        String password = req.getParameter("password");
        String userType = req.getParameter("userType");

        if (nameCompany.equals("") && location.equals("") && email.equals("") && userName.equals("") && password.equals("") && userType.equals("")) {
            req.getRequestDispatcher(INDEX_PAGE).forward(req, resp);
        } else {
            try {
                Connection db = ConnectorDB.getConnection();
                PreparedStatement statement = db.prepareStatement("INSERT INTO users(nameCompany, location_adress, email, userName, password_user, userType) VALUES (?, ?, ?, ?, ?, ?)");
                statement.setString(1, nameCompany);
                statement.setString(2, location);
                statement.setString(3, email);
                statement.setString(4, userName);
                statement.setString(5, password);
                statement.setString(6, userType);
                statement.executeUpdate();
                statement.close();
                db.close();
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
            req.getRequestDispatcher(LOGIN_PAGE).forward(req, resp);
        }
    }
}
