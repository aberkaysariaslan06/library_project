package com.javalibproject.Menu.Admin.BookOperation;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.javalibproject.Exceptions.ViewUsersException;
import com.javalibproject.Menu.Generic.Menu;
import com.javalibproject.Menu.Generic.MenuName;
import com.javalibproject.Menu.Generic.MenuOptions;
import com.javalibproject.Repo.user.book.Book;
import com.javalibproject.Service.BookService;
import com.javalibproject.System.SystemContext;

public class ViewAllBooksMenu extends Menu{
    public static final String BOOK_ID = "BOOK_ID"; // Define a constant for book ID

    public ViewAllBooksMenu(BookService bookService) {
        super("------VİEW ALL BOOKS MENU------", bookService);

        setMenu_options(Arrays.asList(
                new MenuOptions("S", "Search Book",
                       MenuName.ADMIN_SEARCH_BOOKS),
                new MenuOptions("M", "Back to Main Menu",
                       MenuName.ADMIN_MAIN_MENU)
        ));


             
    }


    @Override
    public MenuName execute() {
        printTitle();
        displayAllBooks();
        printOptions();
        return run();
    }
    
    private void displayAllBooks(){
        List<Book> books = getBookService().getAllBooks();
        if(books.isEmpty()){
            error("No books found");
            return;
        } 
        println("-------BOOK LIST-------");
        for (Book book : books) {
            printfItem("Book ID", book.getBookId().toString());
            printfItem("Title", book.getBookName());
            printfItem("Year", book.getYear().toString());
            printfItem("Author", book.getAuthor());
        }
            println("--------------------------------");

         
    }
    
}
