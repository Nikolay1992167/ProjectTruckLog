package by.it.academy.dao;

import by.it.academy.entities.Product;
import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.List;

public interface ProductDAO {

    void creatProduct(HttpServletRequest req) throws SQLException, ClassNotFoundException;

    List<Product> readAllProducts(HttpServletRequest req) throws SQLException, ClassNotFoundException;

    void updateProduct(HttpServletRequest req) throws SQLException, ClassNotFoundException;

    void deleteProduct(HttpServletRequest req) throws SQLException, ClassNotFoundException;
}
