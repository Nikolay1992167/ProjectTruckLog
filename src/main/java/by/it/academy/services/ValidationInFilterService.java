package by.it.academy.services;

import by.it.academy.entities.User;
import by.it.academy.jpautil.JPAUtil;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

import static by.it.academy.entities.Constants.*;

public class ValidationInFilterService {
    private static ValidationInFilterService instance;

    private ValidationInFilterService() {
    }

    public static ValidationInFilterService getInstance() {
        if (instance == null) {
            instance = new ValidationInFilterService();
        }
        return instance;
    }

    EntityManager entityManager = new JPAUtil().getEntityManager();

    public void checkForDuplicates(HttpServletRequest req, HttpServletResponse res) {
        entityManager.getTransaction().begin();
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        try {
            CriteriaQuery<User> criteriaQuery = cb.createQuery(User.class);
            Root<User> userRoot = criteriaQuery.from(User.class);
            Predicate predicate = cb.or(
                    cb.equal(userRoot.get("nameCompany"), req.getParameter("nameCompany")),
                    cb.equal(userRoot.get("email"), req.getParameter("email")),
                    cb.equal(userRoot.get("userName"), req.getParameter("userName")),
                    cb.equal(userRoot.get("password"), req.getParameter("password"))
            );
            criteriaQuery.select(userRoot).where(predicate);
            Optional<User> userOptional = entityManager.createQuery(criteriaQuery).getResultStream().findFirst();
            User user = userOptional.orElse(null);
            if (user != null) {
                req.getRequestDispatcher(INDEX_PAGE).forward(req, res);
                return;
            }
            entityManager.getTransaction().commit();
        } catch (ServletException | IOException | NoResultException e) {
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
    }
}
