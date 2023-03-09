package by.it.academy.services;

import by.it.academy.dao.ProductDAO;
import by.it.academy.entities.Product;
import by.it.academy.jpautil.JPAUtil;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ProductService implements ProductDAO {
    EntityManager entityManager = JPAUtil.getEntityManager();

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
    public List<Product> readAllProducts(HttpServletRequest req) {
        List<Product> products;
        entityManager.getTransaction().begin();
        try {
            products = entityManager.createQuery("from Product ", Product.class).getResultList();
            req.setAttribute("products", products);
            entityManager.getTransaction().commit();
        } finally {
            entityManager.close();
        }
        return products;
    }

    @Override
    public void updateProduct(HttpServletRequest req) {
        entityManager.getTransaction().begin();
        try {
            Product product = entityManager.find(Product.class, req.getParameter("id"));
            entityManager.detach(product);
            product.setId(Integer.parseInt(req.getParameter("id")));
            product.setWeight(Integer.parseInt(req.getParameter("weight")));
            product.setLoadingLocation(req.getParameter("loadingLocation"));
            product.setUnloadingLocation(req.getParameter("unloadingLocation"));
            product.setCargoCost(Integer.parseInt(req.getParameter("cargoCost")));
            entityManager.merge(product);
            entityManager.getTransaction().commit();
        }  finally {
            entityManager.close();
        }
    }

    @Override
    public void deleteProduct(HttpServletRequest req) {
        entityManager.getTransaction().begin();

        try {
            Product product = entityManager.find(Product.class, req.getParameter("id"));
            entityManager.remove(product);
            entityManager.getTransaction().commit();
        } finally {
            entityManager.close();
        }
    }
}
