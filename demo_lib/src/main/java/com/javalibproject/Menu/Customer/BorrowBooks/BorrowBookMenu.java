package com.javalibproject.Menu.Customer.BorrowBooks;

import com.javalibproject.Menu.Generic.ConsoleReader;
import com.javalibproject.Menu.Generic.Menu;
import com.javalibproject.Menu.Generic.MenuName;
import com.javalibproject.Menu.Generic.ShowBooks;
import com.javalibproject.Repo.user.book.Book;
import com.javalibproject.Service.BookService;
import com.javalibproject.System.SystemContext;

import java.util.List;
import java.util.Optional;

public class BorrowBookMenu extends ShowBooks {
    public static final String BOOK_ID = "BOOK_ID"; // Define a constant for book ID

    public BorrowBookMenu(BookService bookService) {
        super("------BORROW BOOK MENU------", bookService);

    }

    private void attemptBorrow(List<Book> books) { // sonuc olarak sen searchTerm ile de displayAll ile de olsa borrow
                                                   // book yapiyorsun. onu bu function ile al.
        String choice = printAndGet("Enter book ID which is borrowed OR 'X' to go back to main menu:");
        if (choice.equalsIgnoreCase("X"))
            return;

        books.stream()
                .filter(b -> b.getBookId().toString().equals(choice))
                .findFirst()
                .ifPresentOrElse(
                        this::borrowAndNotify,
                        () -> attemptBorrow(books));

    }

    private void borrowAndNotify(Book book) {
        book.borrowBook(SystemContext.getLoggedInUserID());
        getBookService().updateBook(book);
        System.out.println();
        println("Borrowed Book : " + book.getBookName()
                + " successfully borrowed. Please enter to go back to main menu.");
        ConsoleReader.readLine();

    }

    private void handleDisplayAll() {
        List<Book> availabeBook = getBookService()
                .getAllBooks()
                .stream()
                .filter(b -> !b.isBorrowed())
                .toList();

        if (availabeBook.isEmpty()) {
            error("There is no available book to borrow.");
        }
        showBooks(availabeBook);
        attemptBorrow(availabeBook);

    }

    private void handleSearchTerm() {
        String searchTerm = printAndGet("Enter book name to search:");
        List<Book> books = getBookService()
                .searchBooks(searchTerm)
                .stream()
                .filter(b -> !b.isBorrowed()) // filter out borrowed books
                .toList();
        if (books.isEmpty()) {
            error(searchTerm + "not");
        } else {
            showBooks(books);
            attemptBorrow(books);
        }

    }

    @Override
    public MenuName execute() {
        printTitle();
        String borrowChoice = printAndGet("If search with search term please enter 'S', or list all books with 'D' :");
        if (borrowChoice.equalsIgnoreCase("D")) {
            handleDisplayAll();
        } else if (borrowChoice.equalsIgnoreCase("S")) {
            handleSearchTerm();
        }
        return MenuName.USER_MAIN_MENU;

    }

    // @Override
    // public MenuName execute() {
    // printTitle();
    // String borrowChoice = printAndGet("If search with search term please enter
    // 'S', or list all books with 'D' :");
    // if(borrowChoice.equalsIgnoreCase("D")) {
    // displayAllBooks();
    // List<Book> books = getBookService().getAllBooks();

    // String choice = printAndGet("Enter book ID which is borrowed OR 'X' to go
    // back to main menu:");
    // if (choice.equalsIgnoreCase("X")) {
    // return MenuName.USER_MAIN_MENU; // go back to admin main menu

    // } else {
    // Optional<Book> optionalBook = books.stream()
    // .filter(b->b.getBookId().toString().equals(choice))
    // .findFirst();

    // if (optionalBook.isPresent()) {
    // Book book = optionalBook.get();
    // book.borrowBook(SystemContext.getLoggedInUserID());
    // getBookService().updateBook(book);
    // System.out.println();
    // println("Borrowed Book : " + book.getBookName() + " successfully borrowed.
    // Please enter to go back to main menu.");
    // ConsoleReader.readLine();
    // } else {
    // return execute();
    // }
    // }
    // return MenuName.USER_MAIN_MENU;
    // } else if (borrowChoice.equalsIgnoreCase("S")){
    // String searchTerm = printAndGet("Enter book name to search:");
    // List<Book> books = getBookService().searchBooks(searchTerm)
    // .stream().filter(b->!b.isBorrowed())
    // .toList();// filter out borrowed books)
    // if(books.isEmpty()) {
    // error("No book found with the book name: " + searchTerm);

    // } else {
    // showBooks(books);
    // String choice = printAndGet("Enter book ID which is borrowed OR 'X' to go
    // back to main menu:");
    // if (choice.equalsIgnoreCase("X")) {
    // return MenuName.USER_MAIN_MENU; // go back to admin main menu

    // } else {
    // Optional<Book> optionalBook = books.stream()
    // .filter(b->b.getBookId().toString().equals(choice))
    // .findFirst();

    // if (optionalBook.isPresent()) {
    // Book book = optionalBook.get();
    // book.borrowBook(SystemContext.getLoggedInUserID());
    // getBookService().updateBook(book);
    // System.out.println();
    // println("Borrowed Book : " + book.getBookName() + " successfully borrowed.
    // Please enter to go back to main menu.");
    // ConsoleReader.readLine();
    // } else {
    // return execute();
    // }
    // }
    // }
    // }

    // return MenuName.USER_MAIN_MENU;
    // }
}
