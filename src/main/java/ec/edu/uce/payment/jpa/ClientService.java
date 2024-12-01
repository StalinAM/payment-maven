package ec.edu.uce.payment.jpa;

import jakarta.persistence.EntityManager;

public class ClientService {

    private EntityManager em;

    public ClientService(EntityManager em) {
        this.em = em;
    }

    public void createClient(Client client) {
        em.getTransaction().begin();
        em.persist(client);
        em.getTransaction().commit();
    }

}
