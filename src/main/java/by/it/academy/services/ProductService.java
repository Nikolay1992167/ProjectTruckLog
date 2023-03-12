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

    EntityManager entityManager = new JPAUtil().getEntityManager();

    public void creatProduct(HttpServletRequest req) {
        entityManager.getTransaction().begin();
        try {
            Product product = new Product(Integer.parseInt(req.getParameter("weight")), req.getParameter("loadingLocation"), req.getParameter("unloadingLocation"), Integer.parseInt(req.getParameter("cargoCost")));
            entityManager.persist(product);
            entityManager.getTransaction().commit();
        } finally {
            entityManager.close();
        }
    }

    @Override
    public List<Product> readAllProducts() {
        entityManager.getTransaction().begin();
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        List<Product> products;
        try {
            CriteriaQuery<Product> criteriaQuery = cb.createQuery(Product.class);
            Root<Product> productRoot = criteriaQuery.from(Product.class);
            criteriaQuery.select(productRoot);
            products = entityManager.createQuery(criteriaQuery).getResultList();
            entityManager.getTransaction().commit();
        } finally {
            entityManager.close();
        }

        return products;
    }

    @Override
    public void updateProduct(HttpServletRequest req) {
        entityManager.getTransaction().begin();
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        try {

            CriteriaUpdate<Product> criteriaUpdate = cb.createCriteriaUpdate(Product.class);
            Root<Product> root = criteriaUpdate.from(Product.class);
            criteriaUpdate.set("weight", Integer.parseInt(req.getParameter("weight")));
            criteriaUpdate.set("loadingLocation", req.getParameter("loadingLocation"));
            criteriaUpdate.set("unloadingLocation", req.getParameter("unloadingLocation"));
            criteriaUpdate.set("cargoCost", Integer.parseInt(req.getParameter("cargoCost")));
            criteriaUpdate.where(cb.equal(root.get("id"), req.getParameter("id")));
            entityManager.createQuery(criteriaUpdate).executeUpdate();
            entityManager.getTransaction().commit();

            /*Product product = entityManager.find(Product.class, req.getParameter("id"));
            entityManager.detach(product);
            product.setId(Integer.parseInt());
            product.setWeight(Integer.parseInt());
            product.setLoadingLocation();
            product.setUnloadingLocation(req.getParameter("unloadingLocation"));
            product.setCargoCost();
            entityManager.merge(product);
            entityManager.getTransaction().commit();*/
        } finally {
            entityManager.close();
        }
    }

    @Override
    public void deleteProduct(HttpServletRequest req) {
        entityManager.getTransaction().begin();
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        try {

            CriteriaDelete<Product> criteriaDelete = cb.createCriteriaDelete(Product.class);
            Root<Product> root = criteriaDelete.from(Product.class);
            criteriaDelete.where(cb.greaterThan(root.get("id"), req.getParameter("id")));
            entityManager.createQuery(criteriaDelete).executeUpdate();
            /*Product product = entityManager.find(Product.class, req.getParameter("id"));
            entityManager.remove(product);*/
            entityManager.getTransaction().commit();
        } finally {
            entityManager.close();
        }
    }
}
