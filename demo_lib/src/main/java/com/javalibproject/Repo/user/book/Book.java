package com.javalibproject.Repo.user.book;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@Setter
public class Book {
    private final Integer bookId;
    private final String bookName;
    private final Integer year;
    private final String author;
    private Integer borrowedBy;
    private LocalDateTime borrowedDateTime;

    public Book(Integer bookId, String bookName, Integer year, String author){
        this.bookId = bookId;
        this.bookName = bookName;
        this.year = year;
        this.author = author;
        this.borrowedBy = null; // initially not borrowed
        this.borrowedDateTime = null; // initially not borrowed
    }

    public void borrowBook(Integer userId) {
        this.borrowedBy = userId;
        this.borrowedDateTime = LocalDateTime.now();
    }
    public void returnBook() {
        this.borrowedBy = null;
        this.borrowedDateTime = null;
    }


    public boolean isBorrowed() {
        return borrowedBy !=null;
    }
}
