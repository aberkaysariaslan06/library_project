package com.javalibproject.Menu.Admin.UserOperation;

import java.util.List;
import java.util.Optional;

import com.javalibproject.Menu.Generic.ConsoleReader;
import com.javalibproject.Menu.Generic.Menu;
import com.javalibproject.Menu.Generic.MenuName;
import com.javalibproject.Repo.user.Customer;
import com.javalibproject.Repo.user.SystemUser;
import com.javalibproject.Service.UserService;
import com.javalibproject.System.SystemContext;

public class ViewUsersMenu extends Menu {
    
    public static final String USER_ID = "USER_ID"; // Define a constant for user ID

    // public UserLoginMenu(String title, UserService userService) {
    //     super("User Login Succesfully ! ", userService);
    //     //TODO Auto-generated constructor stub
    // }
    public ViewUsersMenu(UserService userService){
        super("View Users Menu", userService);
             
    }

    @Override
    public MenuName execute() {
        printTitle();
        SystemContext.getProperty(USER_ID);
            
       
        String searchTerm = printAndGet("Enter username to search:");     
        List<Customer> customers = getUserService().searchUsers(searchTerm);
        if(customers.isEmpty()) {
            error("No users found with the username: " + searchTerm);
            // return execute(); // main menu
            // return MenuName.SEARCH_USERS;    
        } else {
            System.out.printf("%-5s|%-20s|%-20s|%-20s|%-20s %n", "ID", "Username", "First Name", "Last Name", "Email");
            for (Customer customer : customers) {
                System.out.printf("%-5.5s|%-20.20s|%-20.20s|%-20.20s|%-20.20s %n", 
                    customer.getUserId(), customer.getUsername(), customer.getFirstName(), customer.getLastName(), customer.getEmail());
            }
            String choice = printAndGet("Enter user ID to see OR 'X' to go back to main menu:");
            if (choice.equalsIgnoreCase("X")) {
                return MenuName.ADMIN_MAIN_MENU; // go back to admin main menu

            } else {
                boolean idExist = customers.stream()
                    .anyMatch(customer -> customer.getUserId().toString().equals(choice));
                if (idExist) {
                    return MenuName.ADMIN_VIEW_USERS; // go to view user detail
                } else {
                    return execute();
                }
            }
        }
        return execute();
    }
}

