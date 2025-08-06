package com.javalibproject.Menu.Customer.Profile;

import com.javalibproject.Menu.Generic.Menu;
import com.javalibproject.Menu.Generic.MenuName;
import com.javalibproject.Menu.Generic.MenuOptions;

import java.util.Arrays;

public class ProfileMainMenu extends Menu {

    public ProfileMainMenu() {
        super("------MY PROFILE MENU------");
        setMenu_options(Arrays.asList(
                new MenuOptions("U", "Update my profile",
                        MenuName.USER_UPDATE_PROFILE),
                new MenuOptions("S", "View my profile",
                        MenuName.USER_VIEW_PROFILE),
                new MenuOptions("M", "Return to Main Menu",
                        MenuName.USER_MAIN_MENU)
        ));
    }
}
