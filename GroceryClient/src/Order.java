import java.util.ArrayList;

public class Order {
    private ArrayList<OrderItem> orderItems;
    private Customer customer;
    private double billAmount;

    public Order(ArrayList<OrderItem> orderItems, Customer customer) {
        this.orderItems = orderItems;
        this.customer = customer;
    }

    public ArrayList<OrderItem> getOrderItems() {
        return orderItems;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setBillAmount(double billAmount) {
        this.billAmount = billAmount;
    }

    @Override
    public String toString() {
        String items = orderItems.stream().map(orderItem-> {
            Item item = orderItem.getItem();
            return "Item: " + item.getName() + " | Quantity: " + orderItem.getQuantity() +  " | Price: " + item.getPrice() + " | Discount: " + orderItem.getDiscountApplied();
        }).reduce((item1, item2) -> "" + item1 + "\n" + item2).get();

        return "\n" + customer.toString() + "\nItems Bought\n" + items + "\nTotal: " + billAmount;
    }
}
