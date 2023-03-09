package by.it.academy.services;
import by.it.academy.entities.User;
import by.it.academy.jpautil.JPAUtil;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


import static by.it.academy.entities.Constants.*;

public class ValidationInFilterService {
    EntityManager entityManager = JPAUtil.getEntityManager();


    public void checkForDuplicates(HttpServletRequest req, HttpServletResponse res) {
        entityManager.getTransaction().begin();
        User user = null;
        try {
             user = (User) entityManager.createQuery("SELECT user FROM User user WHERE user.nameCompany = ?1 OR user.email = ?2 OR user.userName = ?3 OR user.password = ?4")
                    .setParameter(1, req.getParameter("nameCompany"))
                    .setParameter(2,req.getParameter("email"))
                    .setParameter(3,req.getParameter("userName"))
                    .setParameter(4, req.getParameter("password"))
                    .getSingleResult();
            if (user != null) {
                req.getRequestDispatcher(INDEX_PAGE).forward(req, res);
                return;
            }
            entityManager.getTransaction().commit();
        } catch (ServletException | IOException | NoResultException e) {
            e.printStackTrace();
        }  finally {
            entityManager.close();
        }
    }
}
