package com.javalibproject.Menu.Admin.AdminMenus;

import com.javalibproject.Menu.Generic.Menu;
import com.javalibproject.Menu.Generic.MenuName;
import com.javalibproject.Repo.user.User.AdminUser;
import com.javalibproject.Service.UserService;
import com.javalibproject.System.SystemContext;

import java.util.Optional;

public class AdminUpdateOwnProfileMenu extends Menu {
    public AdminUpdateOwnProfileMenu(UserService userService) {
        super("------ADMİN UPDATE MENU------", userService);

    }

    @Override
    public MenuName execute() {
        printTitle();
        if (!SystemContext.isLoggedUserAdmin()) {
            error("Only the logged admin can update their profile.");
            return MenuName.ADMIN_MAIN_MENU;
        }
        Integer adminId = SystemContext.getLoggedInUserID();
        if (adminId == null) {
            error("No logged admin found.");
            return MenuName.ADMIN_MAIN_MENU;
        }
        Optional<AdminUser> optionalAdminUser = getAdminService().getById(adminId);
        if (optionalAdminUser.isEmpty()) {
            error("Admin not found.");
            return MenuName.ADMIN_MAIN_MENU;
        }
        AdminUser currentAdmin = optionalAdminUser.get();
        String newUsername = printfAndGet("Username", currentAdmin.getUsername());
        String newPassword = printfAndGet("Password", currentAdmin.getPassword());

        // Basit doğrulamalar
        if (newUsername == null || newUsername.trim().isEmpty()) {
            error("Username cannot be empty.");
            return MenuName.ADMIN_MAIN_MENU;
        }
        if (newPassword == null || newPassword.trim().isEmpty()) {
            error("Password cannot be empty.");
            return MenuName.ADMIN_MAIN_MENU;
        }

        // Sadece kendi profilini güncellesin: AdminService içi zaten kontrol edebilir
        AdminUser updated = new AdminUser(
                currentAdmin.getUserId(),
                newUsername.trim(),
                newPassword.trim());

        try {
            getAdminService().updateOwn(updated);
            println("Admin profile updated successfully.");
        } catch (RuntimeException ex) {
            error("Update failed: " + ex.getMessage());
        }

        return MenuName.ADMIN_MAIN_MENU;
    }

}
