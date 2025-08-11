package com.javalibproject.Menu.Admin.UserOperation;

import com.javalibproject.Exceptions.ViewUsersException;
import com.javalibproject.Menu.Generic.Menu;
import com.javalibproject.Menu.Generic.MenuName;
import com.javalibproject.Repo.user.User.Customer;
import com.javalibproject.Service.CustomerService;
import com.javalibproject.Service.UserService;
import com.javalibproject.System.SystemContext;

import java.util.Optional;

public class DeleteUsersMenu extends Menu {

    public static final String USER_ID = "USER_ID"; // Define a constant for user ID
    // public UserLoginMenu(String title, UserService userService) {
    // super("User Login Succesfully ! ", userService);
    // //TODO Auto-generated constructor stub
    // }

    public DeleteUsersMenu(CustomerService customerService) {
        super("------DELETE USER MENU------", customerService);

    }

    @Override
    public MenuName execute() {
        printTitle();
        String userId = SystemContext.getProperty(USER_ID);
        if (userId == null || userId.isEmpty()) {
            error("User ID is not set. Please search for a user first.");
            return MenuName.ADMIN_SEARCH_USERS; // go back to search users menu
        }
        try {
            Optional<Customer> customerOptional = getUserService().getById(Integer.valueOf(userId));
            Customer customer1 = customerOptional.orElseThrow();

            getUserService().deleteUserByUserId(customer1.getUserId());
            println("User deleted successfully ! " + "Customer id - username :" + customer1.getUserId() + " - "
                    + customer1.getUsername());
            SystemContext.removeProperty(USER_ID); // clear the user ID after deletion
            return MenuName.ADMIN_MAIN_MENU;
        } catch (ViewUsersException vue) {
            error("Invalid User ID format: " + userId);
            return MenuName.ADMIN_MAIN_MENU; // go back to search users menu
        }

    }
}
