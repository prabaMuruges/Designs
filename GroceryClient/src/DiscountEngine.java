import java.util.HashMap;

public class DiscountEngine {
    private HashMap<Item, Double> itemDiscounts;
    private HashMap<Category, Double> categoryDiscounts;
    private HashMap<Discountables, Double> otherDiscounts;

    public DiscountEngine(HashMap<Item, Double> itemDiscounts,
                          HashMap<Category, Double> categoryDiscounts,
                          HashMap<Discountables, Double> otherDiscounts) {
        this.itemDiscounts = itemDiscounts;
        this.categoryDiscounts = categoryDiscounts;
        this.otherDiscounts = otherDiscounts;
    }

    public double getItemDiscount(Item item){
        double discount = 0;
        if (itemDiscounts.get(item) != null) {
            discount = getDiscountFromPercentage(item.getPrice(), itemDiscounts.get(item));
        } else if(categoryDiscounts.get(item.getCategory()) != null){
            discount = getDiscountFromPercentage(item.getPrice(), categoryDiscounts.get(item.getCategory()));
        }
        return discount;
    }

    public double applyStaffDiscount(double price){
        double discount = 0;
        if(otherDiscounts.get(Discountables.STAFF) != null){
            discount = getDiscountFromPercentage(price, otherDiscounts.get(Discountables.STAFF));
        }
        return discount;
    }

    public double applySeniorCitizenDiscount(double price){
        double discount = 0;
        if(otherDiscounts.get(Discountables.SENIOR_CITIZEN) != null){
            discount = getDiscountFromPercentage(price, otherDiscounts.get(Discountables.SENIOR_CITIZEN));
        }
        return discount;
    }

    private double getDiscountFromPercentage(double price, double percentage){
        return price * (percentage / 100);
    }
}
