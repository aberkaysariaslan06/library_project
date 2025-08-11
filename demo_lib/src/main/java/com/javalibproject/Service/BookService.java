package com.javalibproject.Service;

import java.util.List;
import java.util.Optional;

import com.javalibproject.Repo.user.User.Customer;
import com.javalibproject.Repo.user.User.SystemUser;
import com.javalibproject.Repo.user.book.Book;

public interface BookService {
    void createBook(Book book);

    void deleteBookByBookId(Integer bookId);

    List<Book> searchBooks(String searchTerm);

    Optional<Book> getByBookId(Integer bookId);

    void updateBook(Book book);

    List<Book> getAllBooks();

    List<Book> searchBooksBorrowedByUserId(Integer loggedInUserID);
}
