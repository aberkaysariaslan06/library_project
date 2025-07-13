package com.javalibproject.Repo.user;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/*
 * herhangi bir veritabani kullanmadigimiz icin bu sinifi liste olarak tasarlandi.
 * boylelikle hafizada tutulacak ve veritabanı islemleri yapilmayacaktir.
 */
public class UserRepository {

    private final Map<Integer, SystemUser> users = new HashMap<>();

    public void createUser(SystemUser customer) {
        if (customer != null && !users.containsKey(customer.getUserId())) {
            users.put(customer.getUserId(), customer);
        } else {
            throw new IllegalArgumentException("User already exists or is null");
        }
    }
    public void deleteUserByUserId(Integer userId) {
        if (userId != null && users.containsKey(userId)) {
            users.remove(userId);
        } else {
            throw new IllegalArgumentException("User does not exist or is null");
        }
    }
    public Optional<SystemUser> getByUsernameAndPassword(String username, String password) {
        return users.values().stream()
            .filter(user -> user.getUsername().equals(username) && user.getPassword().equals(password))
            .findFirst();//ilk buldugunu gonder
    }

    
}