package by.it.academy.controllers;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import static by.it.academy.entities.Constants.*;

@WebServlet(urlPatterns = {"/logout"}, loadOnStartup = 0)
public class LogoutServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        final HttpSession session = req.getSession();
        session.removeAttribute("userName");
        session.removeAttribute("password");
        session.removeAttribute("userType");
        resp.sendRedirect(super.getServletContext().getContextPath());
        req.getRequestDispatcher(INDEX_PAGE).forward(req, resp);
    }
}
