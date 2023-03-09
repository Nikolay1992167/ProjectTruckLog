package by.it.academy.services;

import by.it.academy.entities.User;
import by.it.academy.entities.UserType;
import by.it.academy.jpautil.JPAUtil;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static by.it.academy.entities.Constants.*;

public class ValidationInServletService {
    EntityManager entityManager = JPAUtil.getEntityManager();

    public boolean checkingEmptyValuesLogin(String login, String password) {
        return (login.equals("") || password.equals(""));
    }

    public void checkingData(HttpSession session, String login, String password){
        entityManager.getTransaction().begin();
        try {
            User user = (User) entityManager.createQuery("SELECT user FROM User user WHERE user.userName = ?1 OR user.password = ?2")
                    .setParameter(1, login).setParameter(2,password)
                    .getSingleResult();
            while (user != null) {
                UserType userType = user.getUserType();
                session.setAttribute("userName", login);
                session.setAttribute("password", password);
                session.setAttribute("userType", userType);
            }
            entityManager.getTransaction().commit();
        }
        finally {
            entityManager.close();
        }
    }

    public void assignActionsByUserType(HttpSession session, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (null != session.getAttribute("userType")) {
            UserType userType = (UserType) session.getAttribute("userType");
            if (userType == UserType.SUPPLIER) {
                req.getRequestDispatcher(READ_PRODUCTS).forward(req, resp);
            } else if (userType == UserType.ADMIN) {
                req.getRequestDispatcher(READ_USERS).forward(req, resp);
            } else if (userType == UserType.CARRIER) {
                req.getRequestDispatcher(READ_PRODUCTS).forward(req, resp);
            } else {
                req.getRequestDispatcher(INDEX_PAGE).forward(req, resp);
            }
        }
    }

    public boolean checkingEmptyValuesCreatUser(HttpServletRequest req) {
        return (req.getParameter("nameCompany").equals("") || req.getParameter("location").equals("") || req.getParameter("email").equals("") || req.getParameter("userName").equals("") || req.getParameter("password").equals("") || req.getParameter("userType").equals(""));
    }

    public void selectPageForType(HttpSession session, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        switch ((UserType) session.getAttribute("userType")) {
            case ADMIN:
                req.getRequestDispatcher(ADMIN_PAGE).forward(req, resp);
                break;
            case CARRIER:
                req.getRequestDispatcher(CARRIER_PAGE).forward(req, resp);
                break;
            case SUPPLIER:
                req.getRequestDispatcher(SUPPLIER_PAGE).forward(req, resp);
                break;
            default:
                req.getRequestDispatcher(ERROR_PAGE).forward(req, resp);
        }
    }
}
