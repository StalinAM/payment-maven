package ec.edu.uce.payment.paymentmethods;

import ec.edu.uce.payment.annotations.QualifierPayment;
import ec.edu.uce.payment.model.Client;
import ec.edu.uce.payment.model.Product;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Set;

@ApplicationScoped
@QualifierPayment("BankTransfer")
public class BankTransfer implements IPayment {
    @Override
    public String pay(Client client, Set<Product> products) {
        double totalAmount = products.stream().mapToDouble(Product::getPrice).sum();

        StringBuilder productDetails = new StringBuilder();
        for (Product product : products) {
            productDetails.append(String.format(
                    " - Nombre: %s\n" +
                            " - Precio: $%.2f\n",
                    product.getName(), product.getPrice()));
        }

        return String.format(
                "¡Pago procesado exitosamente!\n" +
                        "---------------------------------\n" +
                        "Detalles del Usuario:\n" +
                        " - Nombre: %s\n" +
                        " - Correo: %s\n" +
                        "\n" +
                        "Detalles del Producto:\n" +
                        "%s" +
                        "\n" +
                        "Detalles del Pago:\n" +
                        " - Método de Pago: Bank Transfer\n" +
                        " - Monto Total: $%.2f\n" +
                        "---------------------------------\n" +
                        "¡Gracias por su compra!",
                client.getName(), client.getEmail(),
                productDetails.toString(),
                totalAmount);
    }
}
