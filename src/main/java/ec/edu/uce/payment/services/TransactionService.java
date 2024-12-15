package ec.edu.uce.payment.services;

import ec.edu.uce.payment.model.Transaction;
import ec.edu.uce.payment.util.EntityManagerUtil;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.List;

@ApplicationScoped
public class TransactionService implements ICrud<Transaction> {
    private final EntityManager em= EntityManagerUtil.getEntityManager();
    EntityTransaction tx = em.getTransaction();

    public TransactionService() {

    }

    @Override
    public List<Transaction> list() {
        return em.createQuery("from Transaction", Transaction.class).getResultList();
    }

    @Override
    public Transaction findById(int id) {
        return null;
    }

    @Override
    public void save(Transaction transaction) {
        tx.begin();
        em.persist(transaction);
        tx.commit();
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void update(Transaction transaction) {

    }
}
