package com.javalibproject.Repo.user;




public class AdminUser extends SystemUser {
  
    private final Integer userId;
    private final String username;    
    private final String password;

     public AdminUser(Integer userId, String username, String password) {
        super(userId, username, password);
        this.userId = userId;
        this.username = username;
        this.password = password;
        //TODO Auto-generated constructor stub
    }   
    
}
