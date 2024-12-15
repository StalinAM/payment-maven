package ec.edu.uce.payment.services;

import ec.edu.uce.payment.model.Client;
import ec.edu.uce.payment.model.Product;
import ec.edu.uce.payment.model.Transaction;
import ec.edu.uce.payment.util.EntityManagerUtil;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.List;
import java.util.Set;

@ApplicationScoped
public class TransactionService implements ICrud<Transaction> {
    private final EntityManager em= EntityManagerUtil.getEntityManager();
    EntityTransaction tx = em.getTransaction();

    @Override
    public List<Transaction> list() {
        return em.createQuery("from Transaction", Transaction.class).getResultList();
    }

    public List<Transaction> getTransactionsByClient(Client client) {
        return em.createQuery("SELECT t FROM Transaction t WHERE t.client = :client", Transaction.class)
                .setParameter("client", client)
                .getResultList();
    }

    @Override
    public void save(Transaction transaction) {
        tx.begin();
        em.persist(transaction);
        tx.commit();
    }
    public void save(Transaction transaction, Set<Product> products) {
        tx.begin();
        transaction.setProducts(products);
        em.persist(transaction);
        tx.commit();
    }

    public Transaction createTransaction(Client client, Set<Product> products) {
        Transaction transaction = new Transaction();
        transaction.setClient(client);
        transaction.setProducts(products);
        transaction.setAmount(products.stream().mapToDouble(Product::getPrice).sum());
        return transaction;
    }

    @Override
    public void delete(int id) {
    }

    @Override
    public void update(Transaction transaction) {
    }
    @Override
    public Transaction findById(int id) {
        return null;
    }
}
