import java.util.HashMap;

public class Client {
    public static void main(String[] args){
        StoreManager manager = new StoreManager();
        Store groceryStore = manager.setUpStore();

        Customer praba = new Customer("Praba", 65, true);
        HashMap<String, Integer> itemsWantedByPraba = new HashMap<>();
        itemsWantedByPraba.put("Lays", 2);
        itemsWantedByPraba.put("Colgate", 1);

        Customer bala = new Customer("Bala", 35, false);
        HashMap<String, Integer> itemsWantedByBala = new HashMap<>();
        itemsWantedByBala.put("Lays", 1);
        itemsWantedByBala.put("Colgate", 2);
        itemsWantedByBala.put("Badam", 2);

        System.out.println("\nInitial Inventory");
        System.out.println(groceryStore.getInventory());

        groceryStore.processCustomerOrder(praba, itemsWantedByPraba);
        groceryStore.processCustomerOrder(bala, itemsWantedByBala);

        System.out.println("\nInventory after transactions");
        System.out.println(groceryStore.getInventory());
    }
}
