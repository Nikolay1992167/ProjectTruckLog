package by.it.academy.services;

import by.it.academy.dao.ProductDAO;
import by.it.academy.database.ConnectionPool;
import by.it.academy.database.ConnectorDB;
import by.it.academy.entities.Product;
import javax.servlet.http.HttpServletRequest;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductService implements ProductDAO {

    public void creatProduct(HttpServletRequest req) throws SQLException, ClassNotFoundException {
        //Connection connection = ConnectionPool.getInstance().getConnection();
        Connection connection = ConnectorDB.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO products(weight, loading_location, unloading_location, cargo_cost) VALUES (?, ?, ?, ?)");
            statement.setInt(1,  Integer.parseInt(req.getParameter("weight")));
            statement.setString(2,req.getParameter("loadingLocation"));
            statement.setString(3, req.getParameter("unloadingLocation"));
            statement.setInt(4, Integer.parseInt(req.getParameter("cargoCost")));
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

    @Override
    public List<Product> readAllProducts(HttpServletRequest req) throws SQLException, ClassNotFoundException {
        //Connection connection = ConnectionPool.getInstance().getConnection();
        Connection connection = ConnectorDB.getConnection();
        List<Product> products = new ArrayList<>();

        Statement statement = null;
        try {
            statement = connection.createStatement();
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
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return products;
    }


    @Override
    public void updateProduct(HttpServletRequest req) throws SQLException, ClassNotFoundException {
        //Connection connection = ConnectionPool.getInstance().getConnection();
        Connection connection = ConnectorDB.getConnection();

        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE products SET weight=?, loading_location=?, unloading_location=?, cargo_cost=? WHERE product_id=?");
            statement.setInt(1,  Integer.parseInt(req.getParameter("weight")));
            statement.setString(2,req.getParameter("loadingLocation"));
            statement.setString(3, req.getParameter("unloadingLocation"));
            statement.setInt(4, Integer.parseInt(req.getParameter("cargoCost")));
            statement.setInt(5, Integer.parseInt(req.getParameter("id")));
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

    @Override
    public void deleteProduct(HttpServletRequest req) throws SQLException, ClassNotFoundException {
        //Connection connection = ConnectionPool.getInstance().getConnection();
        Connection connection = ConnectorDB.getConnection();
        int idProduct = Integer.parseInt(req.getParameter("id"));

        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM products WHERE product_id = ?");

            statement.setInt(1, idProduct);

            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }
}
