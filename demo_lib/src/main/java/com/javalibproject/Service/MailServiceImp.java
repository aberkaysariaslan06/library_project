package com.javalibproject.Service;

import com.javalibproject.Repo.user.Customer;
import com.javalibproject.Repo.user.SystemUser;
import com.javalibproject.Repo.user.UserRepository;
import com.javalibproject.System.SystemContext;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class MailServiceImp implements MailService{
 
    private final UserRepository userRepository;


    @Override
    public void sendUserUpdatedMail(SystemUser user) {
        System.out.println("Hello%s, your user information is updated. %n " + user.getUsername());


    }

    @Override
    public void sendUserCreatedMail(SystemUser user) {
        System.out.println("Welcome " + user.getUsername());

    }
}
