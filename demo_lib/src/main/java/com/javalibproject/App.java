package com.javalibproject;

import com.javalibproject.Menu.Login.MainLoginMenu;
import com.javalibproject.Menu.Generic.Menu;
import com.javalibproject.Menu.Generic.MenuName;
import com.javalibproject.Menu.Login.UserLoginMenu;
import com.javalibproject.Repo.user.Customer;
import com.javalibproject.Repo.user.UserRepository;
import com.javalibproject.Service.UserService;
import com.javalibproject.Service.UserServiceImp;


public class App 
{
    public static void main( String[] args ) 
    {
      
      UserRepository userRepository = new UserRepository();
      createDummyUsers(userRepository);
      UserService userService = new UserServiceImp(userRepository);
      UserLoginMenu userLoginMenu = new UserLoginMenu(userService);
      Menu mainLoginMenu = new MainLoginMenu(userLoginMenu);
      MenuName menuName = MenuName.MAIN_LOGIN;

      while(true){
        menuName = switch (menuName) {
            case USER_LOGIN -> userLoginMenu.execute();
            default -> mainLoginMenu.execute();

      };
     
        
    }
    }

    private static void createDummyUsers(UserRepository userRepository) {

      userRepository.createUser(new Customer(1, "customer1", "v", "customerUser", "customerUser", "address", "postCode", "city", "email"));
      //Integer userId, String username, String password, String firstName, String lastName, String address, String postCode, String city, String email
          
    }
}
