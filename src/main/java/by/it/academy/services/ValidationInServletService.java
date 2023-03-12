package by.it.academy.services;
import by.it.academy.entities.User;
import by.it.academy.entities.UserType;
import by.it.academy.jpautil.JPAUtil;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;

import static by.it.academy.entities.Constants.*;

public class ValidationInServletService {
    private static ValidationInServletService instance;

    private ValidationInServletService() {
    }

    public static ValidationInServletService getInstance() {
        if (instance == null) {
            instance = new ValidationInServletService();
        }
        return instance;
    }

    EntityManager entityManager = new JPAUtil().getEntityManager();

    public void checkingData(HttpSession session, String login, String password) {
        entityManager.getTransaction().begin();
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        try {
            CriteriaQuery<User> criteriaQuery = cb.createQuery(User.class);
            Root<User> userRoot = criteriaQuery.from(User.class);
            Predicate predicate = cb.or(
                    cb.equal(userRoot.get("userName"), login),
                    cb.equal(userRoot.get("password"), password)
            );
            criteriaQuery.select(userRoot).where(predicate);
            Optional <User> userOptional = entityManager.createQuery(criteriaQuery).getResultStream().findFirst();
            User user = userOptional.orElse(null);

            while (user != null) {
                UserType userType = user.getUserType();
                session.setAttribute("userName", login);
                session.setAttribute("password", password);
                session.setAttribute("userType", userType);
            }
            entityManager.getTransaction().commit();
        } finally {
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
