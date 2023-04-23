package org;

import by.it.academy.entities.Product;
import by.it.academy.entities.User;
import by.it.academy.entities.UserType;
import by.it.academy.jpautil.JPAUtil;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.criteria.*;
import java.util.List;

public class JPAtest {

    private EntityManager entityManager;
    private static CriteriaBuilder criteriaBuilder;

    @Before
    public void setUp() {
        entityManager = new JPAUtil().getEntityManager();
        criteriaBuilder = entityManager.getCriteriaBuilder();
    }

    @Test
    public void selectUsersByID() {
        int id = 2;
        entityManager.getTransaction().begin();
        try {
            CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
            Root<User> userRoot = criteriaQuery.from(User.class);
            criteriaQuery.select(userRoot)
                    .where(criteriaBuilder.equal(userRoot.get("id"), id));
            List<User> users = entityManager.createQuery(criteriaQuery).getResultList();
            if (users != null){
                users.forEach(System.out::println);
            }else {
                System.out.println("User is not found!");
            }
            entityManager.getTransaction().commit();
        } finally {
            entityManager.close();
        }
    }

    @Test
    public void deleteUser() {
        int id = 4;
        entityManager.getTransaction().begin();
        try {
            CriteriaDelete<User> criteria = criteriaBuilder.createCriteriaDelete(User.class);
            Root<User> userRoot = criteria.from(User.class);
            criteria.where(criteriaBuilder.equal(userRoot.get("id"), id));
            entityManager.createQuery(criteria).executeUpdate();
            entityManager.getTransaction().commit();
        }finally {
            entityManager.close();
        }
    }

    @Test
    public void updateUser() {
        String nameCompany = "ARW";
        String location = "lepel";
        String email = "3@mail.ru";
        String userName = "wert";
        String password = "wert234";
        String userType = "SUPPLIER";
        int id = 2;
        entityManager.getTransaction().begin();
        try {
            CriteriaUpdate<User> criteriaUpdate = criteriaBuilder.createCriteriaUpdate(User.class);
            Root<User> userRoot = criteriaUpdate.from(User.class);
            criteriaUpdate.set("nameCompany", nameCompany);
            criteriaUpdate.set("location", location);
            criteriaUpdate.set("email", email);
            criteriaUpdate.set("userName", userName);
            criteriaUpdate.set("password", password);
            criteriaUpdate.set("userType", UserType.valueOf(userType));
            criteriaUpdate.where(criteriaBuilder.equal(userRoot.get("id"), id));
            entityManager.createQuery(criteriaUpdate).executeUpdate();
            entityManager.getTransaction().commit();
        } finally {
            entityManager.close();
        }
    }

    @Test
    public void selectUserByData() {
        String login = "humor";
        String password = "humor234";
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        Root<User> userRoot = criteriaQuery.from(User.class);
        Predicate predicate = criteriaBuilder.and(
                criteriaBuilder.equal(userRoot.get("userName"), login),
                criteriaBuilder.equal(userRoot.get("password"), password)
        );
        criteriaQuery.select(userRoot).where(predicate);
        User singleResult = entityManager.createQuery(criteriaQuery).getSingleResult();
    }
    @Test
    public void creatProduct() {
        entityManager.getTransaction().begin();
        try {
            Product product = new Product();
            product.setWeight(1000);
            product.setName("Metall");
            product.setLoadingLocation("Zhlobin");
            product.setUnloadingLocation("Kiev");
            product.setCargoCost(140000);
            product.setTransportCharacteristic(2);
            entityManager.persist(product);
            entityManager.getTransaction().commit();
        } finally {
            entityManager.close();
        }
    }
}
