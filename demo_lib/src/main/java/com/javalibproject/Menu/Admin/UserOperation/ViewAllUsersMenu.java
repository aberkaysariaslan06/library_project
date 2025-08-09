package com.javalibproject.Menu.Admin.UserOperation;

import com.javalibproject.Exceptions.ViewUsersException;
import com.javalibproject.Menu.Generic.*;
import com.javalibproject.Repo.user.Customer;
import com.javalibproject.Service.BookService;
import com.javalibproject.Service.CustomerService;
import com.javalibproject.Service.UserService;
import com.javalibproject.System.SystemContext;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class ViewAllUsersMenu extends ShowUsers {

    public static final String USER_ID = "USER_ID"; // Define a constant for user ID

    public ViewAllUsersMenu(CustomerService customerService) {
        super("------VİEW ALL USER MENU------", customerService);

        setMenu_options(Arrays.asList(
                new MenuOptions("S", "Search Users",
                        MenuName.ADMIN_SEARCH_USERS),
                new MenuOptions("M", "Back to Main Menu",
                        MenuName.ADMIN_MAIN_MENU)
        ));



    }

    @Override
    public MenuName execute() {
        printTitle();
        List<Customer> customers = fetchAllUsers();
        if(customers == null || customers.isEmpty()) {
            printOptions();
            run();
        }
        showAllUsers();
        printOptions();
        return run();




    }




}
