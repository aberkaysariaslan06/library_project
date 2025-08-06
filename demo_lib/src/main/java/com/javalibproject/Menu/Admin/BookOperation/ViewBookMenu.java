package com.javalibproject.Menu.Admin.BookOperation;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.javalibproject.Exceptions.ViewUsersException;
import com.javalibproject.Menu.Generic.ConsoleReader;
import com.javalibproject.Menu.Generic.Menu;
import com.javalibproject.Menu.Generic.MenuName;
import com.javalibproject.Menu.Generic.MenuOptions;
import com.javalibproject.Repo.user.Customer;
import com.javalibproject.Repo.user.SystemUser;
import com.javalibproject.Repo.user.book.Book;
import com.javalibproject.Service.BookService;
import com.javalibproject.Service.UserService;
import com.javalibproject.System.SystemContext;

import javax.swing.text.View;

public class ViewBookMenu extends Menu {

    public static final String BOOK_ID = "BOOK_ID"; // Define a constant for book ID

    public ViewBookMenu(BookService bookService) {
        super("------VİEW BOOK MENU------", bookService);

        setMenu_options(Arrays.asList(
                new MenuOptions("E", "Edit Book",
                        MenuName.ADMIN_EDIT_BOOKS),
                new MenuOptions("D", "Delete Book",
                        MenuName.ADMIN_DELETE_BOOKS),
                new MenuOptions("M", "Back to Main Menu",
                       MenuName.ADMIN_MAIN_MENU)
        ));


             
    }


    @Override
    public MenuName execute() {
        printTitle();
        String bookId= SystemContext.getProperty(BOOK_ID);
        if(bookId == null ||bookId.isEmpty()) {
            error("Book ID is not set. Please search for a book first.");
            return MenuName.ADMIN_SEARCH_BOOKS; // go back to search users menu
        }
        try {
            Optional<Book> bookOptional = getBookService().getByBookId(Integer.valueOf(bookId));
            Book book1 = bookOptional.orElseThrow();

            printfItem("Book ID", book1.getBookId().toString());
            printfItem("Title", book1.getBookName());
            printfItem("Year", book1.getYear().toString());
            printfItem("Author", book1.getAuthor());
            println("--------------------------------");

            
        } catch (ViewUsersException vue) {
            error("Invalid Book ID format: " + bookId);
            return MenuName.ADMIN_SEARCH_BOOKS; // go back to search books menu
        }


       
        printOptions();
        return run();


    }
}




