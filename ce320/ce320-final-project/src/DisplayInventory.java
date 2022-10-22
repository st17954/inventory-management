import java.io.IOException;
import java.util.ArrayList;

public class DisplayInventory extends Modules { // system function, displays all Products (w/ data) in inventory

    public void moduleDriver() throws IOException { // drives module's function

        ArrayList<Product> inventory = Inventory.getInstance().returnInventorySec(); // gets copy of inventory ArrayList

        System.out.println("====| Current Inventory: ");
        System.out.printf("%-8s %-15s %-30s %-30s %8s %8s %-15s\n", "Line", "ID", "Product Name", "Product Description", "Price", "Stock", "Status");

        int lineCounter = 1; // When printing, line numbers are kept for reference

        //Produces product information by running through the inventory
        for (Product p : inventory) {
            String tempName;
            String tempDesc;
            String tempActive;
            String tempStock;

            if (p.getName().length() > 30) {
                tempName = p.getName().substring(0, 26);
                tempName = tempName + "...";
            } else {
                tempName = p.getName();
            }
            tempStock = Integer.toString(p.getStock());


            System.out.printf("%-8s %-15s %-30s %-30s %8s %8s %-15s  \n", lineCounter, p.getId(), tempName,p.getPrice(), tempStock);

            lineCounter++; // for increasing line number
        }

        returnToMain(); // exits module and returns to main, no user interaction w/ this module
    }
}