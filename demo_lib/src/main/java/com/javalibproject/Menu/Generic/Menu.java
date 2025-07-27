package com.javalibproject.Menu.Generic;

import java.util.List;
import java.util.Optional;

import com.javalibproject.Service.UserService;


import lombok.Getter;
import lombok.Setter;
/*
 final keywordu ile belirlenen ozellikler degistirilemez o yuzden sadece getter metotu kullanilabilir.eger setterde kullanilmaya calissaydi celisecekti ve ide hata verecekti.
 */


public class Menu {
    private final String title; //Library Login Menu
    @Setter
    private List<MenuOptions> menu_options; // U-User Login, A-Admin Login, X-Exit
    @Getter
    private UserService userService;

    public Menu (String title) {
        this.title = title;
    }

    public Menu(String title, UserService userService) {
        this.title = title;
        this.userService = userService;
        
        
    }

    // public Menu(String title, List<MenuOptions> menu_options) {
    //     this.title = title;
    //     this.menu_options = menu_options;   
    // }

    protected void printTitle() {println(title);}

    protected void printOptions() {
        
        for (MenuOptions option : menu_options) {
            println(option.getUserInput() + " - " + option.getTitle());
            // System.out.printf("(%s) - %s %n", option.getUserInput(), option.getTitle());
        }
        print("Enter your choice: ");
        
      
    }
    protected MenuOptions getOption() { 
        int attempts = 0;
        while (true) {
            final String userInput  = ConsoleReader.readLine();
            println("You selected: " + userInput);

            Optional<MenuOptions> option = menu_options.stream()
                .filter(o -> o.getUserInput().equalsIgnoreCase(userInput))
                .findFirst();

            if (option.isPresent()) {
                return option.get();
            } else {
                attempts++;
                if (attempts >= 3) {
                    println("Too many invalid attempts. Exiting.");
                    System.exit(0);
                }
                println("Invalid option selected. Please try again.");
                print("New choice: ");
            }
        }
    }

    public MenuName execute() {
        printTitle();
        printOptions();  
        printTitle();
        return run();

    }
    protected MenuName run () {
        MenuOptions selectedOption = getOption();
        if (selectedOption.getHandler() != null) {
            return selectedOption.getHandler().get();
        } else {
            return selectedOption.getMenuName();
        }
    }
    protected void print (String message) {
        System.out.print(message);
    }
    protected String printAndGet(String text) { System.out.print(text); return ConsoleReader.readLine();}
    protected void println (String message) {System.out.println(message);}
    protected void error (String message) {System.err.println(message);}

        
        
}
