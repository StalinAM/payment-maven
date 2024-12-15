package ec.edu.uce.payment.services;

import ec.edu.uce.payment.model.Product;
import ec.edu.uce.payment.util.EntityManagerUtil;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;


import java.util.HashSet;
import java.util.List;
import java.util.Set;

@ApplicationScoped
public class ProductService implements ICrud<Product> {
    private final EntityManager em = EntityManagerUtil.getEntityManager();
    EntityTransaction tx = em.getTransaction();

    public ProductService() {
    }

    @Override
    public List<Product> list() {
        return em.createQuery("from Product", Product.class).getResultList();
    }

    @Override
    public Product findById(int id) {
        return this.em.find(Product.class, id);
    }

    @Override
    public void save(Product product) {
        tx.begin();
        em.persist(product);
        tx.commit();
    }

    @Override
    public void delete(int id) {
        Product product = em.find(Product.class, id);
        tx.begin();
        em.remove(product);
        tx.commit();
    }

    @Override
    public void update(Product product) {
        tx.begin();
        em.merge(product);
        tx.commit();
    }

    public Set<Product> findProductsByIds(String productIds) {
        Set<Product> products = new HashSet<>();
        String[] productIdArray = productIds.split(",");

        for (String id : productIdArray) {
            int productId = Integer.parseInt(id);
            Product product = findById(productId);
            if (product != null) {
                products.add(product);
            }
        }

        return products;
    }

}
