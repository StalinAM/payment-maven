package ec.edu.uce.payment.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class PaymentMethod {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    public PaymentMethod() {
    }

    public PaymentMethod(int id, String name) {
        this.id = id;
        this.name = name;
    }
    public PaymentMethod(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return String.format("PaymentMethod {\n    id: %d,\n    name: \"%s\"\n}", id, name);
    }
}
