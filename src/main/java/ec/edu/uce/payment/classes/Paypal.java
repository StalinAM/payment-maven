package ec.edu.uce.payment.classes;

public class Paypal implements IPayment {
    @Override
    public void pay(double amount) {
        System.out.println("Paying " + amount + " with Paypal");
    }
}
