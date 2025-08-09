package com.javalibproject.Repo.user;

import java.util.*;

/*
 * herhangi bir veritabani kullanmadigimiz icin bu sinifi liste olarak tasarlandi.
 * boylelikle hafizada tutulacak ve veritabanı islemleri yapilmayacaktir. 
 */
public class UserRepository {

    private final Map<Integer, SystemUser> users = new HashMap<>();

    public void createUser(SystemUser user) {
        Integer maxId = users.keySet().stream().max(Comparator.naturalOrder()).orElse(1); // max userId'yi bulur, eger yoksa 1 olarak baslar
        Integer newUserId = maxId + 1; // yeni userId'yi maxId + 1 olarak belirler

        //admin olusturalamaz diye attigimiz kirk takla
        SystemUser newUser = switch (user) {
            case AdminUser a -> new AdminUser(newUserId, a.getUsername(), a.getPassword());
            case Customer c -> new Customer(newUserId,
                    c.getUsername(),
                    c.getPassword(),
                    c.getFirstName(),
                    c.getLastName(),
                    c.getAddress(),
                    c.getPostCode(),
                    c.getCity(),
                    c.getEmail());
            default -> throw new RuntimeException("Only customer or admin can be created");
        };

        users.put(newUserId, newUser);

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


    public Optional<SystemUser> getById(Integer userId) {
        return Optional.ofNullable(users.get(userId));
    }

    public void updateUser(Customer updatedCustomer) {
        users.put(updatedCustomer.getUserId(), updatedCustomer);
    }

    public Collection<SystemUser> getAllUsers() {
       return users.values();
    }
}