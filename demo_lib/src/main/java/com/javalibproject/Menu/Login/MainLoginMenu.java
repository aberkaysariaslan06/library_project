package com.javalibproject.Menu.Login;

import java.util.Arrays;

import com.javalibproject.Menu.Generic.Menu;
import com.javalibproject.Menu.Generic.MenuName;
import com.javalibproject.Menu.Generic.MenuOptions;

public class MainLoginMenu extends Menu {

      public MainLoginMenu(UserLoginMenu userLoginMenu) {
        super("------LIBRARY LOGIN MENU------");
        
        setMenu_options(Arrays.asList(
            new MenuOptions("U", "User Login",
            MenuName.USER_LOGIN),
            new MenuOptions("A", "Admin Login",
            MenuName.ADMIN_LOGIN),
            new MenuOptions("X", "Exit",
            this::exit)
            ));
            }  
    
       
        private MenuName exit() {
            println("Exiting the application.");
            System.exit(1);
            return null;
        }   
}
