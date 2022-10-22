import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class UserInterface { // In addition to driving the system, this class handles user input as well as serving as the main menu for the system
    // Passes 'control' of system functions to system modules that contain those functions
    //No need to hold onto a single reference or static user interface, userInterface can be created multiple times with no effect on system function

    public Modules currentModule = null; // var for interacting with interface methods of module classes

    public UserInterface() throws IOException { // loads .csv upon creation, essentially loading persistent data on start
        // calls menuMain() to act as class driver

        Inventory.getInstance(); // calls constructor for inventory to load, singleton pattern so no bad effects?

        menuMain(); // displays main menu
    }

    public void runCurrentModule() throws IOException { // for running the module, used by menuMain()
        currentModule.moduleDriver(); // calls current module's driver to start that respective function
    }

    public void changeModule(Modules nextModule) { // used by menuMain() to change system function modules depending on user input
        // can also be called and used by system module if modules would like to redirect to other modules

        currentModule = nextModule;
    }

    public void menuMain() throws IOException {
        System.out.println("====| Main Menu: " + // this will dispaly all the menu options for the user
                "\n1 -> Add a Product" +
                "\n2 -> Search" +
                "\n3 -> Edit a Product" +
                "\n4 -> Display Inventory" +
                "\n0 -> SAVE & EXIT");

        Scanner str = new Scanner(System.in); // trying to get user input

        System.out.println(">>>> Enter Menu #: ");

        String inputString = str.nextLine(); // takes everything the user has enetered

        if (inputString.equals("0")) { // SAVE & EXIT
            System.out.println("====| Saving...");

            File persistData = new File("Databasenew.csv");

            if (persistData.delete()) { // deleting the old to save new data
            }
            else {
                System.out.println("====| NO CHANGES SAVED: issues saving data |====1");
            }

            if (persistData.createNewFile()) { // creating a new blank file, in order to view everything user must save it first

                PrintWriter writer = new PrintWriter(persistData);
                ArrayList<Product> inventory = Inventory.getInstance().returnInventorySec();
                writer.append("id,name,price,description,active,stock\n");

                for (Product eachProduct : inventory) {  // prints the product infor with commas
                    writer.append(eachProduct.getId());
                    writer.append(",");
                    writer.append(eachProduct.getName());
                    writer.append(",");
                    writer.append(Double.toString(eachProduct.getPrice()));
                    writer.append(",");
                    writer.append(",");
                    writer.append(",");
                    writer.append(Integer.toString(eachProduct.getStock()));
                    writer.append("\n");

                }
                writer.flush();
                writer.close();
            }
            else {
                System.out.println("====| NO CHANGES SAVED: issues saving data |====");
            }


            System.out.println("====| Closing...");
            System.exit(0);

        } else if (inputString.equals("1")) { // adding a product
            changeModule(new AddProduct());

            runCurrentModule();

        } else if (inputString.equals("2")) { // searching by id
            changeModule(new Search());

            runCurrentModule();

        } else if (inputString.equals("3")) { // editing a product
            changeModule(new EditProduct());

            runCurrentModule();

        } else if (inputString.equals("4")) { // diaplays everything
            changeModule(new DisplayInventory());

            runCurrentModule();

        }
        else {
            System.out.println("====| Input Not Understood -> Try Again"); // detects user input

            menuMain(); // this will loop the main menu
        }
    }

    public static void main(String[] args) throws IOException {
        new UserInterface();
    }
}