import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Search extends Modules { // The system provides users with the ability to display products based on parameters they specify

    public void moduleDriver() throws IOException { // drives module
        moduleMenu();

        try {
            returnToMain();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void moduleMenu() throws IOException { // The main menu of the module is displayed, and input is handled
        System.out.println("====| Search By: ");
        System.out.println("1 -> ID " +
                "\n2 -> Name" +
                "\n0 -> Exit to Main Menu");

        System.out.println(">>>> Enter Menu #: ");
        Scanner scanner = new Scanner(System.in);
        String userChoice = scanner.nextLine();

        if (userChoice.equals("0")) { // Provides a direct link to the main menu for the user
            returnToMain();
        }
        else if(!userChoice.equals("1") && !userChoice.equals("2") && !userChoice.equals("3") && !userChoice.equals("4")){
            // user input error-catcher
            System.out.println("====| Input Not Understood -> Try Again");

            moduleMenu();
        }

        System.out.println(">>>> Enter Search Parameter: ");
        Scanner scanner1 = new Scanner(System.in);
        String searchParam = scanner1.nextLine();
        searchParam = searchParam.toLowerCase();

        searchFunction(userChoice, searchParam);
    }

    private void searchFunction(String userChoice, String searchParam) throws IOException { // Ensures code readability by performing module functionality
        ArrayList<Product> inventory = Inventory.getInstance().returnInventorySec();// Copy of current inventory is obtained

        ArrayList<Product> searchResults = new ArrayList<>(); // for storing results of a search
        String searchType = null; // Used when calling displaySearchResults() to retrieve the results of the search

        switch (userChoice) { // for appropriate handling of userChoice
            case "1" -> { // search by ID
                searchType = "ID";

                for (Product eachProduct : inventory) {  //  The inventory should be checked to see if each Product ID contains a matching substring
                    String tempProduct = eachProduct.getId();

                    tempProduct = tempProduct.toLowerCase();

                    if (tempProduct.contains(searchParam)) {
                        searchResults.add(eachProduct);
                    }
                }
            }

            case "2" ->  { // search by name
                searchType = "Name";

                for (Product eachProduct : inventory) {  //  The inventory should be checked to see if each Product ID contains a matching substring
                    String tempProduct = eachProduct.getName();

                    tempProduct = tempProduct.toLowerCase();

                    if (tempProduct.contains(searchParam)) {
                        searchResults.add(eachProduct);
                    }
                }
            }



            default -> {
                System.out.println("====| Input Not Understood -> Try Again");
                moduleMenu();
            }
        }

        displaySearchResults(searchResults, searchType);

        moduleMenu();
    }

    private void displaySearchResults(ArrayList<Product> results, String searchType) throws IOException {
        // A Product Array and a searchType are required for display/printing

        int lineCounter = 1; // The line number of the product being printed is kept track of

        if (results.isEmpty()) {
            System.out.println("====| No results to display ");

            moduleMenu();
        }

        System.out.println("====| Search by " + searchType + " Results: ");
        System.out.printf("%-8s %-15s %-30s %-30s %8s %8s %-15s\n", "Line", "ID", "Product Name",  "Price", "Stock");

        //runs through the inventory and prints out product information
        for (Product p : results) {
            String tempName;

            String tempStock;
            //If name/desc is longer than 30 chars, will cut them off at 27 and add 3 dots.
            if (p.getName().length() > 30) {
                tempName = p.getName().substring(0, 26);
                tempName = tempName + "...";
            } else {
                tempName = p.getName();
            }

            tempStock = Integer.toString(p.getStock());
            //Print out inventory list.

            System.out.printf("%-8s %-15s %-30s %-30s %8s %8s %-15s  \n", lineCounter, p.getId(), tempName, p.getPrice(), tempStock);

            lineCounter++;
        }
    }
}