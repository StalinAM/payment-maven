package ec.edu.uce.payment;

import ec.edu.uce.payment.model.Client;
import ec.edu.uce.payment.model.PaymentMethod;
import ec.edu.uce.payment.model.Product;
import ec.edu.uce.payment.model.Transaction;
import ec.edu.uce.payment.services.*;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    public String processTransaction(@QueryParam("clientId") int clientId,
                                     @QueryParam("productIds") String productIds,
                                     @QueryParam("paymentMethodId") int paymentMethodId) {
        Client client = clientService.findById(clientId);
        PaymentMethod paymentMethod = paymentMethodService.findById(paymentMethodId);

        Set<Product> products = productService.findProductsByIds(productIds);

        Transaction transaction = transactionService.createTransaction(client, products);
        transactionService.save(transaction, products);
        return paymentService.processPayment(paymentMethod, client, products);
    }

}
