package com.javalibproject.Repo.user;

import java.util.HashMap;
import java.util.Map;

/*
 * herhangi bir veritabani kullanmadigimiz icin bu sinifi liste olarak tasarlandi.
 * boylelikle hafizada tutulacak ve veritabanı islemleri yapilmayacaktir.
 */
public class UserRepository {

    private final Map<Integer, User> users = new HashMap<>();

    public void addUser(User user) {
        if (user != null && !users.containsKey(user.getUserId())) {
            users.put(user.getUserId(), user);
        } else {
            throw new IllegalArgumentException("User already exists or is null");
        }
    }
    public void deleteUser(Integer userId) {
        if (userId != null && users.containsKey(userId)) {
            users.remove(userId);
        } else {
            throw new IllegalArgumentException("User does not exist or is null");
        }
    }

    
}