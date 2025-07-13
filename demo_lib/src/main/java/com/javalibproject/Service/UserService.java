package com.javalibproject.Service;

import java.util.Optional;

import com.javalibproject.Repo.user.SystemUser;

public interface UserService {

    void createUser(SystemUser user);
    void deleteUserByUserId(Integer userId);
    
   Optional<SystemUser> getByUsernameAndPassword(String username, String password);

} 