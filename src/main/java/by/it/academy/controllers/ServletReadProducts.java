package by.it.academy.controllers;
import by.it.academy.database.ConnectorDB;
import by.it.academy.entities.Product;
import by.it.academy.entities.UserType;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = "/read_products")
public class ServletReadProducts extends HttpServlet {
    private static final String ADMIN_PAGE = "/admin.jsp";
    private static final String CARRIER_PAGE = "/carrier.jsp";
    private static final String SUPPLIER_PAGE = "/supplier.jsp";
    private static final String ERROR_PAGE = "/404.jsp";
    List<Product> products = new ArrayList<>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(true);
        try {
            Connection db = ConnectorDB.getConnection();
            Statement statement = db.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM products");
            while (resultSet.next()) {
                Product product = new Product();
                product.setId(resultSet.getInt(1));
                product.setWeight(resultSet.getInt(2));
                product.setLoading_location(resultSet.getString(3));
                product.setUnloading_location(resultSet.getString(4));
                product.setCargo_cost(resultSet.getInt(5));
                products.add(product);
                req.setAttribute("products", products);
            }
            statement.close();
            db.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        if (session.getAttribute("userType").equals(UserType.ADMIN)) {
            req.getRequestDispatcher(ADMIN_PAGE).forward(req, resp);
        }
        if (session.getAttribute("userType").equals(UserType.CARRIER)) {
            req.getRequestDispatcher(CARRIER_PAGE).forward(req, resp);
        }
        if (session.getAttribute("userType").equals(UserType.SUPPLIER)) {
            req.getRequestDispatcher(SUPPLIER_PAGE).forward(req, resp);
        } else {
            req.getRequestDispatcher(ERROR_PAGE).forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
