package com.javalibproject.Service;

import java.util.List;
import java.util.Optional;

import com.javalibproject.Repo.user.Customer;
import com.javalibproject.Repo.user.SystemUser;

public interface UserService {

    void createUser(SystemUser user);
    void deleteUserByUserId(Integer userId);
    
   Optional<SystemUser> getByUsernameAndPassword(String username, String password);
   List<Customer> searchUsers(String searchTerm); //System User kullanmadik sonucta biz adminleri degil sadece customerlari search edebilecegiz.

    Optional<Customer> getById(Integer userId);

    //tek bir seyi almak istedigimizde ve null gibi bir durum ile ugrasmak istemedigimizde Optional kullanabiliriz.
} 