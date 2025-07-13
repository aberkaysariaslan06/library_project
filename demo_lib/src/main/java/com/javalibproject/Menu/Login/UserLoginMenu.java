package com.javalibproject.Menu.Login;

import java.util.Optional;

import com.javalibproject.Menu.Generic.ConsoleReader;
import com.javalibproject.Menu.Generic.Menu;
import com.javalibproject.Repo.user.SystemUser;
import com.javalibproject.Service.UserService;

public class UserLoginMenu extends Menu {

    // public UserLoginMenu(String title, UserService userService) {
    //     super("User Login Succesfully ! ", userService);
    //     //TODO Auto-generated constructor stub
    // }
    public UserLoginMenu(UserService userService){
        super("User Login Menu", userService);
             
    }

    @Override
    public Menu execute() {
        printTitle();
        int attempts = 0;
        while(attempts<3) {
            print("User name:");
            String username = ConsoleReader.readLine();
            print("Password:");
            String password = ConsoleReader.readLine();
            Optional<SystemUser> user = getUserService().getByUsernameAndPassword(username, password);
            if (user.isPresent()) {
                println("User login successful: " + user.get().getUsername());
                return null; //sonraki menu
            } else {
                attempts++;
                error("User login failed: Invalid username or password. Please try again.");
//                println("Attempts remaining: " + (3 - attempts));
            }
        }
        error("User login failed: Too many attempts. Please try again later.");
        return null; // main menu


    

    

}
}