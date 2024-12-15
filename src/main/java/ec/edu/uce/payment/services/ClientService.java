package ec.edu.uce.payment.services;

import ec.edu.uce.payment.model.Client;
import ec.edu.uce.payment.util.EntityManagerUtil;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.*;

import java.util.List;

@ApplicationScoped
public class ClientService implements ICrud<Client> {
    private final EntityManager em = EntityManagerUtil.getEntityManager();

    EntityTransaction tx = em.getTransaction();

    public ClientService() {
    }

    @Override
    public List<Client> list() {
        return em.createQuery("from Client", Client.class).getResultList();

    }

    @Override
    public Client findById(int id) {
        return em.find(Client.class, id);
    }

    @Override
    public void save(Client entity) {

        tx.begin();
        em.persist(entity);
        tx.commit();

    }

    @Override
    public void delete(int id) {
        Client client = this.em.find(Client.class, id);
        tx.begin();
        em.remove(client);
        tx.commit();

    }

    @Override
    public void update(Client client) {

        tx.begin();
        em.merge(client);
        tx.commit();

    }

}
