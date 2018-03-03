public class Item {
    private String name;
    private float price;
    private Category category;

    public Item(String name, float price, Category category) {
        this.name = name;
        this.price = price;
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public Category getCategory() {
        return category;
    }

    public float getPrice() {
        return price;
    }
}
