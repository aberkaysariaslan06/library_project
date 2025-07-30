package com.javalibproject.Service;

import java.util.List;
import java.util.Optional;

import com.javalibproject.Repo.user.Customer;
import com.javalibproject.Repo.user.SystemUser;
import com.javalibproject.Repo.user.book.Book;

public interface BookService {
    void createBook(Book book);
    void deleteBookByBookId(Integer bookId);
    
   
    List<Book> searchBooks(String searchTerm); 
    Optional<Customer> getById(Integer bookId);

    void updateBook(Book book);

    
}
