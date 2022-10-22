import java.util.UUID;

public class Product { // Information about a single product in a data class


    private String name;
    private double price;
    private int stock;
    private String id;

    Product(String inputID) { // It is not possible to change the ID after creating a Product with a specific ID
        this.id = inputID;
        name = "No Name Set";
        price = 0.00;
        stock = 0;
    }

    //getters and setters for the all data attributes EXCEPT ID
    public void setName(String s) {
        name = s;
    }
    public String getName() {
        return name;
    }

    public void setPrice(double p) {
        price = p;
    }
    public double getPrice() {
        return price;
    }


    public void setStock(int p) {
        stock = p;
    }
    public int getStock() {
        return stock;
    }


    public String getId(){ return id; }
}