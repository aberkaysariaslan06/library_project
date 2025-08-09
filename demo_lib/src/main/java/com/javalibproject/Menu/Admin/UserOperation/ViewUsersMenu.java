package com.javalibproject.Menu.Admin.UserOperation;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.javalibproject.Exceptions.ViewUsersException;
import com.javalibproject.Menu.Generic.ConsoleReader;
import com.javalibproject.Menu.Generic.Menu;
import com.javalibproject.Menu.Generic.MenuName;
import com.javalibproject.Menu.Generic.MenuOptions;
import com.javalibproject.Repo.user.Customer;
import com.javalibproject.Repo.user.SystemUser;
import com.javalibproject.Service.CustomerService;
import com.javalibproject.Service.UserService;
import com.javalibproject.System.SystemContext;

import javax.swing.text.View;

public class ViewUsersMenu extends Menu {

    public static final String USER_ID = "USER_ID"; // Define a constant for user ID
        // public UserLoginMenu(String title, UserService userService) {
    //     super("User Login Succesfully ! ", userService);
    //     //TODO Auto-generated constructor stub
    // }

    public ViewUsersMenu(CustomerService customerService) {
        super("------VİEW USER MENU------", customerService);

        setMenu_options(Arrays.asList(
                new MenuOptions("E", "Edit User",
                        MenuName.ADMIN_EDIT_USERS),
                new MenuOptions("D", "Delete User",
                        MenuName.ADMIN_DELETE_USERS),
                new MenuOptions("M", "Back to Main Menu",
                       MenuName.ADMIN_MAIN_MENU)
        ));


             
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

            printfItem("User ID", customer1.getUserId().toString());
            printfItem("Username", customer1.getUsername());
            printfItem("First Name", customer1.getFirstName());
            printfItem("Last Name", customer1.getLastName());
            printfItem("Email", customer1.getEmail());
            printfItem("Address", customer1.getAddress());
            printfItem("Post Code", customer1.getPostCode());
            printfItem("City", customer1.getCity());
            println("--------------------------------");

        } catch (ViewUsersException vue) {
            error("Invalid User ID format: " + userId);
            return MenuName.ADMIN_SEARCH_USERS; // go back to search users menu
        }


       
        printOptions();
        return run();


    }
}



