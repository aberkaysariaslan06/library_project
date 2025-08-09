package com.javalibproject.Service;

import com.javalibproject.Repo.user.AdminUser;


import java.util.List;
import java.util.Optional;

public interface AdminService {
    Optional<AdminUser> getById(Integer adminId);
    Optional<AdminUser> login(String username, String password);
    List<AdminUser> getAllAdmins();
    void updateOwn(AdminUser updateAdmin);


}
