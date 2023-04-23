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
import java.util.List;

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
    CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
    public void checkForDuplicates(HttpServletRequest req, HttpServletResponse res) {
        entityManager.getTransaction().begin();
        try {
            CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
            Root<User> userRoot = criteriaQuery.from(User.class);
            Predicate predicate = criteriaBuilder.or(
                    criteriaBuilder.equal(userRoot.get("nameCompany"), req.getParameter("nameCompany")),
                    criteriaBuilder.equal(userRoot.get("email"), req.getParameter("email")),
                    criteriaBuilder.equal(userRoot.get("userName"), req.getParameter("userName")),
                    criteriaBuilder.equal(userRoot.get("password"), req.getParameter("password"))
            );
            criteriaQuery.select(userRoot).where(predicate);
            List<User> users = entityManager.createQuery(criteriaQuery).getResultList();
            if (users != null) {
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
