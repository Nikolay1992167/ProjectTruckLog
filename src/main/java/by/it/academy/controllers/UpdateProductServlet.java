package by.it.academy.controllers;

import by.it.academy.services.ProductService;
import lombok.SneakyThrows;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static by.it.academy.entities.Constants.*;

@WebServlet(urlPatterns = {"/updateProduct"}, loadOnStartup = 0)
public class UpdateProductServlet extends HttpServlet {
    ProductService productService = new ProductService();

    @SneakyThrows
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        productService.updateProduct(req);
        req.getRequestDispatcher(READ_PRODUCTS).forward(req, resp);
    }
}
