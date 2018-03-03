import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.IntStream;

public class StoreManager {

    public Store setUpStore(){
        ArrayList<Category> categories = generateCategories();
        Inventory inventory = getInventory(categories);

        return new Store(
                inventory,
                getRegisters(),
                new DiscountEngine(getItemDiscounts(inventory), getCategoryDiscounts(categories), getOtherDiscounts()));
    }

    private HashMap<Item, Double> getItemDiscounts(Inventory inventory){
        HashMap<Item, Double> itemDiscounts = new HashMap<>();
        itemDiscounts.put(inventory.getItem("Lays"), 3.0);
        return itemDiscounts;
    }

    private HashMap<Discountables, Double> getOtherDiscounts() {
        HashMap<Discountables, Double> otherDiscounts = new HashMap<>();
        otherDiscounts.put(Discountables.STAFF, 5.0);
        otherDiscounts.put(Discountables.SENIOR_CITIZEN, 10.0);
        return otherDiscounts;
    }

    private HashMap<Category, Double> getCategoryDiscounts(ArrayList<Category> categories) {
        HashMap<Category, Double> categoryDiscounts = new HashMap<>();
        categoryDiscounts.put(categories.get(0), 10.0);
        categoryDiscounts.put(categories.get(1), 15.0);
        categoryDiscounts.put(categories.get(2), 20.0);
        return categoryDiscounts;
    }

    private ArrayList<Register> getRegisters() {
        ArrayList<Register> registers = new ArrayList<>();
        IntStream.rangeClosed(1,4)
                .forEach(value -> registers.add(new Register("Register" + value)));
        return registers;
    }

    private Inventory getInventory(ArrayList<Category> categories) {
        HashMap<Item, Integer> availableItems = new HashMap<>();
        availableItems.put(new Item("Lays", 10, categories.get(0)), 10);
        availableItems.put(new Item("Colgate", 100, categories.get(1)), 10);
        availableItems.put(new Item("Badam", 250, categories.get(2)), 10);
        return new Inventory(availableItems);
    }

    private ArrayList<Category> generateCategories(){
        ArrayList<Category> categories = new ArrayList<>();
        categories.add(new Category("Chips"));
        categories.add(new Category("Household"));
        categories.add(new Category("Healthy"));
        return categories;
    }
}
