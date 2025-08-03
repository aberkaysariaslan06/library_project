package com.javalibproject.Menu.Customer;

import com.javalibproject.Menu.Generic.Menu;
import com.javalibproject.Menu.Generic.MenuName;
import com.javalibproject.Menu.Generic.MenuOptions;

import java.util.Arrays;

public class UserMainMenu extends Menu {

    public UserMainMenu() {
        super("------USER MAIN MENU------");
        setMenu_options(Arrays.asList(
                new MenuOptions("P", "My Profile",
                        MenuName.USER_VIEW_PROFILE),
                new MenuOptions("B", "Borrow Book",
                        MenuName.USER_BORROW_BOOK),
                new MenuOptions("R", "Return Book",
                        MenuName.USER_RETURN_BOOK),
                new MenuOptions("O", "Log Off",
                        MenuName.LOG_OFF)
        ));
    }
}
