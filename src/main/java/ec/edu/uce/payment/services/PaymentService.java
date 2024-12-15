package ec.edu.uce.payment.services;

import ec.edu.uce.payment.annotations.QualifierPayment;
import ec.edu.uce.payment.paymentmethods.IPayment;
import ec.edu.uce.payment.model.Client;
import ec.edu.uce.payment.model.PaymentMethod;
import ec.edu.uce.payment.model.Product;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.Set;

@ApplicationScoped
public class PaymentService {
    @Inject
    @QualifierPayment("CreditCard")
    private IPayment creditCardPayment;

    @Inject
    @QualifierPayment("BankTransfer")
    private IPayment bankTransferPayment;

    @Inject
    @QualifierPayment("Paypal")
    private IPayment paypalPayment;

    public String processPayment(PaymentMethod paymentMethod, Client client, Set<Product> products) {
        switch (paymentMethod.getName().toLowerCase()) {
            case "creditcard":
                return creditCardPayment.pay(client, products);
            case "banktransfer":
                return bankTransferPayment.pay(client, products);
            case "paypal":
                return paypalPayment.pay(client, products);
            default:
                throw new IllegalArgumentException("Payment method not supported: " + paymentMethod.getName());
        }
    }
}
