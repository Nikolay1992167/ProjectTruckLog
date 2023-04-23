package by.it.academy.services;

import by.it.academy.dao.ProductDAO;
import by.it.academy.entities.Product;
import by.it.academy.jpautil.JPAUtil;
import javax.persistence.EntityManager;
import javax.persistence.criteria.*;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ProductService implements ProductDAO {

    private static ProductService instance;

    private ProductService() {
    }

    public static ProductService getInstance() {
        if (instance == null) {
            instance = new ProductService();
        }
        return instance;
    }

    public void creatProduct(HttpServletRequest req) {
        EntityManager entityManager = new JPAUtil().getEntityManager();
        entityManager.getTransaction().begin();
        try {
            Product product = new Product(req.getParameter("name")
                    , Integer.parseInt(req.getParameter("weight"))
                    , req.getParameter("loadingLocation")
                    , req.getParameter("unloadingLocation")
                    , Integer.parseInt(req.getParameter("cargoCost"))
                    , Integer.parseInt(req.getParameter("characteristic")));
            entityManager.persist(product);
            entityManager.getTransaction().commit();
        } finally {
            entityManager.close();
        }
    }

    @Override
    public List<Product> readAllProducts(HttpServletRequest req) {
        EntityManager entityManager = new JPAUtil().getEntityManager();
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        entityManager.getTransaction().begin();
        List<Product> products;
        try {
            CriteriaQuery<Product> criteriaQuery = criteriaBuilder.createQuery(Product.class);
            Root<Product> productRoot = criteriaQuery.from(Product.class);
            criteriaQuery.select(productRoot);
            products = entityManager.createQuery(criteriaQuery).getResultList();
            req.setAttribute("products", products);
            entityManager.getTransaction().commit();
        } finally {
            entityManager.close();
        }
        return products;
    }

    @Override
    public void updateProduct(HttpServletRequest req) {
        EntityManager entityManager = new JPAUtil().getEntityManager();
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        entityManager.getTransaction().begin();
        try {
            CriteriaUpdate<Product> criteriaUpdate = criteriaBuilder.createCriteriaUpdate(Product.class);
            Root<Product> root = criteriaUpdate.from(Product.class);
            criteriaUpdate.set("name", Integer.parseInt(req.getParameter("name")));
            criteriaUpdate.set("weight", Integer.parseInt(req.getParameter("weight")));
            criteriaUpdate.set("loadingLocation", req.getParameter("loadingLocation"));
            criteriaUpdate.set("unloadingLocation", req.getParameter("unloadingLocation"));
            criteriaUpdate.set("cargoCost", Integer.parseInt(req.getParameter("cargoCost")));
            criteriaUpdate.set("transportCharacteristic", Integer.parseInt(req.getParameter("characteristic")));
            criteriaUpdate.where(criteriaBuilder.equal(root.get("id"), req.getParameter("id")));
            entityManager.createQuery(criteriaUpdate).executeUpdate();
            entityManager.getTransaction().commit();
        } finally {
            entityManager.close();
        }
    }

    @Override
    public void deleteProduct(HttpServletRequest req) {
        EntityManager entityManager = new JPAUtil().getEntityManager();
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        entityManager.getTransaction().begin();
        try {
            CriteriaDelete<Product> criteriaDelete = criteriaBuilder.createCriteriaDelete(Product.class);
            Root<Product> productRoot = criteriaDelete.from(Product.class);
            criteriaDelete.where(criteriaBuilder.equal(productRoot.get("id"), req.getParameter("id")));
            entityManager.createQuery(criteriaDelete).executeUpdate();
            entityManager.getTransaction().commit();
        } finally {
            entityManager.close();
        }
    }
}
