package com.javalibproject.Service;

import com.javalibproject.Repo.user.AdminUser;
import com.javalibproject.Repo.user.UserRepository;
import com.javalibproject.System.SystemContext;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class AdminServiceImp implements AdminService{
    private final UserRepository userRepository;
    @Override
    public Optional<AdminUser> getById(Integer adminId) {
        if(!SystemContext.isLoggedUserAdmin()) {
            throw new RuntimeException("Only admin can view admins.");
        }
        return userRepository.getAdminById(adminId);
    }

    @Override
    public Optional<AdminUser> login(String username, String password) {
        return userRepository.getAdminByUsernameAndPassword(username, password);
    }

    @Override
    public List<AdminUser> getAllAdmins() {
        if(!SystemContext.isLoggedUserAdmin()) {
            throw new RuntimeException("Only admin can view admins.");
        }
        return userRepository.getAllAdmins();

    }

    @Override
    public void updateOwn(AdminUser updateAdmin) {
        if(!SystemContext.isLoggedUserAdmin()) {
            throw new RuntimeException("Only admin can view admins.");
        }
        userRepository.updateAdmin(updateAdmin);

    }

    }




