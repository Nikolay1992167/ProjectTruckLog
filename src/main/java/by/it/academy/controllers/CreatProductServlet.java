package by.it.academy.controllers;

import by.it.academy.services.ProductService;
import lombok.SneakyThrows;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static by.it.academy.entities.Constants.*;

@WebServlet(urlPatterns = "/creat_product")
public class CreatProductServlet extends HttpServlet {

    ProductService productService = ProductService.getInstance();

    @SneakyThrows
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        productService.creatProduct(req);
        req.getRequestDispatcher(READ_PRODUCTS).forward(req, resp);
    }
}
