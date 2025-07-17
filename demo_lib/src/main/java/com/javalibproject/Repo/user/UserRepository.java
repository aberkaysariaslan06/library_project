package com.javalibproject.Repo.user;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
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
    public List<SystemUser> searchUsers(String searchTerm) {
        return users.values().stream()
            .filter(user -> user.getUsername().contains(searchTerm))
            .toList(); // Java 16 ile gelen toList() metodu, stream'den listeye donus yapar
    }
    private boolean searchUser(SystemUser user, String searchTerm){
        boolean found = findIn(searchTerm, user.getUserId().toString(), 
            user.getUsername());

            if(user instanceof Customer) {
                return found || findIn(searchTerm, 
                    ((Customer) user).getFirstName(), 
                    ((Customer) user).getLastName(), 
                    ((Customer) user).getAddress(), 
                    ((Customer) user).getPostCode(), 
                    ((Customer) user).getCity(), 
                    ((Customer) user).getEmail());
            } else return false;
        
    }
    private boolean findIn(String searchTerm, String... values) {
        return Arrays.stream(values)
        .anyMatch(s->s.contains(searchTerm));
        // .filter(s->s.contains(searchTerm))
        // .findAny().isPresent();
    }

    
}