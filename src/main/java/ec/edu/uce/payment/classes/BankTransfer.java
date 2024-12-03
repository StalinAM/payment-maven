package ec.edu.uce.payment.classes;

public class BankTransfer implements IPayment {
    @Override
    public void pay(double amount) {
        System.out.println("Paying " + amount + " with Bank Transfer");
    }
}
