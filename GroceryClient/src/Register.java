import java.util.ArrayList;

public class Register {
    private String name;
    private ArrayList<Order> orders;
    private boolean free;

    public Register(String name) {
        this.name = name;
        this.free = true;
        this.orders = new ArrayList<>();
    }

    private void addOrder(Order order) {
        this.orders.add(order);
    }

    public boolean isFree() {
        return free;
    }

    public Order generateBill(Order order, DiscountEngine discountEngine){
        blockRegister();
        order.setBillAmount(getBillAmount(order, discountEngine));
        addOrder(order);
        System.out.println(order);
        unblockRegister();
        return order;
    }

    private void blockRegister(){
        free = false;
    }

    private void unblockRegister(){
        free = true;
    }

    private double getBillAmount(Order order, DiscountEngine discountEngine) {
        return order.getOrderItems().stream()
                .mapToDouble(orderItem -> {
                    Item item = orderItem.getItem();
                    double discount = getDiscount(order, discountEngine, item);
                    orderItem.setDiscountApplied(discount);
                    double priceAfterDiscount = item.getPrice() - discount;
                    orderItem.setPrice(priceAfterDiscount);
                    return priceAfterDiscount;
                }).sum();
    }

    private double getDiscount(Order order, DiscountEngine discountEngine, Item item) {
        double discount = 0;

        discount += discountEngine.getItemDiscount(item);

        if(order.getCustomer().isSeniorCitizen()) {
            discount += discountEngine.applySeniorCitizenDiscount(item.getPrice());
        }

        if (order.getCustomer().isStaff()) {
            discount += discountEngine.applyStaffDiscount(item.getPrice());
        }

        return discount;
    }

}
