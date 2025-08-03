package com.javalibproject.Menu.Customer.ReturnBooks;

import com.javalibproject.Menu.Generic.Menu;
import com.javalibproject.Menu.Generic.MenuName;
import com.javalibproject.Repo.user.book.Book;
import com.javalibproject.Service.BookService;
import com.javalibproject.System.SystemContext;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class ReturnBookMenu extends Menu {
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
            // return execute(); // main menu
            return MenuName.USER_MAIN_MENU;
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
                    return MenuName.USER_MAIN_MENU; // go to view book detail
                } else {
                    return execute();
                }
            }
        }
    }
}
