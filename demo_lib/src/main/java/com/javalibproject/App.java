package com.javalibproject;

import com.javalibproject.Menu.Admin.UserOperation.*;
import com.javalibproject.Menu.Customer.BorrowBooks.BorrowBookMenu;
import com.javalibproject.Menu.Customer.Profile.ProfileMainMenu;
import com.javalibproject.Menu.Customer.Profile.UpdateMyProfileMenu;
import com.javalibproject.Menu.Customer.Profile.ViewMyProfile;
import com.javalibproject.Menu.Customer.ReturnBooks.ReturnBookMenu;
import com.javalibproject.Menu.Customer.UserMainMenu;
import com.javalibproject.Menu.Generic.ConsoleReader;
import com.javalibproject.Menu.Login.AdminLoginMenu;
import com.javalibproject.Menu.Login.MainLoginMenu;
import com.javalibproject.Menu.Admin.AdminMenus.AdminMainMenu;
import com.javalibproject.Menu.Admin.BookOperation.CreateBookMenu;
import com.javalibproject.Menu.Admin.BookOperation.DeleteBookMenu;
import com.javalibproject.Menu.Admin.BookOperation.EditBookMenu;
import com.javalibproject.Menu.Admin.BookOperation.SearchBookMenu;
import com.javalibproject.Menu.Admin.BookOperation.ViewAllBooksMenu;
import com.javalibproject.Menu.Admin.BookOperation.ViewBookMenu;
import com.javalibproject.Menu.Generic.Menu;
import com.javalibproject.Menu.Generic.MenuName;
import com.javalibproject.Menu.Login.UserLoginMenu;
import com.javalibproject.Repo.user.User.AdminUser;
import com.javalibproject.Repo.user.User.Customer;
import com.javalibproject.Repo.user.User.UserRepository;
import com.javalibproject.Repo.user.book.Book;
import com.javalibproject.Repo.user.book.BookRepository;
import com.javalibproject.Service.*;

public class App {
  public static void main(String[] args) {

    UserRepository userRepository = new UserRepository();
    BookRepository bookRepository = new BookRepository();
    createDummyUsers(userRepository);
    createDummyBooks(bookRepository);
    MailService mailService = new MailServiceImp(userRepository);
    UserService userService = new UserServiceImp(userRepository, mailService);
    CustomerService customerService = new CustomerServiceImp(userRepository, mailService);
    AdminService adminService = new AdminServiceImp(userRepository);
    BookService bookService = new BookServiceImp(bookRepository);
    // menu operation
    UserLoginMenu userLoginMenu = new UserLoginMenu(userService);
    AdminLoginMenu adminLoginMenu = new AdminLoginMenu(adminService);
    AdminMainMenu adminMainMenu = new AdminMainMenu();
    // admin user operation
    CreateUsersMenu createUsersMenu = new CreateUsersMenu(customerService);
    EditUsersMenu editUsersMenu = new EditUsersMenu(customerService);
    ViewUsersMenu viewUsersMenu = new ViewUsersMenu(customerService);
    DeleteUsersMenu deleteUsersMenu = new DeleteUsersMenu(customerService);
    SearchUsersMenu searchUsersMenu = new SearchUsersMenu(customerService);
    ViewAllUsersMenu viewAllUsersMenu = new ViewAllUsersMenu(customerService);

    // admin book operation
    SearchBookMenu searchBookMenu = new SearchBookMenu(bookService);
    ViewBookMenu viewBookMenu = new ViewBookMenu(bookService);
    EditBookMenu editBookMenu = new EditBookMenu(bookService);
    DeleteBookMenu deleteBookMenu = new DeleteBookMenu(bookService);
    CreateBookMenu createBookMenu = new CreateBookMenu(bookService);
    ViewAllBooksMenu viewAllBooksMenu = new ViewAllBooksMenu(bookService);
    // user operation
    UpdateMyProfileMenu updateMyProfileMenu = new UpdateMyProfileMenu(userService);
    ViewMyProfile viewMyProfile = new ViewMyProfile(userService);
    UserMainMenu userMainMenu = new UserMainMenu();
    // user book operation
    BorrowBookMenu borrowBookMenu = new BorrowBookMenu(bookService);
    ReturnBookMenu returnBookMenu = new ReturnBookMenu(bookService);

    Menu mainLoginMenu = new MainLoginMenu(userLoginMenu);
    MenuName menuName = MenuName.MAIN_LOGIN;

    while (true) {
      menuName = switch (menuName) {
        case USER_LOGIN -> userLoginMenu.execute();

        case ADMIN_LOGIN -> adminLoginMenu.execute();
        case ADMIN_SEARCH_USERS -> searchUsersMenu.execute();
        case ADMIN_MAIN_MENU -> adminMainMenu.execute();

        // admin user operation
        case ADMIN_VIEW_USERS -> viewUsersMenu.execute();
        case ADMIN_EDIT_USERS -> editUsersMenu.execute();
        case ADMIN_DELETE_USERS -> deleteUsersMenu.execute();
        case ADMIN_CREATE_USERS -> createUsersMenu.execute();
        case ADMIN_VIEWS_ALL_USERS -> viewAllUsersMenu.execute();
        // admin book operation
        case ADMIN_CREATE_BOOKS -> createBookMenu.execute();
        case ADMIN_EDIT_BOOKS -> editBookMenu.execute();
        case ADMIN_DELETE_BOOKS -> deleteBookMenu.execute();
        case ADMIN_SEARCH_BOOKS -> searchBookMenu.execute();
        case ADMIN_VIEW_BOOKS -> viewBookMenu.execute();
        case ADMIN_VIEWS_ALL_BOOKS -> viewAllBooksMenu.execute();
        // user profile operation
        case USER_UPDATE_PROFILE -> updateMyProfileMenu.execute();
        case USER_VIEW_PROFILE -> viewMyProfile.execute();
        case USER_MY_PROFILE -> updateMyProfileMenu.execute();
        case USER_MAIN_MENU -> userMainMenu.execute();
        // user book operation
        case USER_BORROW_BOOK -> borrowBookMenu.execute();
        case USER_RETURN_BOOK -> returnBookMenu.execute();

        case LOG_OFF -> mainLoginMenu.execute();
        default -> mainLoginMenu.execute(); //

      };

      // test - test2
    }
  }

  private static void createDummyUsers(UserRepository userRepository) {

    userRepository.seedAdminIfEmpty("admin", "admin");

    userRepository.createCustomer(new Customer(1, "customer1", "v", "customerUserTest1", "customerUserTest1",
        "address1", "postCode1", "city1", "email1@email.com"));
    userRepository.createCustomer(new Customer(2, "customer2", "v", "customerUserTest2", "customerUserTest2",
        "address2", "postCode2", "city2", "email2@email.com"));

    // Integer userId, String username, String password, String firstName, String
    // lastName, String address, String postCode, String city, String email
    // userRepository.createUser((new AdminUser(1001, "admin", "admin")));

  }

  private static void createDummyBooks(BookRepository bookRepository) {

    bookRepository.createBook(new Book(1, "Ornek Kitap", 2010, "Author 1"));
    bookRepository.createBook(new Book(2, "Example Book", 2020, "Author 2"));
    bookRepository.createBook(new Book(3, "book3", 2023, "Author 3"));
    bookRepository.createBook(new Book(4, "book4", 2024, "Author 4"));

  }

}
