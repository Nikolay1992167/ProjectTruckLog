package JPA.client;

import JPA.entity.Message;
import by.it.academy.jpautil.JPAUtil;

import javax.persistence.EntityManager;

public class HelloWorldClient {
    public static void main(String[] args) {
        saveEntityByJPA();
    }

    private static void saveEntityByJPA(){
        EntityManager entityManager = JPAUtil.getEntityManager();
        entityManager.getTransaction().begin();

        Message message = new Message("Hello world with JPA!");
        Message message1 = new Message("Hi mozur!");

        entityManager.persist(message);
        entityManager.persist(message1);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

}
