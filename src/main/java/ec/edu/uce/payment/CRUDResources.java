package ec.edu.uce.payment;

import ec.edu.uce.payment.model.Client;
import ec.edu.uce.payment.model.PaymentMethod;
import ec.edu.uce.payment.model.Product;
import ec.edu.uce.payment.services.ClientService;
import ec.edu.uce.payment.services.PaymentMethodService;
import ec.edu.uce.payment.services.ProductService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;

import java.util.List;


@Path("/crud")
public class CRUDResources {

    @Inject
    ClientService clientService;

    @Inject
    ProductService productService;

    @Inject
    PaymentMethodService paymentMethodService;

    @GET
    @Produces("text/plain")
    @Path("/payment-methods")
    public String getPaymentMethods() {
        List<PaymentMethod> listPaymentMethods = paymentMethodService.list();
        return listPaymentMethods.toString();
    }

    @GET
    @Produces("text/plain")
    @Path("/init-data/create")
    public String createPaymentMethod() {

        if (paymentMethodService.list().isEmpty() && clientService.list().isEmpty() && productService.list().isEmpty()) {
            PaymentMethod paymentMethod1 = new PaymentMethod();
            paymentMethod1.setName("creditcard");

            PaymentMethod paymentMethod2 = new PaymentMethod();
            paymentMethod2.setName("paypal");

            PaymentMethod paymentMethod3 = new PaymentMethod();
            paymentMethod3.setName("banktransfer");

            // Guardar los tres métodos de pago
            paymentMethodService.save(paymentMethod1);
            paymentMethodService.save(paymentMethod2);
            paymentMethodService.save(paymentMethod3);

            Client client1 = new Client();
            client1.setName("John Doe");
            client1.setEmail("john.doe@email.com");
            clientService.save(client1);

            Client client2 = new Client();
            client2.setName("Jane Smith");
            client2.setEmail("jane.smith@email.com");
            clientService.save(client2);

            Client client3 = new Client();
            client3.setName("Carlos Rivera");
            client3.setEmail("carlos.rivera@email.com");
            clientService.save(client3);

            Client client4 = new Client();
            client4.setName("Maria Gómez");
            client4.setEmail("maria.gomez@email.com");
            clientService.save(client4);

            Client client5 = new Client();
            client5.setName("Luis Martínez");
            client5.setEmail("luis.martinez@email.com");
            clientService.save(client5);

            Product product1 = new Product();
            product1.setName("Café Espresso");
            product1.setPrice(10.5);
            productService.save(product1);

            Product product2 = new Product();
            product2.setName("Café Latte");
            product2.setPrice(12.0);
            productService.save(product2);

            Product product3 = new Product();
            product3.setName("Café Americano");
            product3.setPrice(8.5);
            productService.save(product3);
            return "Datos iniciales subidos!";
        } else {

            List<Client> allClients = clientService.list();
            List<Product> allProducts = productService.list();
            List<PaymentMethod> listPaymentMethods = paymentMethodService.list();

            // Generamos la salida para cada lista
            StringBuilder result = new StringBuilder();

            // Mostrar los clientes
            result.append("Clientes: ").append(allClients.size()).append("\n")
                    .append("-------------------------------\n");
            for (Client client : allClients) {
                result.append("ID: ").append(client.getId())
                        .append(", Nombre: ").append(client.getName())
                        .append(", Correo: ").append(client.getEmail())
                        .append("\n");
            }

            // Mostrar los productos
            result.append("\nProductos: ").append(allProducts.size()).append("\n")
                    .append("-------------------------------\n");
            for (Product product : allProducts) {
                result.append("ID: ").append(product.getId())
                        .append(", Nombre: ").append(product.getName())
                        .append(", Precio: ").append(product.getPrice())
                        .append("\n");
            }

            // Mostrar los métodos de pago
            result.append("\nMétodos de pago: ").append(listPaymentMethods.size()).append("\n")
                    .append("-------------------------------\n");
            for (PaymentMethod paymentMethod : listPaymentMethods) {
                result.append("ID: ").append(paymentMethod.getId())
                        .append(", Nombre: ").append(paymentMethod.getName())
                        .append("\n");
            }

            return result.toString();

        }

    }

    @GET
    @Produces("text/plain")
    @Path("/client/create")
    public String createClient() {
        Client client = new Client();
        client.setName("John Doe");
        client.setEmail("example@email.com");
        clientService.save(client);

        return "Cliente " + client.getName() + " creado!";
    }

    @GET
    @Produces("text/plain")
    @Path("/client/update")
    public String updateClient() {
        Client client = new Client();
        client.setId(1);
        client.setName("John Does");
        client.setEmail("example@email.com");
        clientService.update(client);

        return "Cliente " + client.getName() + " actualizado!";
    }

    @GET
    @Produces("text/plain")
    @Path("/client/{id}")
    public String getClient(@PathParam("id") int id) {
        Client client = clientService.findById(id);
        if (client == null) {
            return "Cliente no encontrado!";
        }
        return client.getName() + " encontrado!";
    }

    @GET
    @Produces("text/plain")
    @Path("/client/all")
    public String getAllClients() {
        List<Client> allClients = clientService.list();
        return allClients.toString();
    }

    @GET
    @Produces("text/plain")
    @Path("/product/create")
    public String createProduct() {
        Product product = new Product();
        product.setName("Cafe");
        product.setPrice(20.5);
        productService.save(product);

        return "Product " + product.getName() + " creado!";
    }

    @GET
    @Produces("text/plain")
    @Path("/product/{id}")
    public String getProduct(@PathParam("id") int id) {

        Product product = productService.findById(id);
        if (product == null) {
            return "Producto no encontrado!";
        }
        return product.getName();
    }

    @GET
    @Produces("text/plain")
    @Path("/product/all")
    public String getAllProducts() {
        List<Product> allProducts = productService.list();
        return allProducts.toString();
    }

    @GET
    @Produces("text/plain")
    @Path("/product/update")
    public String updateProduct() {
        Product product = new Product();
        product.setId(1);
        product.setName("Cafes");
        product.setPrice(10.5);
        productService.update(product);
        return "Product " + product.getName() + " actualizado!";
    }

    @GET
    @Produces("text/plain")
    @Path("/product/delete")
    public String deleteProduct() {
        Product product = productService.findById(1);
        if (product == null) {
            return "Producto no encontrado!";
        }
        return product.getName() + " eliminado!";
    }

}