package ec.edu.uce.payment;

import ec.edu.uce.payment.jpa.Client;
import ec.edu.uce.payment.jpa.ClientService;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;


@Path("/clients")
public class HelloResource {
    private static final EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("Client");
    @GET
    @Produces("text/plain")
    @Path("/create")
    public String createClient() {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();


        Client client = new Client();
        client.setName("John Doe");
        client.setEmail("example@email.com");
        client.setPassword("password");
        client.setCardNumber("1234-5678-9101-1121");
        client.setAccountNumber("1234567890");
        ClientService clientService = new ClientService(em);
        clientService.createClient(client);

        return "listo!";
    }
    @GET
    @Produces("text/plain")
    @Path("/hello")
    public String hello() {
        return "Hello, World!";
    }
}