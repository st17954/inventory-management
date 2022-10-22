import java.io.*;
import java.util.ArrayList;


public class Inventory { // Class that manages the inventory of products (ArrayList of Products)

    private static Inventory inventoryInstance = null; // This method returns only the existing Inventory instance
    private static ArrayList<Product> inventory;

    private Inventory() throws IOException {
        inventory = new ArrayList<>(); // Empties the inventory

        // A persistent data file named "Database.csv" is loaded into inventory by the remainder of the constructor code
        File file = new File("Database.csv");

        // Creates readers for files
        FileReader reader = new FileReader(file);
        BufferedReader buffReader = new BufferedReader(reader);

        String line = ""; // needed for reading in csv
        String [] temp; // needed for reading in csv

        int productCounter = -1; // starts at -1 for handling first line headers

        while((line = buffReader.readLine()) != null) { // reads .csv
            temp = line.split(",");

            if (productCounter >= 0) { // handles first line headers

                // loads data from .csv into a Product
                Product newProd = new Product(temp[0]);

                newProd.setName(temp[1]);
                newProd.setPrice(Double.parseDouble(temp[2]));


                newProd.setStock(Integer.parseInt(temp[4]));

                addProductSec(newProd); // adds Product to inventory
            }

            productCounter++; // keeps track of the # of Products being loaded
        }

        System.out.println("====| " + productCounter + " products loaded into inventory");
        reader.close();
        buffReader.close();
    }

    public static Inventory getInstance() throws IOException { // for Singleton pattern, calls private constructor
        if (inventoryInstance == null) {
            inventoryInstance = new Inventory();
        }

        return inventoryInstance;
    }

    public boolean addProductSec(Product prod) { // for protecting data-accessing methods
        return addProduct(prod);
    } // for security, calls actual method

    private boolean addProduct(Product prod) { // adds given product to inventory ArrayList
        for(Product p : inventory){ // searches for matching ids
            if(p.getId().equals(prod.getId())){ // if ids match then don't add product
                return false;
            }
        }

        inventory.add(prod); // adds product to inventory ArrayList

        return true;
    }

    public boolean modProductSec(Product prod) { // for security, calls actual method
        return modProduct(prod);
    }

    private boolean modProduct(Product prod) { // modifies already existing Product
        for(Product p : inventory) {
            if (p.getId().equals(prod.getId())) { // search for id
                p.setName(prod.getName()); // modify name
                p.setPrice(prod.getPrice()); // modify price
                p.setStock(prod.getStock()); // modify stock

                return true;
            }
        }

        return false;
    }

    public ArrayList<Product> returnInventorySec() { // for security, calls actual method
        return returnInventory();
    }

    private ArrayList<Product> returnInventory() {

        return inventory;
    }


    public boolean removeProductSec(String id) { // for security, calls actual method
        return removeProduct(id);
    }

    private boolean removeProduct(String id) { // removes a Product from inventory, given an ID

        return true;
    }
}