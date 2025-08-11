package com.javalibproject.Menu.Admin.UserOperation;

import com.javalibproject.Exceptions.ViewUsersException;
import com.javalibproject.Menu.Generic.ConsoleReader;
import com.javalibproject.Menu.Generic.Menu;
import com.javalibproject.Menu.Generic.MenuName;
import com.javalibproject.Repo.user.User.Customer;
import com.javalibproject.Service.CustomerService;
import com.javalibproject.Service.UserService;
import com.javalibproject.System.SystemContext;

import java.util.Optional;

public class CreateUsersMenu extends Menu {

    public static final String USER_ID = "USER_ID"; // Define a constant for user ID
    // public UserLoginMenu(String title, UserService userService) {
    // super("User Login Succesfully ! ", userService);
    // //TODO Auto-generated constructor stub
    // }

    public CreateUsersMenu(CustomerService customerService) {
        super("------CREATE USER MENU------", customerService);

    }

    private String printfAndGet(String text) { // edit user properties function
        // System.out.printf("%-20s: %s --> New Value : ", label, value);
        System.out.printf("%-20s: ", text); // Print the label
        // Print the label and current value, then prompt for new value
        String input = ConsoleReader.readLine();
        if (input == null || input.trim().equals("")) {
            return text; // if no input, keep the old value(bosluga bastim edit yapma demek icin)
        } else {
            return input; // return the new value
        }
    }

    @Override
    public MenuName execute() {
        printTitle();

        String userName = printfAndGet("Username");
        String password = printfAndGet("Password");
        String firstName = printfAndGet("First Name");
        String lastName = printfAndGet("Last Name");
        String email = printfAndGet("Email");
        String address = printfAndGet("Address");
        String city = printfAndGet("City");
        String postCode = printfAndGet("Post Code");

        Customer newCustomer = new Customer(null, userName, password, firstName, lastName, email, address, postCode,
                city);
        getUserService().createUser(newCustomer);
        System.out.println();
        println("User created successfully: " + newCustomer.getUsername());
        println("Devam etmek icin bir tusa basiniz...");
        ConsoleReader.readLine();

        return MenuName.ADMIN_MAIN_MENU;

    }
}
