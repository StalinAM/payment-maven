package ec.edu.uce.payment.jpa;

public class Record {
    private int id;
    private String date;
    private String state;
    private int idClient;
    private int nameClient;
    private int idProduct;
    private int nameProduct;
    private int quantity;
    private double price;
    private String paymentMethod;

    public Record() {
    }

    public Record(int id, String date, String state, int idClient, int nameClient, int idProduct, int nameProduct, int quantity, double price, String paymentMethod) {
        this.id = id;
        this.date = date;
        this.state = state;
        this.idClient = idClient;
        this.nameClient = nameClient;
        this.idProduct = idProduct;
        this.nameProduct = nameProduct;
        this.quantity = quantity;
        this.price = price;
        this.paymentMethod = paymentMethod;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public int getNameClient() {
        return nameClient;
    }

    public void setNameClient(int nameClient) {
        this.nameClient = nameClient;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public int getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(int nameProduct) {
        this.nameProduct = nameProduct;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String toString() {
        return "Record{" +
                "id=" + id +
                ", date='" + date + '\'' +
                ", state='" + state + '\'' +
                ", idClient=" + idClient +
                ", nameClient=" + nameClient +
                ", idProduct=" + idProduct +
                ", nameProduct=" + nameProduct +
                ", quantity=" + quantity +
                ", price=" + price +
                ", paymentMethod='" + paymentMethod + '\'' +
                '}';
    }
}
