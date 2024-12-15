package ec.edu.uce.payment.classes;

import ec.edu.uce.payment.annotations.QualifierPayment;
import ec.edu.uce.payment.jpa.Client;
import ec.edu.uce.payment.jpa.Product;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
@QualifierPayment("BankTransfer")
public class BankTransfer implements IPayment {
    @Override
    public String pay(Client client, Product product) {
        return String.format(
                "¡Pago procesado exitosamente!\n" +
                        "---------------------------------\n" +
                        "Detalles del Usuario:\n" +
                        " - Nombre: %s\n" +
                        " - Correo: %s\n" + // Incluido correo en el formato
                        "\n" +
                        "Detalles del Producto:\n" +
                        " - Nombre: %s\n" +
                        " - Precio: $%.2f\n" +
                        "\n" +
                        "Detalles del Pago:\n" +
                        " - Método de Pago: Bank Transfer\n" +
                        " - Monto: $%.2f\n" +
                        "---------------------------------\n" +
                        "¡Gracias por su compra!",
                client.getName(), client.getEmail(), // Argumentos coinciden con el formato
                product.getName(), product.getPrice(),
                product.getPrice());
    }
}
