package ec.edu.uce.payment.model;

import jakarta.persistence.*;

@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "payment_method_id")
    private PaymentMethod paymentMethod;

    private double amount;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return String.format(
                "Transaccion {\n" +
                        "    id: %d,\n" +
                        "    client: {\n" +
                        "        id: %d,\n" +
                        "        name: \"%s\"\n" +
                        "    },\n" +
                        "    product: {\n" +
                        "        id: %d,\n" +
                        "        name: \"%s\",\n" +
                        "        price: %.2f\n" +
                        "    },\n" +
                        "    paymentMethod: {\n" +
                        "        id: %d,\n" +
                        "        name: \"%s\"\n" +
                        "    },\n" +
                        "    amount: %.2f\n" +
                        "}",
                id,
                client.getId(),
                client.getName(),
                product.getId(),
                product.getName(),
                product.getPrice(),
                paymentMethod.getId(),
                paymentMethod.getName(),
                amount
        );
    }
}
