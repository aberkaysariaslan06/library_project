package com.javalibproject.Menu.Admin.UserOperation;

import com.javalibproject.Exceptions.ViewUsersException;
import com.javalibproject.Menu.Generic.ConsoleReader;
import com.javalibproject.Menu.Generic.Menu;
import com.javalibproject.Menu.Generic.MenuName;
import com.javalibproject.Menu.Generic.MenuOptions;
import com.javalibproject.Repo.user.Customer;
import com.javalibproject.Service.UserService;
import com.javalibproject.System.SystemContext;

import java.util.Arrays;
import java.util.Optional;

public class EditUsersMenu extends Menu {

    public static final String USER_ID = "USER_ID"; // Define a constant for user ID
        // public UserLoginMenu(String title, UserService userService) {
    //     super("User Login Succesfully ! ", userService);
    //     //TODO Auto-generated constructor stub
    // }

    public EditUsersMenu(UserService userService) {
        super("------EDİT USER MENU------", userService);

             
    }


    @Override
    public MenuName execute() {
        printTitle();
        String userId = SystemContext.getProperty(USER_ID);
        if(userId == null ||userId.isEmpty()) {
            error("User ID is not set. Please search for a user first.");
            return MenuName.ADMIN_SEARCH_USERS; // go back to search users menu
        }
        try {
            Optional<Customer> customerOptional = getUserService().getById(Integer.valueOf(userId));
            Customer customer1 = customerOptional.orElseThrow();

           //String id  = printfAndGet("User ID", customer1.getUserId().toString());
           String userName = printfAndGet("Username", customer1.getUsername());
           String firstName = printfAndGet("First Name", customer1.getFirstName());
           String lastName = printfAndGet("Last Name", customer1.getLastName());
           String email = printfAndGet("Email", customer1.getEmail());
           String address = printfAndGet("Address", customer1.getAddress());
           String postCode = printfAndGet("Post Code", customer1.getPostCode());
           String city = printfAndGet("City", customer1.getCity());

           Customer updatedCustomer = new Customer(
                customer1.getUserId(), // keep the same ID
                userName,
                customer1.getPassword(), // keep the same password
                firstName,
                lastName,
                address,
                postCode,
                city,
                email
            );

            getUserService().updateUser(updatedCustomer);
            println("User updated successfully: " + updatedCustomer.getUsername());
            SystemContext.removeProperty(USER_ID); // clear the user ID after update
            return MenuName.ADMIN_MAIN_MENU;
    } catch (ViewUsersException vue) {
            error("Invalid User ID format: " + userId);
            return MenuName.ADMIN_SEARCH_USERS; // go back to search users menu
        }

    }
}



