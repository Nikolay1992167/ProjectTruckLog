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

@WebServlet(urlPatterns = "/delete_product")
public class ServletDeleteProduct extends HttpServlet {
    private static final String READ_PRODUCTS = "/read_products";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        try {
            Connection db = ConnectorDB.getConnection();
            PreparedStatement statement = db.prepareStatement("DELETE FROM products WHERE product_id = ?");
            statement.setInt(1, id);
            statement.executeUpdate();
            statement.close();
            db.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        req.getRequestDispatcher(READ_PRODUCTS).forward(req, resp);
    }
}
