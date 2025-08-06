package com.javalibproject.Menu.Customer.ReturnBooks;

import com.javalibproject.Menu.Generic.ConsoleReader;
import com.javalibproject.Menu.Generic.Menu;
import com.javalibproject.Menu.Generic.MenuName;
import com.javalibproject.Menu.Generic.ShowBooks;
import com.javalibproject.Repo.user.book.Book;
import com.javalibproject.Service.BookService;
import com.javalibproject.System.SystemContext;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class ReturnBookMenu extends ShowBooks {
    public static final String BOOK_ID = "BOOK_ID"; // Define a constant for book ID 

    public ReturnBookMenu(BookService bookService){
        super("Return Book Menu", bookService);

    }

    @Override
    public MenuName execute() {
        // printTitle();

        List<Book> books = getBookService().searchBooksBorrowedByUserId(SystemContext.getLoggedInUserID());
        if(books.isEmpty()) {
            error("No borrowed book found. Press enter the continue.");
            ConsoleReader.readLine();

            return MenuName.USER_MAIN_MENU;
        } else {
            showBooks(books);
            String choice = printAndGet("Enter book ID to see OR 'X' to go back to main menu:");
            if (choice.equalsIgnoreCase("X")) {
                return MenuName.USER_MAIN_MENU; // go back to admin main menu

            } else {
                Optional<Book> optionalBook = books.stream()
                        .filter(b->b.getBookId().toString().equals(choice))
                        .findFirst();

                if (optionalBook.isPresent()) {
                    Book book = optionalBook.get();
                    book.returnBook();
                    getBookService().updateBook(book);
                    System.out.println();
                    println("Borrowed Book : " + book.getBookName() + " successfully borrowed. Please enter to go back to main menu");

                } else {
                    return execute();
                }
            }
            return MenuName.USER_MAIN_MENU; // go back to user main menu
        }
    }
}
