package by.it.academy.controllers;

import by.it.academy.services.ProductService;
import lombok.SneakyThrows;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static by.it.academy.entities.Constants.*;

@WebServlet(urlPatterns = "/delete_product")
public class DeleteProductServlet extends HttpServlet {

    ProductService productService;

    @SneakyThrows
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        productService.deleteProduct(req);
        req.getRequestDispatcher(READ_PRODUCTS).forward(req, resp);
    }
}
