package com.javalibproject.Service;

import java.util.List;
import java.util.Optional;
import com.javalibproject.Repo.user.book.Book;
import com.javalibproject.Repo.user.book.BookRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class BookServiceImp implements BookService {

    private final BookRepository bookRepository;

   

    @Override
    public void createBook(Book book) {
        bookRepository.createBook(book);
        // Implementation for creating a book
    }

    @Override
    public void deleteBookByBookId(Integer bookId) {
        bookRepository.deleteBookByBookId(bookId);
        // Implementation for deleting a book by its ID
    }

    @Override
    public List<Book> searchBooks(String searchTerm) {
        // Implementation for searching books by a term
        return bookRepository.searchBooks(searchTerm); // Placeholder return
    }

    @Override
    public Optional<Book> getByBookId(Integer bookId) {
        // Implementation for getting a book by its ID
        return bookRepository.getByBookId(bookId); // Placeholder return
    }

    @Override
    public void updateBook(Book book) {
        bookRepository.updateBook(book);
        // Implementation for updating a book
    }

    @Override
    public List<Book> searchBooksBorrowedByUserId(Integer userId) {
        return bookRepository.searchBooksBorrowedByUserId(userId);

    }

    @Override
    public List<Book> getAllBooks() {
        // TODO Auto-generated method stub
        return bookRepository.getAllBooks();
    }


}
