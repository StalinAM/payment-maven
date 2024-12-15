package ec.edu.uce.payment.classes;

import ec.edu.uce.payment.jpa.Client;
import ec.edu.uce.payment.jpa.Product;

public interface IPayment {

    public String pay(Client client, Product product);

}
