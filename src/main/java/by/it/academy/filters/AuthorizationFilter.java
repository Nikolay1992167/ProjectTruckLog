package by.it.academy.filters;

import by.it.academy.database.ConnectorDB;
import lombok.SneakyThrows;

import javax.servlet.FilterChain;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@WebFilter(urlPatterns = {"/create"})
public class AuthorizationFilter extends HttpFilter {
    private static final String INDEX_PAGE = "/index.jsp";

    @SneakyThrows
    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) {
        String nameCompany = req.getParameter("nameCompany");
        String email = req.getParameter("email");
        String login = req.getParameter("userName");
        String password = req.getParameter("password");

        Connection db = ConnectorDB.getConnection();
        PreparedStatement statement = db.prepareStatement("SELECT * FROM users WHERE namecompany = ? OR email = ? OR username = ? OR password_user = ?");
        statement.setString(1, nameCompany);
        statement.setString(2, email);
        statement.setString(3, login);
        statement.setString(4, password);
        ResultSet rs = statement.executeQuery();
        while (rs.next()) {
            req.getRequestDispatcher(INDEX_PAGE).forward(req, res);
            return;
        }
        chain.doFilter(req, res);
    }

    @Override
    public void destroy() {
    }
}
