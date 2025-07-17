package com.javalibproject.Menu.Admin;

import java.util.List;
import java.util.Optional;

import com.javalibproject.Menu.Generic.ConsoleReader;
import com.javalibproject.Menu.Generic.Menu;
import com.javalibproject.Menu.Generic.MenuName;
import com.javalibproject.Repo.user.Customer;
import com.javalibproject.Repo.user.SystemUser;
import com.javalibproject.Service.UserService;

public class SearchUsersMenu extends Menu {

    // public UserLoginMenu(String title, UserService userService) {
    //     super("User Login Succesfully ! ", userService);
    //     //TODO Auto-generated constructor stub
    // }
    public SearchUsersMenu(UserService userService){
        super("Search Users Menu", userService);
             
    }

    @Override
    public MenuName execute() {
        printTitle();
        String searchTerm = printAndGet("Enter username to search:");     
        List<Customer> customers = getUserService().searchUsers(searchTerm);
        if(customers.isEmpty()) {
            error("No users found with the username: " + searchTerm);
            return execute(); // main menu
        } else {
            System.out.printf("%-5s|%-20s|%-20s|%-20s|%-20s %n", "ID", "Username", "First Name", "Last Name", "Email");
            for (Customer customer : customers) {
                System.out.printf("%-5.5s|%-20.20s|%-20.20s|%-20.20s|%-20.20s %n", 
                    customer.getUserId(), customer.getUsername(), customer.getFirstName(), " ", customer.getLastName(), customer.getEmail());
            }
        }
        return execute();

       

    

    

}
}