package by.it.academy.services;

import by.it.academy.dao.UserDAO;
import by.it.academy.entities.Product;
import by.it.academy.entities.User;
import by.it.academy.entities.UserType;
import by.it.academy.jpautil.JPAUtil;

import javax.persistence.EntityManager;
import javax.persistence.criteria.*;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class UserService implements UserDAO {
    private static UserService instance;
    private UserService(){}
    public static UserService getInstance(){
        if(instance == null){
            instance = new UserService();
        }
        return instance;
    }
    /*EntityManager entityManager = new JPAUtil().getEntityManager();
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();*/

    @Override
    public void creatUser(HttpServletRequest req) {
        EntityManager entityManager = new JPAUtil().getEntityManager();
        entityManager.getTransaction().begin();
        try {
            User user = new User(req.getParameter("nameCompany"), req.getParameter("location"), req.getParameter("email"), req.getParameter("userName"), req.getParameter("password"), UserType.valueOf(req.getParameter("userType")));
            entityManager.persist(user);
            entityManager.getTransaction().commit();
        } finally {
            entityManager.close();
        }
    }

    @Override
    public List<User> readAllUsers(HttpServletRequest req)  {
        EntityManager entityManager = new JPAUtil().getEntityManager();
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        List<User> users;
        entityManager.getTransaction().begin();
        try {
            CriteriaQuery<User> criteriaQuery = cb.createQuery(User.class);
            Root<User> userRoot = criteriaQuery.from(User.class);
            criteriaQuery.select(userRoot);
            users = entityManager.createQuery(criteriaQuery).getResultList();
            req.setAttribute("users", users);
            entityManager.getTransaction().commit();
        } finally {
            entityManager.close();
        }
        return users;
    }

    @Override
    public void updateUser(HttpServletRequest req){
        EntityManager entityManager = new JPAUtil().getEntityManager();
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        entityManager.getTransaction().begin();
        try {
            CriteriaUpdate<User> criteriaUpdate = cb.createCriteriaUpdate(User.class);
            Root<User> root = criteriaUpdate.from(User.class);
            criteriaUpdate.set("nameCompany", req.getParameter("nameCompany"));
            criteriaUpdate.set("location",req.getParameter("location"));
            criteriaUpdate.set("email", req.getParameter("email"));
            criteriaUpdate.set("userName",req.getParameter("userName"));
            criteriaUpdate.set("password",req.getParameter("password"));
            criteriaUpdate.set("userType",UserType.valueOf(req.getParameter("userType")));
            criteriaUpdate.where(cb.equal(root.get("id"), req.getParameter("id")));
            entityManager.createQuery(criteriaUpdate).executeUpdate();
            entityManager.getTransaction().commit();
        } finally {
            entityManager.close();
        }
    }

    @Override
    public void deleteUser(HttpServletRequest req){
        EntityManager entityManager = new JPAUtil().getEntityManager();
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        entityManager.getTransaction().begin();
        try {
            CriteriaDelete<User> criteriaDelete = cb.createCriteriaDelete(User.class);
            Root<User> root = criteriaDelete.from(User.class);
            criteriaDelete.where(cb.equal(root.get("id"), req.getParameter("id")));
            entityManager.createQuery(criteriaDelete).executeUpdate();
            entityManager.getTransaction().commit();
        } finally {
            entityManager.close();
        }
    }
}
