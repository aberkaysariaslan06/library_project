package com.javalibproject.Repo.user.book;

import java.text.Normalizer;
import java.util.*;


public class BookRepository {
    private final Map<Integer, Book> books = new HashMap<>();

    public void createBook(Book newBook) {
        Integer maxId = books.keySet().stream().max(Comparator.naturalOrder()).orElse(1); // max userId'yi bulur, eger yoksa 1 olarak baslar
        Integer newBookId = maxId + 1; // yeni userId'yi maxId + 1 olarak belirler

        Book book = new Book(newBookId, newBook.getBookName(), newBook.getYear(), newBook.getAuthor());
        books.put(newBookId, book);

    }
    public void deleteBookByBookId(Integer bookId) {
       books.remove(bookId);
    }
    public Optional<Book> getByBookId(Integer bookId) {
        return Optional.ofNullable(books.get(bookId));
    }
    /*
    public List<Book> searchBooks(String searchTerm) {
        return books.values().stream().filter(b -> searchBook(b, searchTerm))
            .toList(); // Java 16 ile gelen toList() metodu, stream'den listeye donus yapar  
    }
    */


    /*
    private boolean searchBook(Book book, String searchTerm){
         
             
        return findIn(searchTerm, 
            book.getBookId(),
            book.getBookName(),
            book.getYear(),
            book.getAuthor());
        

    }

    private boolean findIn(String searchTerm, Object... values) {
        return Arrays.stream(values)
        .anyMatch(s->s.toString().contains(searchTerm));
        // .filter(s->s.contains(searchTerm))
        // .findAny().isPresent();
    }
    */

    public List<Book> searchBooks(String query) {
        String norm = normalize(query);
        if(norm.isEmpty()) return List.of();
        String[] terms = norm.split("\\s+");
        return getAllBooks().stream()
                .filter(book -> {
                    String idStr = normalize(String.valueOf(book.getBookId()));
                    String bookNameStr = normalize(book.getBookName());
                    String yearStr = normalize(String.valueOf(book.getYear()));
                    String authorStr = normalize(book.getAuthor());
                    for(String term : terms){
                        boolean matchedThisTerm = idStr.contains(term) ||
                                bookNameStr.contains(term) ||
                                yearStr.contains(term) ||
                                authorStr.contains(term);
                        if(!matchedThisTerm) {
                            return false;
                        }

                    }
                    return true;
                })
                .toList();
    }

    private static String normalize(String s) {
        if(s==null) return "";
        String t = s.trim().toLowerCase(java.util.Locale.ROOT);
        t = java.text.Normalizer.normalize(t, Normalizer.Form.NFD)
                .replaceAll("\\p{M}", "");
        return t;

    }

    public void updateBook(Book updatedBook) {
        books.put(updatedBook.getBookId(), updatedBook);
    }

    public List<Book> searchBooksBorrowedByUserId(Integer userId) {
        return books.values().stream().
                filter(b -> Objects.equals(b.getBorrowedBy(), userId))
                .toList();
    }
    public List<Book> getAllBooks() {
        // TODO Auto-generated method stub
        return new ArrayList<>(books.values());
    }
}
