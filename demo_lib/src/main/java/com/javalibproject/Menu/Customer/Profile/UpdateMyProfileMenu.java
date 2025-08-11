package com.javalibproject.Menu.Customer.Profile;

import com.javalibproject.Exceptions.ViewUsersException;
import com.javalibproject.Menu.Generic.ConsoleReader;
import com.javalibproject.Menu.Generic.Menu;
import com.javalibproject.Menu.Generic.MenuName;
import com.javalibproject.Repo.user.User.Customer;
import com.javalibproject.Service.UserService;
import com.javalibproject.System.SystemContext;

import java.util.Optional;

public class UpdateMyProfileMenu extends Menu {

    public UpdateMyProfileMenu(UserService userService) {
        super("------UPDATE MY PROFILE MENU------", userService);

    }

    @Override
    public MenuName execute() {

        printTitle();
        Integer userId = SystemContext.getLoggedInUserID();
        Optional<Customer> customerOptional = getUserService().getById(userId);
        Customer customer1 = customerOptional.orElseThrow();

        // String id = printfAndGet("User ID", customer1.getUserId().toString());
        String userName = printfAndGet("Username", customer1.getUsername());
        String password = printfAndGet("Password", " ");
        String firstName = printfAndGet("First Name", customer1.getFirstName());
        String lastName = printfAndGet("Last Name", customer1.getLastName());
        String email = printfAndGet("Email", customer1.getEmail());
        String address = printfAndGet("Address", customer1.getAddress());
        String postCode = printfAndGet("Post Code", customer1.getPostCode());
        String city = printfAndGet("City", customer1.getCity());

        Customer updatedCustomer = new Customer(
                customer1.getUserId(), // keep the same ID
                userName,
                // customer1.getPassword(), // keep the same password
                password,
                firstName,
                lastName,
                address,
                postCode,
                city,
                email);

        getUserService().updateUser(updatedCustomer);
        println("Profile is updated successfully:");
        println("Enter any key to continue...");
        ConsoleReader.readLine();
        return MenuName.USER_MAIN_MENU;
    }

}
