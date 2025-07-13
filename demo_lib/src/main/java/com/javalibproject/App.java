package com.javalibproject;




import com.javalibproject.Menu.Login.MainLoginMenu;
import com.javalibproject.Menu.Generic.Menu;
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
      Menu mainMenu = new MainLoginMenu(userLoginMenu);
      Menu menu = mainMenu.execute();
      menu.execute();
        
    }

    private static void createDummyUsers(UserRepository userRepository) {

      userRepository.createUser(new Customer(1, "customer1", "password1", "customerUser", "customerUser", "address", "postCode", "city", "email"));
      //Integer userId, String username, String password, String firstName, String lastName, String address, String postCode, String city, String email
          
    }
}
