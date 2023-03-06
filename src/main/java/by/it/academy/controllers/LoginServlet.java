package by.it.academy.controllers;

import by.it.academy.services.ValidationInServletService;
import lombok.SneakyThrows;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import static by.it.academy.entities.Constants.*;

@WebServlet(urlPatterns = {"/authorize"}, loadOnStartup = 0)
public class LoginServlet extends HttpServlet {

    ValidationInServletService service = new ValidationInServletService();

    @SneakyThrows
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        String login = req.getParameter("userName");
        String password = req.getParameter("password");
        HttpSession session = req.getSession(true);

        if (service.checkingEmptyValuesLogin(login, password)) {
            req.getRequestDispatcher(INDEX_PAGE).forward(req, resp);
        } else{
            service.checkingData(session, login, password);
            service.assignActionsByUserType(session, req, resp);
        }
    }
}
