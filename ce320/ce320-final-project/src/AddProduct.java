import java.io.IOException;
import java.util.Scanner;



public class AddProduct extends Modules { // Adds a product to the inventory using the system function

    public void moduleDriver() throws IOException {
        userInput();

        try { // the java IDE gods said put this in so...
            returnToMain();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void userOptions() throws IOException { // Displays the main menu for the module
        System.out.println("1 -> Add Product " + "\n2 -> Exit to Main Menu"); // Menu
        Scanner str = new Scanner(System.in); //Objects are created for scanners
        String inputString = str.nextLine(); // As long as the user enters the correct information, no errors are created

        if(inputString.equals("1")){ // Add product
            userInput();
        }
        else if(inputString.equals("2")){ // exit to main
            returnToMain();
        }
        else{ // menu
            System.out.println("====| Input Not Understood -> Try Again");
            userOptions();
        }
    }

    public void userInput() throws IOException { // Modifies Product details based on user input

        Scanner n = new Scanner(System.in);
        Scanner p = new Scanner(System.in);
        Scanner d = new Scanner(System.in);
        Scanner s = new Scanner(System.in);
        Scanner a = new Scanner(System.in);
        Scanner i = new Scanner(System.in);

        // take id from user
        System.out.println(">>>> Enter ID: ");
        String id = i.nextLine();

        // take name from user
        System.out.println(">>>> Enter Name: ");
        String name = n.nextLine();

        // take price from user
        System.out.println(">>>> Enter Price: ");
        Double price = 0.0;
        try{
            price = p.nextDouble();
        }
        catch(Exception e){ // if input is not a double
            System.out.println("====| Input Not Understood -> Try Again");
            userOptions();
            return;
        }



        // take stock from user
        System.out.println(">>>> Enter Stock: ");
        int stock = 0;
        try{
            stock = s.nextInt();
        }
        catch(Exception e){ // if input is not an int
            System.out.println("====| Input Not Understood -> Try Again");
            userOptions();
            return;
        }

        // take active from user


        // sets variables for the product
        Product prod = new Product(id);
        prod.setName(name);
        prod.setPrice(price);

        prod.setStock(stock);


        // adds product to inventory
        Inventory inventory = Inventory.getInstance();
        boolean add = inventory.addProductSec(prod);

        if(add == false){ // if addProductSec can't add a product
            System.out.println("====| Product '" + id + "' already exists -> Try Again");
        }
        else{ // product was added successfully
            System.out.println("====| Product '" + id + "' was added");
        }
    }
}
