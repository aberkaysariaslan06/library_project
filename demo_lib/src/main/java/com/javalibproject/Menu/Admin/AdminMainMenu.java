package com.javalibproject.Menu.Admin;

import java.util.Arrays;

import com.javalibproject.Menu.Generic.Menu;
import com.javalibproject.Menu.Generic.MenuName;
import com.javalibproject.Menu.Generic.MenuOptions;
import com.javalibproject.Menu.Login.AdminLoginMenu;
import com.javalibproject.Menu.Login.UserLoginMenu;

public class AdminMainMenu extends Menu {
      

      public AdminMainMenu() {
        super("------ADMIN MAIN MENU------");
        setMenu_options(Arrays.asList(
            new MenuOptions("U", "Search User",
            MenuName.ADMIN_SEARCH_USERS),
            new MenuOptions("C", "Create User",
            MenuName.ADMIN_SEARCH_USERS),
            new MenuOptions("B", "Search Book",
            MenuName.ADMIN_SEARCH_USERS),
            new MenuOptions("K", "Create Book",
            MenuName.ADMIN_SEARCH_USERS),
            new MenuOptions("O", "Log Off",
            MenuName.LOG_OFF) 
            ));
     }   
      
}
    
