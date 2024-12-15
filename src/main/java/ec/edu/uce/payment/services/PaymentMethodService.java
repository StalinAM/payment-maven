package ec.edu.uce.payment.services;

import ec.edu.uce.payment.model.PaymentMethod;
import ec.edu.uce.payment.util.EntityManagerUtil;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;

import java.util.List;

@ApplicationScoped
public class PaymentMethodService implements ICrud<PaymentMethod> {

    private final EntityManager em = EntityManagerUtil.getEntityManager();

    public PaymentMethodService() {

    }

    @Override
    public void save(PaymentMethod paymentMethod) {
        try {
            if (!em.getTransaction().isActive()) {
                em.getTransaction().begin();
                System.out.println("Transacción iniciada.");
            }

            if (paymentMethod.getId() == 0) {
                em.persist(paymentMethod); // Guardar nueva entidad
                System.out.println("Entidad nueva persistida: " + paymentMethod);
            } else {
                em.merge(paymentMethod); // Actualizar entidad existente
                System.out.println("Entidad existente actualizada: " + paymentMethod);
            }

            em.getTransaction().commit();
            System.out.println("Transacción confirmada.");
        } catch (Exception e) {
            System.err.println("Error durante la operación save: " + e.getMessage());
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
                System.out.println("Transacción revertida debido a un error.");
            }
            throw e;
        }

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void update(PaymentMethod entity) {

    }

    @Override
    public List<PaymentMethod> list() {
        try {
            return em.createQuery("FROM PaymentMethod", PaymentMethod.class).getResultList();
        } catch (Exception e) {
            System.err.println("Error al listar PaymentMethod: " + e.getMessage());
            throw e;
        }
    }

    @Override
    public PaymentMethod findById(int id) {
        return em.find(PaymentMethod.class, id);
    }

}
