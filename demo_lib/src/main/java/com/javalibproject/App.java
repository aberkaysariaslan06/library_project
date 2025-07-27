package com.javalibproject;

import com.javalibproject.Menu.Admin.UserOperation.*;
import com.javalibproject.Menu.Generic.ConsoleReader;
import com.javalibproject.Menu.Login.AdminLoginMenu;
import com.javalibproject.Menu.Login.MainLoginMenu;
import com.javalibproject.Menu.Admin.AdminMainMenu;
import com.javalibproject.Menu.Generic.Menu;
import com.javalibproject.Menu.Generic.MenuName;
import com.javalibproject.Menu.Login.UserLoginMenu;
import com.javalibproject.Repo.user.AdminUser;
import com.javalibproject.Repo.user.Customer;
import com.javalibproject.Repo.user.UserRepository;
import com.javalibproject.Service.MailService;
import com.javalibproject.Service.MailServiceImp;
import com.javalibproject.Service.UserService;
import com.javalibproject.Service.UserServiceImp;


public class App 
{
    public static void main( String[] args ) 
    {
      
      UserRepository userRepository = new UserRepository();
      createDummyUsers(userRepository);
      MailService mailService = new MailServiceImp(userRepository);
      UserService userService = new UserServiceImp(userRepository, mailService);
      UserLoginMenu userLoginMenu = new UserLoginMenu(userService);
      AdminLoginMenu adminLoginMenu = new AdminLoginMenu(userService);
      AdminMainMenu adminMainMenu = new AdminMainMenu();
      SearchUsersMenu searchUsersMenu = new SearchUsersMenu(userService);
      ViewUsersMenu viewUsersMenu = new ViewUsersMenu(userService);
      EditUsersMenu editUsersMenu = new EditUsersMenu(userService);
      DeleteUsersMenu deleteUsersMenu = new DeleteUsersMenu(userService);
      CreateUsersMenu createUsersMenu = new CreateUsersMenu(userService);

      Menu mainLoginMenu = new MainLoginMenu(userLoginMenu);
      MenuName menuName = MenuName.MAIN_LOGIN;

      while(true){
        menuName = switch (menuName) {
            case USER_LOGIN -> userLoginMenu.execute();
            case ADMIN_LOGIN ->  adminLoginMenu.execute();
            case ADMIN_SEARCH_USERS -> searchUsersMenu.execute();
            case ADMIN_MAIN_MENU -> adminMainMenu.execute();
            case USER_MAIN_MENU -> userLoginMenu.execute();
            case ADMIN_VIEW_USERS -> viewUsersMenu.execute();
            case ADMIN_EDIT_USERS -> editUsersMenu.execute();
            case ADMIN_DELETE_USERS -> deleteUsersMenu.execute();
            case ADMIN_CREATE_USERS -> createUsersMenu.execute();
            case LOG_OFF -> mainLoginMenu.execute();
            default -> mainLoginMenu.execute(); //

      };
     
        //test - test2
    }
    }

    private static void createDummyUsers(UserRepository userRepository) {

      userRepository.createUser(new Customer(1, "customer1", "v", "customerUserTest1", "customerUserTest1", "address1", "postCode1", "city1", "email1@email.com"));
      userRepository.createUser(new Customer(2, "customer2", "v", "customerUserTest2", "customerUserTest2", "address2", "postCode2", "city2", "email2@email.com"));

      //Integer userId, String username, String password, String firstName, String lastName, String address, String postCode, String city, String email
      userRepository.createUser((new AdminUser(1001, "admin", "admin")));
    }
}
