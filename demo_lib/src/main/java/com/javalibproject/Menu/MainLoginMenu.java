package com.javalibproject.Menu;

import java.util.Arrays;
import java.util.List;

public class MainLoginMenu extends Menu {

      public MainLoginMenu() {
        super("Login Menu");
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

        private void handleUserLogin() {
            System.out.println("User login handler executed.");
        }
        private void handleAdminLogin() {
            System.out.println("Admin login handler executed.");
        }
        private void handleExit() {
            System.out.println("Exiting the application.");
            System.exit(0);
        }   
}
