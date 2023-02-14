package by.it.academy.controllers;
import by.it.academy.database.ConnectorDB;
import by.it.academy.entities.User;
import by.it.academy.entities.UserType;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = "/read_users")
public class ServletReadUsers extends HttpServlet {
    private static final String READ_PRODUCTS = "/read_products";
    List<User> users = new ArrayList<>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Connection db = ConnectorDB.getConnection();
            Statement statement = db.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM users");
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
            statement.close();
            db.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        req.getRequestDispatcher(READ_PRODUCTS).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
