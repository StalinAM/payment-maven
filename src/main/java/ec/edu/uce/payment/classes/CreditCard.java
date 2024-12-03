package ec.edu.uce.payment.classes;

public class CreditCard implements IPayment {
    @Override
    public void pay(double amount) {
        System.out.println("Paying " + amount + " with Credit Card");
    }
}
