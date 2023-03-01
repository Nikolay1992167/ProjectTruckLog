package JPA.client;

import JPA.entity.Message;
import JPA.util.JPAUtil;

import javax.persistence.EntityManager;

public class HelloWorldClient {
    public static void main(String[] args) {
        saveEntityByJPA();
    }

    private static void saveEntityByJPA(){
        EntityManager entityManager = JPAUtil.getEntityManager();
        entityManager.getTransaction().begin();

        Message message = new Message("Hello world with JPA!");
        entityManager.persist(message);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

}
