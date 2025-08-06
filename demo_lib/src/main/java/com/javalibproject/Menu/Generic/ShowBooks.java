package com.javalibproject.Menu.Generic;

import java.util.List;

import com.javalibproject.Repo.user.book.Book;
import com.javalibproject.Service.BookService;

public class ShowBooks extends Menu {

    

    public ShowBooks(String title, BookService bookService) {
        super(title, bookService);
        //TODO Auto-generated constructor stub
    }

    protected void showBooks(List<Book> books) {
        System.out.printf("%-5s|%-20s|%-20s|%-20s %n", "ID", "Title", "Year", "Author");

            for (Book b : books) {
                System.out.printf("%-5.5s|%-20.20s|%-20.20s|%-20.20s %n",
                        b.getBookId(), b.getBookName(), b.getYear(), b.getAuthor());
            }
        
        
        
    }
    
}
