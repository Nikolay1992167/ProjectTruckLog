package org;

import by.it.academy.entities.User;
import by.it.academy.entities.UserType;
import by.it.academy.jpautil.JPAUtil;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.*;
import java.util.List;

public class JPAtest {
    public static void main(String[] args) {
        //select("serg", "serg234");
        //delete(7);
        //update("ASW","lepel", "33@mail.ret", "lep", "lep234","ADMIN", 6);
        //select(4);
    }

    private static void select(int id) {
        EntityManager entityManager = new JPAUtil().getEntityManager();
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        entityManager.getTransaction().begin();
        try {
            CriteriaQuery<User> criteriaQuery = cb.createQuery(User.class);
            Root<User> root = criteriaQuery.from(User.class);
            criteriaQuery.select(root)
                    .where(cb.equal(root.get("id"), id));
            List<User> users = entityManager.createQuery(criteriaQuery).getResultList();
            users.forEach(System.out::println);
            entityManager.getTransaction().commit();
        } finally {
            entityManager.close();
        }
    }

    private static void delete(int id) {
        EntityManager entityManager = new JPAUtil().getEntityManager();
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        entityManager.getTransaction().begin();
        try {
            CriteriaDelete<User> criteria = cb.createCriteriaDelete(User.class);
            Root<User> root = criteria.from(User.class);
            criteria.where(cb.equal(root.get("id"), id));
            entityManager.createQuery(criteria).executeUpdate();
            entityManager.getTransaction().commit();
        } finally {
            entityManager.close();
        }
    }

    private static void update(String nameCompany, String location, String email, String userName, String password, String userType, int id) {
        EntityManager entityManager = new JPAUtil().getEntityManager();
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        entityManager.getTransaction().begin();
        try {
            CriteriaUpdate<User> criteriaUpdate = cb.createCriteriaUpdate(User.class);
            Root<User> root = criteriaUpdate.from(User.class);
            criteriaUpdate.set("nameCompany", nameCompany);
            criteriaUpdate.set("location", location);
            criteriaUpdate.set("email", email);
            criteriaUpdate.set("userName", userName);
            criteriaUpdate.set("password", password);
            criteriaUpdate.set("userType", UserType.valueOf(userType));
            criteriaUpdate.where(cb.equal(root.get("id"), id));
            entityManager.createQuery(criteriaUpdate).executeUpdate();
            entityManager.getTransaction().commit();
        } finally {
            entityManager.close();
        }
    }

    private static void select(String login, String password) {
        EntityManager entityManager = new JPAUtil().getEntityManager();
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = cb.createQuery(User.class);
        Root<User> userRoot = criteriaQuery.from(User.class);
        Predicate predicate = cb.and(
                cb.equal(userRoot.get("userName"), login),
                cb.equal(userRoot.get("password"), password)
        );
        criteriaQuery.select(userRoot).where(predicate);
        User singleResult = entityManager.createQuery(criteriaQuery).getSingleResult();
    }

}
