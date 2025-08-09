package com.javalibproject.Menu.Generic;


import com.javalibproject.Exceptions.ViewUsersException;
import com.javalibproject.Repo.user.Customer;
import com.javalibproject.Repo.user.book.Book;
import com.javalibproject.Service.UserService;
import com.javalibproject.System.SystemContext;

import java.util.List;
import java.util.Optional;

public abstract class ShowUsers extends Menu{

    public ShowUsers(String userName, UserService userService) {
        super(userName, userService);
        //TODO Auto-generated constructor stub
    }

    protected List <Customer> fetchAllUsers(){
        return getUserService().getAllUsers();
    }

    protected void showAllUsers() {
        List<Customer> users = getUserService().getAllUsers();
        if(users.isEmpty()){
            error("No users found");
            return;
        }
        println("-------USER LIST-------");
        for (Customer u : users) {
            printfItem("User ID", u.getUserId().toString());
            printfItem("Username", u.getUsername());
            printfItem("First Name", u.getFirstName());
            printfItem("Last Name", u.getLastName());
            printfItem("Email", u.getEmail());
            printfItem("City", u.getCity());

        }
        println("--------------------------------");


    }



}
