package ec.edu.uce.payment.services;

import ec.edu.uce.payment.annotations.QualifierPayment;
import ec.edu.uce.payment.paymentmethods.IPayment;
import ec.edu.uce.payment.model.Client;
import ec.edu.uce.payment.model.PaymentMethod;
import ec.edu.uce.payment.model.Product;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Inject;

import java.util.HashMap;
import java.util.Map;
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

    @Produces
    public Map<String, IPayment> producePaymentMethods() {
        Map<String, IPayment> paymentMethods = new HashMap<>();
        paymentMethods.put("creditcard", creditCardPayment);
        paymentMethods.put("banktransfer", bankTransferPayment);
        paymentMethods.put("paypal", paypalPayment);
        return paymentMethods;
    }

    public String processPayment(PaymentMethod paymentMethod, Client client, Set<Product> products) {
        IPayment paymentProcessor = producePaymentMethods().get(paymentMethod.getName().toLowerCase());
        if (paymentProcessor == null) {
            throw new IllegalArgumentException("Payment method not supported: " + paymentMethod.getName());
        }
        return paymentProcessor.pay(client, products);
    }
}
