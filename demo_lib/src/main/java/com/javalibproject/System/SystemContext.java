package com.javalibproject.System;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import com.javalibproject.Exceptions.UserLoginException;
import com.javalibproject.Repo.user.AdminUser;
import com.javalibproject.Repo.user.Customer;
import com.javalibproject.Repo.user.SystemUser;

public final class SystemContext {  //kimse bu classi extends etmemeli o yuzden final keywordu kullanildi

    //amacimiz sisteme kim giris yapti ? kullanici mi admin mi ? 
    
    
    private static Integer loggedInUserID;
    private static boolean isLoggedUserAdmin = false;
    public static final Map<String,String> properties = new HashMap<>();

    public static void addProperty(String name, String value) {
        properties.put(name, value);
    } 
    public static String getProperty(String name) {
        return properties.get(name);
    }

    public static boolean isLoggedUserAdmin(){
        return isLoggedUserAdmin; //return isLoggedUserAdmin==true; demektir.
    } 
    public static Integer getLoggedInUserID() { //kullanici id'si null olamaz, o yuzden bir hata firlatilabilir.
        return Optional.ofNullable(loggedInUserID)
            .orElseThrow(() -> new UserLoginException("No user is logged in"));   
          
    }
    public static void logInUser(SystemUser user) {
        switch (user) {
            case null -> {
                throw new UserLoginException("User cannot be null");
            }
            case Customer customer -> {
                loggedInUserID = customer.getUserId();
                isLoggedUserAdmin = false;  // customer is not admin
            }
            case AdminUser admin -> {
                loggedInUserID = admin.getUserId();   
                isLoggedUserAdmin = true;  // admin is logged in
        }
        default -> {
                throw new UserLoginException("Unknown user type" + user);
            }
        }
        
    }
    public static void logOutUser() {
        loggedInUserID = null; // logged out
        isLoggedUserAdmin = false; // reset admin status
    }
    
}
