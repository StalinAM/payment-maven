package ec.edu.uce.payment.jpa;

import jakarta.persistence.EntityManager;

import java.util.ArrayList;

public class ClientService {

    private final EntityManager em;

    public ClientService(EntityManager em) {
        this.em = em;
    }

    public void createClient(Client client) {
        em.getTransaction().begin();
        em.persist(client);
        em.getTransaction().commit();
    }

    public void updateClient(Client client) {
        em.getTransaction().begin();
        em.merge(client);
        em.getTransaction().commit();
    }

    public void deleteClient(Client client) {
        em.getTransaction().begin();
        em.remove(client);
        em.getTransaction().commit();
    }

    public Client getClient(int id) {
        return em.find(Client.class, id);
    }

    public void close() {
        em.close();
    }
}
