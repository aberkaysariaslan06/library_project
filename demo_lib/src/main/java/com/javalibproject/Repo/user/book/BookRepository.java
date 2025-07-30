package com.javalibproject.Repo.user.book;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.javalibproject.Repo.user.AdminUser;
import com.javalibproject.Repo.user.Customer;
import com.javalibproject.Repo.user.SystemUser;

public class BookRepository {
    private final Map<Integer, Book> books = new HashMap<>();

    public void createBook(Book newBook) {
        Integer maxId = books.keySet().stream().max(Comparator.naturalOrder()).orElse(1); // max userId'yi bulur, eger yoksa 1 olarak baslar
        Integer newBookId = maxId + 1; // yeni userId'yi maxId + 1 olarak belirler

        Book book = new Book(newBookId, newBook.title(), newBook.year(), newBook.author());
        books.put(newBookId, book);

    }
    public void deleteBookByBookId(Integer bookId) {
       books.remove(bookId);
    }
    public Optional<Book> getByBookId(Integer bookId) {
        return Optional.ofNullable(books.get(bookId));
    }
    public List<Book> searchBooks(String searchTerm) {
        return books.values().stream().filter(b -> searchBook(b, searchTerm))
            .toList(); // Java 16 ile gelen toList() metodu, stream'den listeye donus yapar
            
           
    }
    private boolean searchBook(Book book, String searchTerm){
         
             
        return findIn(searchTerm, 
            book.id(),
            book.title(),
            book.year(),
            book.author());
        

    }
    private boolean findIn(String searchTerm, Object... values) {
        return Arrays.stream(values)
        .anyMatch(s->s.toString().contains(searchTerm));
        // .filter(s->s.contains(searchTerm))
        // .findAny().isPresent();
    }


   

    public void updateBook(Book updatedBook) {
        book.put(updatedBook.(), updatedBook);
    }
    
}
