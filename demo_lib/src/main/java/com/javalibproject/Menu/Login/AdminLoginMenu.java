package com.javalibproject.Menu.Login;

import java.util.Optional;

import com.javalibproject.Menu.Generic.ConsoleReader;
import com.javalibproject.Menu.Generic.Menu;
import com.javalibproject.Menu.Generic.MenuName;
import com.javalibproject.Repo.user.User.AdminUser;
import com.javalibproject.Repo.user.User.SystemUser;
import com.javalibproject.Service.AdminService;
import com.javalibproject.Service.UserService;
import com.javalibproject.System.SystemContext;

public class AdminLoginMenu extends Menu {

    // public UserLoginMenu(String title, UserService userService) {
    // super("User Login Succesfully ! ", userService);
    // //TODO Auto-generated constructor stub
    // }
    public AdminLoginMenu(AdminService adminService) {
        super("------ADMIN LOGIN MENU------", adminService);

    }

    @Override
    public MenuName execute() {
        printTitle();
        int attempts = 0;
        while (attempts < 3) {
            String username = printAndGet("User name:");
            String password = printAndGet("Password:");

            Optional<AdminUser> adminUser = getAdminService().login(username, password);
            if (adminUser.isPresent()) {
                if (adminUser.get() instanceof AdminUser) {
                    SystemContext.logInUser(adminUser.get());
                    println("Admin login successful: " + adminUser.get().getUsername());
                    return MenuName.ADMIN_MAIN_MENU; // sonraki menu
                } else {
                    error("Admin login failed: " + adminUser.get().getUsername() + " is not an admin user.");
                    return execute(); // main menu
                }

            } else {
                attempts++;
                error("Admin login failed: Invalid username or password. Please try again.");
                // println("Attempts remaining: " + (3 - attempts));
            }
        }
        error("Admin login failed: Too many attempts. Please try again later.");
        return null; // main menu
    }
}