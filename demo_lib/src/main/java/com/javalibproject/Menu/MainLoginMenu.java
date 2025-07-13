package com.javalibproject.Menu;

import java.util.Arrays;
import java.util.List;

import com.javalibproject.Service.UserService;

public class MainLoginMenu extends Menu {

    private UserLoginMenu userLoginMenu;   

      public MainLoginMenu(UserLoginMenu userLoginMenu) {
        super("------LIBRARY LOGIN MENU------");
        this.userLoginMenu = userLoginMenu;
        setMenu_options(Arrays.asList(
            new MenuOptions("U", "User Login",          
            this::handleUserLogin),
            new MenuOptions("A", "Admin Login",
            this::handleAdminLogin),
            new MenuOptions("X", "Exit",
            this::handleExit)
            ));
    
        //TODO Auto-generated constructor stub
    }

        private Menu handleUserLogin() {
            println("User login handler started.");
            return userLoginMenu;
            
        }
        private Menu handleAdminLogin() {
            println("Admin login handler executed.");
            return this;
        }
        private Menu handleExit() {
            println("Exiting the application.");
            System.exit(0);
            return null;
        }   
}
