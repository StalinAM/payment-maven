package ec.edu.uce.payment.paymentmethods;

import ec.edu.uce.payment.model.Client;
import ec.edu.uce.payment.model.Product;

import java.util.Set;

public interface IPayment {

    public String pay(Client client, Set<Product> products);

}
