package com.javalibproject.Service;

import java.util.List;
import java.util.Optional;

import com.javalibproject.Repo.user.User.AdminUser;

public interface AdminService {
    Optional<AdminUser> getById(Integer adminId);

    Optional<AdminUser> login(String username, String password);

    List<AdminUser> getAllAdmins();

    void updateOwn(AdminUser updateAdmin);

}
