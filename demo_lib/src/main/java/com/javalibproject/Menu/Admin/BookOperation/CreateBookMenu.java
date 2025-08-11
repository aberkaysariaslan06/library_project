package com.javalibproject.Menu.Admin.BookOperation;

import com.javalibproject.Exceptions.ViewUsersException;
import com.javalibproject.Menu.Generic.ConsoleReader;
import com.javalibproject.Menu.Generic.Menu;
import com.javalibproject.Menu.Generic.MenuName;
import com.javalibproject.Repo.user.User.Customer;
import com.javalibproject.Repo.user.book.Book;
import com.javalibproject.Service.BookService;
import com.javalibproject.Service.UserService;
import com.javalibproject.System.SystemContext;

import java.util.Optional;

public class CreateBookMenu extends Menu {

    public static final String BOOK_ID = "BOOK_ID"; // Define a constant for book ID

    public CreateBookMenu(BookService bookService) {
        super("------CREATE BOOK MENU------", bookService);

    }

    @Override
    public MenuName execute() {
        printTitle();

        String title = printAndGet("Book Name : ");
        Integer year = Integer.valueOf(printAndGet("Year : "));
        String author = printAndGet("Author : ");

        Book newBook = new Book(null, title, year, author);
        getBookService().createBook(newBook);
        System.out.println();
        println("Book created successfully: " + newBook.getBookName());
        println("Press enter to continue ");

        ConsoleReader.readLine();

        return MenuName.ADMIN_MAIN_MENU;

    }
}
