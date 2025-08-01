package com.javalibproject.Menu.Admin.BookOperation;

import com.javalibproject.Exceptions.ViewUsersException;
import com.javalibproject.Menu.Generic.ConsoleReader;
import com.javalibproject.Menu.Generic.Menu;
import com.javalibproject.Menu.Generic.MenuName;
import com.javalibproject.Menu.Generic.MenuOptions;
import com.javalibproject.Repo.user.Customer;
import com.javalibproject.Repo.user.book.Book;
import com.javalibproject.Service.BookService;
import com.javalibproject.Service.UserService;
import com.javalibproject.System.SystemContext;

import java.util.Arrays;
import java.util.Optional;

public class EditBookMenu extends Menu {

    public static final String BOOK_ID = "BOOK_ID"; // Define a constant for book ID
  

    public EditBookMenu(BookService bookService) {
        super("Edit Book Menu", bookService);

             
    }
    private String printfAndGet (String label, String value) { //edit book properties function
        System.out.printf("%-20s: %s --> New Value : ", label, value);
        String input = ConsoleReader.readLine();
        if(input == null || input.trim().equals("")) {
            return value; // if no input, keep the old value(bosluga bastim edit yapma demek icin)
        } else  {
            return input; // return the new value
        }
    }

    @Override
    public MenuName execute() {
        printTitle();
        String bookId = SystemContext.getProperty(BOOK_ID);
        if(bookId == null ||bookId.isEmpty()) {
            error("User ID is not set. Please search for a user first.");
            return MenuName.ADMIN_SEARCH_USERS; // go back to search users menu
        }
        try {
            Optional<Book> bookOptional = getBookService().getByBookId(Integer.valueOf(bookId));
            Book book1 = bookOptional.orElseThrow();

           //String id  = printfAndGet("User ID", customer1.getUserId().toString());
           String title = printfAndGet("Book Name", book1.title());
           Integer year = Integer.valueOf(printfAndGet("Year", book1.year().toString()));
           String author = printfAndGet("Author", book1.author());
           

           Book updatedBook = new Book(book1.id(), // keep the same ID
                title,year,author
            );

            getBookService().updateBook(updatedBook);
            println("Book updated successfully: " + updatedBook.title());
            SystemContext.removeProperty(BOOK_ID); // clear the book ID after update
            return MenuName.ADMIN_MAIN_MENU;
    } catch (ViewUsersException vue) {
            error("Invalid Book ID format: " + bookId);
            return MenuName.ADMIN_SEARCH_BOOKS; // go back to search users menu
        }

    }
}




