import java.util.ArrayList;
import java.util.HashMap;

public class Store {
    private Inventory inventory;
    private ArrayList<Register> registers;
    private DiscountEngine discountEngine;

    public Store(Inventory inventory, ArrayList<Register> registers, DiscountEngine discountEngine) {
        this.inventory = inventory;
        this.registers = registers;
        this.discountEngine = discountEngine;
    }

    public void processCustomerOrder(Customer customer, HashMap<String, Integer> customerOrders){
        Order order = makeOrder(customer, customerOrders);
        getRegister().generateBill(order, discountEngine);
        inventory.adjust(order.getOrderItems());
    }

    private Order makeOrder(Customer customer, HashMap<String, Integer> customerOrders) {
        ArrayList<OrderItem> orderItems = new ArrayList<>();
        customerOrders.forEach((itemName, quantity) -> {
            OrderItem orderItem = inventory.getOrderItem(itemName, quantity);
            orderItems.add(orderItem);
        });
        return new Order(orderItems, customer);
    }

    private Register getRegister(){
        return registers.stream()
                .filter(Register::isFree)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Sorry All Registers are Busy!"));
    }

    public Inventory getInventory() {
        return inventory;
    }
}
