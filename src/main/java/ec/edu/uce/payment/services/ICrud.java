package ec.edu.uce.payment.services;

import java.util.List;

public interface ICrud<T> {

    List<T> list();
    T findById(int id);
    void save(T entity);
    void delete(int id);
    void update(T entity);

}
