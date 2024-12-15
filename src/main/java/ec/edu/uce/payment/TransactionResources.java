package ec.edu.uce.payment;

import ec.edu.uce.payment.jpa.Client;
import ec.edu.uce.payment.jpa.PaymentMethod;
import ec.edu.uce.payment.jpa.Product;
import ec.edu.uce.payment.jpa.Transaction;
import ec.edu.uce.payment.services.*;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;

import java.util.List;

@Path("/transaction")
public class TransactionResources {

    @Inject
    ClientService clientService;

    @Inject
    ProductService productService;

    @Inject
    PaymentService paymentService;

    @Inject
    PaymentMethodService paymentMethodService;

    @Inject
    TransactionService transactionService;

    @GET
    @Produces("text/plain")
    @Path("/all")
    public String getTransactions() {
        List<Transaction> listTransaction = transactionService.list();
        return listTransaction.toString();
    }

    @GET
    @Produces("text/plain")
    @Path("/process")
    public String processTransaction(@QueryParam("clientId") int clientId, @QueryParam("productId") int productId,@QueryParam("paymentMethodId") int paymentMethodId) {
        Client client = clientService.findById(clientId);
        Product product = productService.findById(productId);
        PaymentMethod paymentMethod = paymentMethodService.findById(paymentMethodId);

        Transaction transaction = new Transaction();
        transaction.setClient(client);
        transaction.setProduct(product);
        transaction.setPaymentMethod(paymentMethod);
        transaction.setAmount(product.getPrice());
        transactionService.save(transaction);
        return paymentService.processPayment(paymentMethod,client,product);
    }
}
