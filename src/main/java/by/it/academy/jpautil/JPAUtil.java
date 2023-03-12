package by.it.academy.jpautil;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {
    private EntityManager ENTITY_MANAGER = buildEntityManager();

    private EntityManager buildEntityManager() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PersistenceCompany");
        return emf.createEntityManager();
    }
    public EntityManager getEntityManager(){
        return ENTITY_MANAGER;
    }
}
