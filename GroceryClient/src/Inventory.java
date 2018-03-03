import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Inventory {
    private HashMap<Item, Integer> availableItems;

    public Inventory(HashMap<Item, Integer> availableItems) {
        this.availableItems = availableItems;
    }

    public OrderItem getOrderItem(String itemName, int itemQuantity){
        Item item = getItem(itemName);

        if(itemQuantity <= availableItems.get(item)) {
             return new OrderItem(item, itemQuantity);
        } else {
            throw new RuntimeException("Sorry we don't have requested quantity for " + itemName);
        }
    }

    public void adjust(ArrayList<OrderItem> processedOrderItems){
        processedOrderItems.forEach(orderItem -> {
            Item item = orderItem.getItem();
            int oldQuantity = availableItems.get(item);
            if(oldQuantity == orderItem.getQuantity()) {
                availableItems.remove(item);
            } else {
                availableItems.put(item, oldQuantity - orderItem.getQuantity());
            }
        });
    }

    public Item getItem(String itemName) {
        return availableItems.keySet()
                    .stream()
                    .filter(inventoryItem -> inventoryItem.getName().equals(itemName))
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("Sorry we don't have " + itemName));
    }

    @Override
    public String toString() {
        StringBuilder inventoryString = new StringBuilder();
        for (Map.Entry<Item, Integer> inventoryItem : availableItems.entrySet()) {
            inventoryString.append("Item: ").append(inventoryItem.getKey().getName()).append(" | Available Quantity: ").append(inventoryItem.getValue()).append("\n");
        }
        return inventoryString.toString();
    }
}
