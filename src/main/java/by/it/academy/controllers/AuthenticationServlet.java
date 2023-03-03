package by.it.academy.controllers;

import by.it.academy.database.ConnectorDB;
import by.it.academy.entities.UserType;
import lombok.SneakyThrows;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.*;

@WebServlet(urlPatterns = {"/authorize"}, loadOnStartup = 0)
public class AuthenticationServlet extends HttpServlet {
    private static final String READ_USERS = "/read_users";
    private static final String READ_PRODUCTS = "/read_products";
    private static final String INDEX_PAGE = "/index.jsp";

    @SneakyThrows
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        String login = req.getParameter("userName");
        String password = req.getParameter("password");
        HttpSession session = req.getSession(true);
        if (login.equals("") && password.equals("")) {
            req.getRequestDispatcher(INDEX_PAGE).forward(req, resp);
        }
        checkIsExist(session, login, password);
        checkSession(session, req, resp);
    }

    private void checkIsExist(HttpSession session, String inlogin, String inpassword) throws SQLException, ClassNotFoundException {
        Connection db = ConnectorDB.getConnection();
        PreparedStatement statement = db.prepareStatement("SELECT * FROM users WHERE username = ? AND password_user = ?");
        statement.setString(1, inlogin);
        statement.setString(2, inpassword);
        ResultSet rs = statement.executeQuery();
        while (rs.next()) {
            UserType userType = UserType.valueOf(rs.getString("usertype"));
            session.setAttribute("userName", inlogin);
            session.setAttribute("password", inpassword);
            session.setAttribute("userType", userType);
        }
        statement.close();
        db.close();
    }

    private void checkSession(HttpSession session, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (null != session.getAttribute("userType")) {
            UserType userType = (UserType) session.getAttribute("userType");
            if (userType == UserType.SUPPLIER){
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
}
