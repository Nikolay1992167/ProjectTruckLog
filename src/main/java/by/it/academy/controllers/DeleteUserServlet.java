package by.it.academy.controllers;

import by.it.academy.services.UserService;
import lombok.SneakyThrows;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static by.it.academy.entities.Constants.*;

@WebServlet(urlPatterns = "/delete_user")
public class DeleteUserServlet extends HttpServlet {
    UserService userService = UserService.getInstance();
    @SneakyThrows
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {

        userService.deleteUser(req);
        req.getRequestDispatcher(READ_USERS).forward(req, resp);
    }
}
