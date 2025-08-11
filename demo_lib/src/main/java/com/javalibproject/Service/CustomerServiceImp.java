package com.javalibproject.Service;

import com.javalibproject.Repo.user.User.Customer;
import com.javalibproject.Repo.user.User.UserRepository;
import com.javalibproject.System.SystemContext;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class CustomerServiceImp implements CustomerService {

    private final UserRepository userRepository;
    private final MailService mailService;

    @Override
    public Customer create(Customer customer) {
        if (SystemContext.isLoggedUserAdmin()) {
            Customer c = userRepository.createCustomer(customer);
            mailService.sendUserCreatedMail(c);
            return c;
        } else {
            throw new RuntimeException("Only admin can create users");
        }

    }

    @Override
    public void deleteById(Integer customerId) {
        if (SystemContext.isLoggedUserAdmin()) {
            userRepository.deleteCustomer(customerId);
        } else {
            throw new RuntimeException("Only admin can delete users");
        }
        // TODO Auto-generated method stub

    }

    @Override
    public Optional<Customer> getById(Integer customerId) {
        return userRepository.getCustomerById(customerId);

    }

    @Override
    public Optional<Customer> getByUsernameAndPassword(String username, String password) {
        return userRepository.getByUsernameAndPassword(username, password);

    }

    @Override
    public List<Customer> search(String query) {
        if (SystemContext.isLoggedUserAdmin()) {
            return userRepository.searchCustomers(query);

        } else {
            throw new RuntimeException("Only admin can search users");
        }

    }

    @Override
    public void update(Customer updatedCustomer) {
        if (SystemContext.isLoggedUserAdmin()) {
            userRepository.updateCustomer(updatedCustomer);
            mailService.sendUserUpdatedMail(updatedCustomer);

        } else {
            throw new RuntimeException("Only admin can search users");
        }

    }

    @Override
    public List<Customer> getAllCustomers() {
        if (SystemContext.isLoggedUserAdmin()) {
            return userRepository.getAllCustomers();

        } else {
            throw new RuntimeException("Only admin can search users");
        }

    }
}
