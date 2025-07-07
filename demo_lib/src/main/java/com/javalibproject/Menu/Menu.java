package com.javalibproject.Menu;

import java.util.List;
import java.util.Optional;

import lombok.AllArgsConstructor;
import lombok.Setter;


public class Menu {
    private final String title; //Library Login Menu
    @Setter
    private List<MenuOptions> menu_options; // U-User Login, A-Admin Login, X-Exit

    public Menu(String title) {
        this.title = title;
        
    }

    // public Menu(String title, List<MenuOptions> menu_options) {
    //     this.title = title;
    //     this.menu_options = menu_options;   
    // }

    public void printOptions() {
        System.out.println(title);
        for (MenuOptions option : menu_options) {
            System.out.println(option.getUserInput() + " - " + option.getTitle());
            // System.out.printf("(%s) - %s %n", option.getUserInput(), option.getTitle());
        }
        System.out.println("Enter your choice: ");
        
      
    }
    public MenuOptions getOption() { 
        int attempts = 0;
        while (true) {
            final String userInput  = ConsoleReader.readLine();
            System.out.println("You selected: " + userInput);

            Optional<MenuOptions> option = menu_options.stream()
                .filter(o -> o.getUserInput().equalsIgnoreCase(userInput))
                .findFirst();

            if (option.isPresent()) {
                return option.get();
            } else {
                attempts++;
                if (attempts >= 3) {
                    System.out.println("Too many invalid attempts. Exiting.");
                    System.exit(0);
                }
                System.out.println("Invalid option selected. Please try again.");
                System.out.print("New choice: ");
            }
        }
    }

    public void execute() {
      printOptions();  
      MenuOptions selectedOption = getOption();
      selectedOption.getHandler().handle();
    }

    
        
        
}
