package com.javalibproject.Menu.Admin.BookOperation;

import static com.javalibproject.Menu.Admin.BookOperation.ViewBookMenu.BOOK_ID;

import java.util.List;
import java.util.Optional;

import com.javalibproject.Menu.Generic.ConsoleReader;
import com.javalibproject.Menu.Generic.Menu;
import com.javalibproject.Menu.Generic.MenuName;
import com.javalibproject.Repo.user.Customer;
import com.javalibproject.Repo.user.SystemUser;
import com.javalibproject.Repo.user.book.Book;
import com.javalibproject.Service.BookService;
import com.javalibproject.Service.UserService;
import com.javalibproject.System.SystemContext;

public class SearchBookMenu extends Menu {
    
    public static final String BOOK_ID = "BOOK_ID"; // Define a constant for book ID

    public SearchBookMenu(BookService bookService){
        super("Search Book Menu", bookService);
             
    }

    @Override
    public MenuName execute() {
        // printTitle();
        String searchTerm = printAndGet("Enter book name to search:");
        List<Book> books = getBookService().searchBooks(searchTerm);
        if(books.isEmpty()) {
            error("No book found with the book name: " + searchTerm);
            // return execute(); // main menu
            // return MenuName.SEARCH_USERS;    
        } else {
            //System.out.printf("%-5s|%-20s|%-20s|%-20s|%-20s %n", "ID", "Title", "Year", "Author");
            System.out.printf("%-5s|%-20s|%-20s|%-20s %n", "ID", "Title", "Year", "Author");

            for (Book b : books) {
                //System.out.printf("%-5.5s|%-20.20s|%-20.20s|%-20.20s|%-20.20s %n",
                System.out.printf("%-5.5s|%-20.20s|%-20.20s|%-20.20s %n",
                    b.getBookId(), b.getBookName(), b.getYear(), b.getAuthor());
            }
            String choice = printAndGet("Enter book ID to see OR 'X' to go back to main menu:");
            if (choice.equalsIgnoreCase("X")) {
                return MenuName.ADMIN_MAIN_MENU; // go back to admin main menu

            } else {
                boolean idExist = books.stream()
                    .anyMatch(b -> b.getBookId().toString().equals(choice));
                if (idExist) {
                    SystemContext.addProperty(BOOK_ID, choice);
                    return MenuName.ADMIN_VIEW_BOOKS; // go to view book detail
                } else {
                    return execute();
                }
            }
        }
        return execute();
    }
}

