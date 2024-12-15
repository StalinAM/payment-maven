package ec.edu.uce.payment.services;

import ec.edu.uce.payment.annotations.QualifierPayment;
import ec.edu.uce.payment.paymentmethods.IPayment;
import ec.edu.uce.payment.model.Client;
import ec.edu.uce.payment.model.PaymentMethod;
import ec.edu.uce.payment.model.Product;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

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

    public String processPayment(PaymentMethod paymentMethod, Client client, Product product) {
        switch (paymentMethod.getName().toLowerCase()) {
            case "creditcard":
                return creditCardPayment.pay(client, product);
            case "banktransfer":
                return bankTransferPayment.pay(client, product);
            case "paypal":
                return paypalPayment.pay(client, product);
            default:
                throw new IllegalArgumentException("Payment method not supported: " + paymentMethod.getName());
        }
    }
}
