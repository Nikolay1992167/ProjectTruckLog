package by.it.academy.services;

import by.it.academy.dao.UserDAO;
import by.it.academy.entities.User;
import by.it.academy.entities.UserType;
import by.it.academy.jpautil.JPAUtil;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class UserService implements UserDAO {
    EntityManager entityManager = JPAUtil.getEntityManager();

    @Override
    public void creatUser(HttpServletRequest req) {
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
        List<User> users;
        entityManager.getTransaction().begin();
        try {
            users = entityManager.createQuery("from User", User.class).getResultList();
            req.setAttribute("users", users);
            entityManager.getTransaction().commit();
        } finally {
            entityManager.close();
        }
        return users;
    }

    @Override
    public void updateUser(HttpServletRequest req){
        entityManager.getTransaction().begin();
        try {
            User user = entityManager.find(User.class, req.getParameter("id"));
            entityManager.detach(user);
            user.setId(Integer.parseInt(req.getParameter("id")));
            user.setNameCompany(req.getParameter("nameCompany"));
            user.setLocation(req.getParameter("location"));
            user.setEmail(req.getParameter("email"));
            user.setUserName(req.getParameter("userName"));
            user.setPassword(req.getParameter("password"));
            user.setUserType(UserType.valueOf(req.getParameter("userType")));
            entityManager.merge(user);
            entityManager.getTransaction().commit();
        } finally {
            entityManager.close();
        }
    }

    @Override
    public void deleteUser(HttpServletRequest req){
        entityManager.getTransaction().begin();
        try {
            User user = entityManager.find(User.class, req.getParameter("id"));
            entityManager.remove(user);
            entityManager.getTransaction().commit();
        } finally {
            entityManager.close();
        }
    }
}
