package com.javalibproject.Menu.Admin.BookOperation;

import com.javalibproject.Exceptions.ViewUsersException;
import com.javalibproject.Menu.Generic.ConsoleReader;
import com.javalibproject.Menu.Generic.Menu;
import com.javalibproject.Menu.Generic.MenuName;
import com.javalibproject.Repo.user.Customer;
import com.javalibproject.Repo.user.book.Book;
import com.javalibproject.Service.BookService;
import com.javalibproject.Service.UserService;
import com.javalibproject.System.SystemContext;

import java.util.Optional;

public class DeleteBookMenu extends Menu {

    public static final String BOOK_ID = "BOOK_ID"; // Define a constant for book ID
 

    public DeleteBookMenu(BookService bookService) {
        super("Delete Book Menu", bookService);

             
    }
    

    @Override
    public MenuName execute() {
        printTitle();
        String bookId = SystemContext.getProperty(BOOK_ID);
        if(bookId == null ||bookId.isEmpty()) {
            error("Book ID is not set. Please search for a book first.");
            return MenuName.ADMIN_SEARCH_BOOKS; // go back to search users menu
        }
        try {
            Optional<Book> bookOptional = getBookService().getByBookId(Integer.valueOf(bookId));
            Book book1 = bookOptional.orElseThrow();



            getBookService().deleteBookByBookId(book1.id());
            println("Book deleted successfully" + book1.id() + " - " + book1.title());
            SystemContext.removeProperty(BOOK_ID); // clear the user ID after deletion
            return MenuName.ADMIN_MAIN_MENU;
    } catch (ViewUsersException vue) {
            error("Invalid Book ID format: " + bookId);
            return MenuName.ADMIN_MAIN_MENU; // go back to search book menu
        }


    }
}




