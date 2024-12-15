package ec.edu.uce.payment.model;

import jakarta.persistence.*;

import java.util.Set;

@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToMany
    @JoinTable(
            name = "transaction_product",  // Tabla intermedia
            joinColumns = @JoinColumn(name = "transaction_id"),  // FK para la transacción
            inverseJoinColumns = @JoinColumn(name = "product_id")  // FK para el producto
    )
    private Set<Product> products; // Usar Set para evitar duplicados de productos

    private double amount;  // El total de la transacción

    public Transaction() {
    }

    public Transaction(int id, Client client, Set<Product> products, double amount) {
        this.id = id;
        this.client = client;
        this.products = products;
        this.amount = amount;
    }

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

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Transaction {\n")
                .append("    id: ").append(id).append(",\n")
                .append("    client: { id: ").append(client.getId()).append(", name: \"").append(client.getName()).append("\" },\n")
                .append("    products: [");

        // Añadir los productos de la transacción
        for (Product product : products) {
            sb.append("\n        { id: ").append(product.getId())
                    .append(", name: \"").append(product.getName())
                    .append("\", price: ").append(product.getPrice())
                    .append(" }");
        }

        sb.append("\n    ],\n")
                .append("    amount: ").append(amount)
                .append("\n}");
        return sb.toString();
    }
}
