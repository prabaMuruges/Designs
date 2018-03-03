
public class Customer {
    private String name;
    private int age;
    private boolean storeStaff;

    public Customer(String name, int age, boolean storeStaff) {
        this.name= name;
        this.age = age;
        this.storeStaff = storeStaff;
    }

    public boolean isStaff(){
        return storeStaff;
    }

    public boolean isSeniorCitizen(){
        return (age > 60);
    }

    @Override
    public String toString() {
        return "Customer: " + name + "\n" + "Age: " + age + "\n";
    }
}
