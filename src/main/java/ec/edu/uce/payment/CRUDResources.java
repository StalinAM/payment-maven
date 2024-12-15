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
    public String initData() {

        if (isDatabaseEmpty()) {
            createInitialData();
            return "Datos iniciales subidos!";
        } else {
            return getDatabaseInfo();
        }

    }

    @GET
    @Produces("text/plain")
    @Path("/client/create")
    public String createClient(@QueryParam("name") String name, @QueryParam("email") String email) {
        if (name == null || email == null) {
            return "Debe proporcionar nombre y correo electrónico.";
        }
        Client client = new Client();
        client.setName(name);
        client.setEmail(email);
        clientService.save(client);
        return "Cliente " + client.getName() + " creado!";
    }

    @GET
    @Produces("text/plain")
    @Path("/client/update")
    public String updateClient(@QueryParam("id") int id, @QueryParam("name") String name, @QueryParam("email") String email) {
        if (id == 0 || name == null || email == null) {
            return "Debe proporcionar ID, nombre y correo electrónico.";
        }
        Client client = clientService.findById(id);
        if (client != null) {
            client.setName(name);
            client.setEmail(email);
            clientService.update(client);
            return "Cliente " + client.getName() + " actualizado!";
        } else {
            return "Cliente no encontrado!";
        }
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
    public String createProduct(@QueryParam("name") String name, @QueryParam("price") double price) {
        if (name == null || price == 0) {
            return "Debe proporcionar nombre y precio del producto.";
        }
        Product product = new Product();
        product.setName(name);
        product.setPrice(price);
        productService.save(product);
        return "Producto " + product.getName() + " creado!";
    }

    @GET
    @Produces("text/plain")
    @Path("/product/{id}")
    public String getProduct(@PathParam("id") int id) {

        Product product = productService.findById(id);
        if (product == null) {
            return "Producto no encontrado!";
        }
        return "Producto " + product.getName() + " encontrado!";
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
    public String updateProduct(@QueryParam("id") int id, @QueryParam("name") String name, @QueryParam("price") double price) {
        if (id == 0 || name == null || price == 0) {
            return "Debe proporcionar ID, nombre y precio del producto.";
        }
        Product product = productService.findById(id);
        if (product != null) {
            product.setName(name);
            product.setPrice(price);
            productService.update(product);
            return "Producto " + product.getName() + " actualizado!";
        } else {
            return "Producto no encontrado!";
        }
    }

    private boolean isDatabaseEmpty() {
        return paymentMethodService.list().isEmpty() && clientService.list().isEmpty() && productService.list().isEmpty();
    }

    private void createInitialData() {
        paymentMethodService.save(new PaymentMethod("creditcard"));
        paymentMethodService.save(new PaymentMethod("paypal"));
        paymentMethodService.save(new PaymentMethod("banktransfer"));

        clientService.save(new Client("John Doe", "john.doe@email.com"));
        clientService.save(new Client("Jane Smith", "jane.smith@email.com"));
        clientService.save(new Client("Carlos Rivera", "carlos.rivera@email.com"));
        clientService.save(new Client("Maria Gómez", "maria.gomez@email.com"));
        clientService.save(new Client("Luis Martínez", "luis.martinez@email.com"));

        productService.save(new Product("Café Espresso", 10.5));
        productService.save(new Product("Café Latte", 12.0));
        productService.save(new Product("Café Americano", 8.5));
    }

    private String getDatabaseInfo() {
        List<Client> allClients = clientService.list();
        List<Product> allProducts = productService.list();
        List<PaymentMethod> listPaymentMethods = paymentMethodService.list();
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