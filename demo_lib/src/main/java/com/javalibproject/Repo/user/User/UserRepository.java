package com.javalibproject.Repo.user.User;

import java.util.*;
import java.util.stream.Stream;

/*
 * herhangi bir veritabani kullanmadigimiz icin bu sinifi liste olarak tasarlandi.
 * boylelikle hafizada tutulacak ve veritabanı islemleri yapilmayacaktir.
 */
public class UserRepository {

    private final Map<Integer, SystemUser> users = new HashMap<>();
    private final Map<Integer, AdminUser> admins = new HashMap<>();
    private final Map<Integer, Customer> customers = new HashMap<>();
    private int lastId = 0; // tum kullanicilar icin ayni id dizisi paylasilsin.

    private int nextId() { // yeni bir kullanıcı, admin veya müşteri oluşturulacağı zaman çakışmayan,
                           // benzersiz bir ID atamak için kullanılır.
        if (lastId == 0) {
            int maxAdmin = admins.keySet().stream().max(Comparator.naturalOrder()).orElse(0);
            int maxCustomer = (customers.keySet().stream().max(Comparator.naturalOrder()).orElse(0));
            lastId = Math.max(maxAdmin, maxCustomer);
        }
        return ++lastId;
    }

    public Customer createCustomer(Customer raw) { // just create customer not user
        int id = nextId();
        Customer customer = new Customer(id,
                raw.getUsername(),
                raw.getPassword(),
                raw.getFirstName(),
                raw.getLastName(),
                raw.getAddress(),
                raw.getPostCode(),
                raw.getCity(),
                raw.getEmail());
        customers.put(id, customer);
        return customer;
    }

    /*
     * public void createUser(SystemUser user) {
     * Integer maxId =
     * users.keySet().stream().max(Comparator.naturalOrder()).orElse(1); // max
     * userId'yi bulur, eger yoksa 1 olarak baslar
     * Integer newUserId = maxId + 1; // yeni userId'yi maxId + 1 olarak belirler
     * 
     * //admin olusturalamaz diye attigimiz kirk takla
     * SystemUser newUser = switch (user) {
     * case AdminUser a -> new AdminUser(newUserId, a.getUsername(),
     * a.getPassword());
     * case Customer c -> new Customer(newUserId,
     * c.getUsername(),
     * c.getPassword(),
     * c.getFirstName(),
     * c.getLastName(),
     * c.getAddress(),
     * c.getPostCode(),
     * c.getCity(),
     * c.getEmail());
     * default -> throw new
     * RuntimeException("Only customer or admin can be created");
     * };
     * 
     * users.put(newUserId, newUser);
     * 
     * }
     */

    public Optional<Customer> getCustomerById(Integer userId) {
        if (userId == null)
            return Optional.empty();
        return Optional.ofNullable(customers.get(userId));
    }

    public void updateCustomer(Customer updatedCustomer) {
        if (updatedCustomer == null || updatedCustomer.getUserId() == null) {
            throw new IllegalArgumentException("Updated customer or userId cannot be null");
        }
        customers.put(updatedCustomer.getUserId(), updatedCustomer);
    }

    public void deleteCustomer(Integer userId) {
        if (userId == null || !customers.containsKey(userId)) {
            throw new IllegalArgumentException("User does not exist or is null");
        }
        customers.remove(userId);

    }

    public List<Customer> getAllCustomers() {
        return customers.values().stream()
                .sorted(Comparator.comparing(Customer::getUserId))
                .toList();
    }

    public List<Customer> searchCustomers(String query) {
        if (query == null)
            return List.of();
        String q = query.trim().toLowerCase(Locale.ROOT);
        if (q.isEmpty())
            return List.of();

        String[] terms = q.split("\\s+");
        return getAllCustomers().stream()
                .filter(c -> matchesCustomer(c, terms))
                .toList();
    }

    public boolean matchesCustomer(Customer c, String[] terms) {
        String idStr = String.valueOf(c.getUserId()).toLowerCase(Locale.ROOT);
        String username = safe(c.getUsername());
        String firstName = safe(c.getFirstName());
        String lastName = safe(c.getLastName());
        String email = safe(c.getEmail());
        String address = safe(c.getAddress());
        String postCode = safe(c.getPostCode());
        String city = safe(c.getCity());

        for (String t : terms) {
            boolean hit = idStr.contains(t) || username.contains(t) || firstName.contains(t) || lastName.contains(t)
                    || email.contains(t) ||
                    address.contains(t) || postCode.contains(t) || city.contains(t);
            if (!hit)
                return false;
        }
        return true;

    }

    private String safe(String str) { // bu metot null olan stringleri kontrol eder ve null ise boş string döner,
                                      // yoksa lowercase çevirir.
        return str == null ? "" : str.toLowerCase(Locale.ROOT);
    }

    public Optional<Customer> getByUsernameAndPassword(String username, String password) {
        if (username == null || password == null) {
            return Optional.empty();
        }

        return customers.values().stream()
                .filter(c -> username.equals(c.getUsername()) && password.equals(c.getPassword()))
                .findFirst();
    }

    public Optional<AdminUser> getAdminById(Integer adminId) {
        if (adminId == null)
            return Optional.empty();
        return Optional.ofNullable(admins.get(adminId));
    }

    public Optional<AdminUser> getAdminByUsernameAndPassword(String username, String password) {
        if (username == null || password == null) {
            return Optional.empty();
        }
        return admins.values().stream()
                .filter(a -> username.equals(a.getUsername()) && password.equals(a.getPassword()))
                .findFirst();

    }

    public List<AdminUser> getAllAdmins() {
        return admins.values().stream()
                .sorted(Comparator.comparing(AdminUser::getUserId))
                .toList();
    }

    public void updateAdmin(AdminUser updatedAdmin) {
        if (updatedAdmin == null || updatedAdmin.getUserId() == null) {
            throw new IllegalArgumentException("Updated admin or userId cannot be null");
        }
        admins.put(updatedAdmin.getUserId(), updatedAdmin);
    }

    public void seedAdminIfEmpty(String adminUsername, String adminPassword) {
        if (admins.isEmpty()) {
            AdminUser a = new AdminUser(1, adminUsername, adminPassword);
            admins.put(1, a);
            lastId = Math.max(lastId, 1);
        }
    }

    /*
     * private boolean searchUser(SystemUser user, String searchTerm){
     * boolean found = findIn(searchTerm, user.getUserId().toString(),
     * user.getUsername());
     * 
     * if(user instanceof Customer) {
     * return found || findIn(searchTerm,
     * ((Customer) user).getFirstName(),
     * ((Customer) user).getLastName(),
     * ((Customer) user).getAddress(),
     * ((Customer) user).getPostCode(),
     * ((Customer) user).getCity(),
     * ((Customer) user).getEmail());
     * } else return false;
     * 
     * }
     * private boolean findIn(String searchTerm, String... values) {
     * return Arrays.stream(values)
     * .anyMatch(s->s.contains(searchTerm));
     * // .filter(s->s.contains(searchTerm))
     * // .findAny().isPresent();
     * }
     * 
     */

}