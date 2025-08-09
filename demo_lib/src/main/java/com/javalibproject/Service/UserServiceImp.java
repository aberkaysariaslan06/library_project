package com.javalibproject.Service;

import java.util.List;
import java.util.Optional;

import com.javalibproject.Repo.user.Customer;
import com.javalibproject.Repo.user.SystemUser;
import com.javalibproject.Repo.user.UserRepository;
import com.javalibproject.System.SystemContext;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UserServiceImp implements UserService{
 
    private final UserRepository userRepository;
    private final MailService mailService;

    @Override
    public void createUser(SystemUser user) {
        if(SystemContext.isLoggedUserAdmin()) {
            userRepository.createUser(user);
            mailService.sendUserCreatedMail(user);
        } else {
            throw new RuntimeException("Only admin can create users");
        }
        
        // UserRepository userRepository = new UserRepository(); 
        // userRepository.createUser(user);
        
    }

    @Override
    public void deleteUserByUserId(Integer userId) {
         if(SystemContext.isLoggedUserAdmin()) {
            userRepository.deleteUserByUserId(userId);
        } else {
            throw new RuntimeException("Only admin can delete users");
        }
        // TODO Auto-generated method stub
        
    }

    @Override
    public Optional<SystemUser> getByUsernameAndPassword(String username, String password) {
        return userRepository.getByUsernameAndPassword(username, password);
    }

    @Override
    public List<Customer> searchUsers(String searchTerm) {
        if(SystemContext.isLoggedUserAdmin()) { 
            return userRepository.searchUsers(searchTerm)
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
        return userRepository.getById(userId)

            .map(systemUser -> (Customer) systemUser);


    }

    @Override
    public void updateUser(Customer updatedCustomer) {
        userRepository.updateUser(updatedCustomer);
        mailService.sendUserUpdatedMail(updatedCustomer);

    }

    @Override
    public List<Customer> getAllUsers() {
        return userRepository.getAllUsers()
            .stream()
            .filter(user -> user instanceof Customer)
            .map(user -> (Customer) user)
            .toList();
    }

}
