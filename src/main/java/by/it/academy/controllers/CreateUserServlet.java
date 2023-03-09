package by.it.academy.controllers;

import by.it.academy.services.UserService;
import by.it.academy.services.ValidationInServletService;
import lombok.SneakyThrows;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static by.it.academy.entities.Constants.*;

@WebServlet(urlPatterns = {"/create"}, loadOnStartup = 0)
public class CreateUserServlet extends HttpServlet {

    ValidationInServletService service = new ValidationInServletService();
    UserService userService = new UserService();

    @SneakyThrows
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        if (service.checkingEmptyValuesCreatUser(req)) {
            req.getRequestDispatcher(INDEX_PAGE).forward(req, resp);
        } else {
            userService.creatUser(req);
            req.getRequestDispatcher(LOGIN_PAGE).forward(req, resp);
        }
    }
}
