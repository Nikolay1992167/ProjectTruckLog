package by.it.academy.jpautil;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {
    private static EntityManager ENTITY_MANAGER = buildEntityManager();

    private static EntityManager buildEntityManager() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PersistenceCompany");
        return emf.createEntityManager();
    }
    public static EntityManager getEntityManager(){
        return ENTITY_MANAGER;
    }
}
