package com.javalibproject.Service;

import com.javalibproject.Repo.user.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerService {
    Customer create(Customer customer);
    void deleteById(Integer customerId);
    Optional<Customer> getById(Integer customerId);
    Optional<Customer> getByUsernameAndPassword(String username, String password);
    List<Customer> search(String query);
    void update(Customer updatedCustomer);
    List<Customer> getAllCustomers();

}
