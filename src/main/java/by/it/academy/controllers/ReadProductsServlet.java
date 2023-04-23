package by.it.academy.controllers;

import by.it.academy.services.ProductService;
import by.it.academy.services.ValidationInServletService;
import lombok.SneakyThrows;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(urlPatterns = "/read_products")
public class ReadProductsServlet extends HttpServlet {

   ProductService productService = ProductService.getInstance();
   ValidationInServletService service = ValidationInServletService.getInstance();

    @SneakyThrows
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession(true);
        productService.readAllProducts(req);
        service.selectPageForType(session,req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp){
        doGet(req, resp);
    }
}
