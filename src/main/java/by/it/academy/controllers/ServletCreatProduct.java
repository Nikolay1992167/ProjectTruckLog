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

@WebServlet(urlPatterns = "/creat_product")
public class ServletCreatProduct extends HttpServlet {
    private static final String READ_PRODUCTS = "/read_products";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int weight_product = Integer.parseInt(req.getParameter("weight"));
        String loading_location_product = req.getParameter("loading_location");
        String unloading_location_product = req.getParameter("unloading_location");
        int cargo_cost_product = Integer.parseInt(req.getParameter("cargo_cost"));

        try {
            Connection db = ConnectorDB.getConnection();
            PreparedStatement statement = db.prepareStatement("INSERT INTO products(weight, loading_location, unloading_location, cargo_cost) VALUES (?, ?, ?, ?)");
            statement.setInt(1, weight_product);
            statement.setString(2, loading_location_product);
            statement.setString(3, unloading_location_product);
            statement.setInt(4, cargo_cost_product);
            statement.executeUpdate();
            statement.close();
            db.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        req.getRequestDispatcher(READ_PRODUCTS).forward(req, resp);
    }
}
