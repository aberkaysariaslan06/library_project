package com.javalibproject.Service;

import java.util.List;
import java.util.Optional;

import com.javalibproject.Repo.user.User.Customer;

public interface CustomerService {
    Customer create(Customer customer);

    void deleteById(Integer customerId);

    Optional<Customer> getById(Integer customerId);

    Optional<Customer> getByUsernameAndPassword(String username, String password);

    List<Customer> search(String query);

    void update(Customer updatedCustomer);

    List<Customer> getAllCustomers();

}
