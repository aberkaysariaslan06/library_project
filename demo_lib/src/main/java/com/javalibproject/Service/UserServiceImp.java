package com.javalibproject.Service;

import java.util.List;
import java.util.Optional;

import com.javalibproject.Repo.user.AdminUser;
import com.javalibproject.Repo.user.Customer;
import com.javalibproject.Repo.user.SystemUser;
import com.javalibproject.Repo.user.UserRepository;
import com.javalibproject.System.SystemContext;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Deprecated
public class UserServiceImp implements UserService{
 
    private final UserRepository userRepository;
    private final MailService mailService;

    @Override
    public void createUser(SystemUser user) {
        if(SystemContext.isLoggedUserAdmin()) {
            if(user instanceof Customer c) {
                Customer createdCustomer = userRepository.createCustomer(c);
                mailService.sendUserCreatedMail(createdCustomer);
            }
            else if(user instanceof AdminUser) {
                throw new RuntimeException("Admin user cannot be created.");
            }
            else {
                throw new RuntimeException("Unknown user type: " );
            }

        }

    }

    @Override
    public void deleteUserByUserId(Integer userId) {
         if(SystemContext.isLoggedUserAdmin()) {
            userRepository.deleteCustomer(userId);
        } else {
            throw new RuntimeException("Only admin can delete users");
        }
        // TODO Auto-generated method stub
        
    }

    @Override
    public Optional<SystemUser> getByUsernameAndPassword(String username, String password) {
        return userRepository.getByUsernameAndPassword(username, password)
                .map(c -> (SystemUser) c);
    }

    @Override
    public List<Customer> searchUsers(String searchTerm) {
        if(SystemContext.isLoggedUserAdmin()) { 
            return userRepository.searchCustomers(searchTerm)
                .stream()
                .filter(user -> user instanceof Customer)
                .map(user -> (Customer) user)
                .toList();
        } else {
            throw new RuntimeException("Only admin can search users");
        }
      
    }

    @Override
    public Optional<Customer> getById(Integer userId) {
        return userRepository.getCustomerById(userId)

            .map(systemUser -> (Customer) systemUser);


    }

    @Override
    public void updateUser(Customer updatedCustomer) {
        userRepository.updateCustomer(updatedCustomer);
        mailService.sendUserUpdatedMail(updatedCustomer);

    }

    @Override
    public List<Customer> getAllUsers() {
        return userRepository.getAllCustomers()
            .stream()
            .filter(user -> user instanceof Customer)
            .map(user -> (Customer) user)
            .toList();
    }

}
