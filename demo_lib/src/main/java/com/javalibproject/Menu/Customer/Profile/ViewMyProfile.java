package com.javalibproject.Menu.Customer.Profile;

import com.javalibproject.Exceptions.ViewUsersException;
import com.javalibproject.Menu.Generic.Menu;
import com.javalibproject.Menu.Generic.MenuName;
import com.javalibproject.Menu.Generic.MenuOptions;
import com.javalibproject.Repo.user.Customer;
import com.javalibproject.Service.UserService;
import com.javalibproject.System.SystemContext;

import java.util.Arrays;
import java.util.Optional;

public class ViewMyProfile extends Menu {
    public ViewMyProfile(UserService userService) {
        super("------VİEW MY PROFILE MENU------", userService);
        setMenu_options(Arrays.asList(

                new MenuOptions("U", "Update my profile",
                        MenuName.USER_UPDATE_PROFILE),
                new MenuOptions("R", "Return to my profile",
                        MenuName.USER_MAIN_MENU)

        ));
    }

    @Override
    public MenuName execute() {
        printTitle();
        Integer userId = SystemContext.getLoggedInUserID();
        Optional<Customer> customerOptional = getUserService().getById((userId));
        Customer customer1 = customerOptional.orElseThrow();
        printfItem("User ID", customer1.getUserId().toString());
        printfItem("Username", customer1.getUsername());
        printfItem("First Name", customer1.getFirstName());
        printfItem("Last Name", customer1.getLastName());
        printfItem("Email", customer1.getEmail());
        printfItem("Address", customer1.getAddress());
        printfItem("Post Code", customer1.getPostCode());
        printfItem("City", customer1.getCity());
        println("--------------------------------------");


        printOptions();
        return run();


    }
}
