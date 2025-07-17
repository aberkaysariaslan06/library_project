package com.javalibproject.Menu.Generic;

import java.util.function.Supplier;


import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class MenuOptions {
    
    private  String userInput;
    private  String title;
    // private final MenuOptionHandler handler;
    private Supplier<MenuName> handler;
    private MenuName menuName;

    public MenuOptions(String userInput, String title, Supplier<MenuName> handler) {
        this.userInput = userInput;
        this.title = title;
        this.handler = handler;
    }
    public MenuOptions(String userInput, String title, MenuName menuName) {
        this.userInput = userInput;
        this.title = title;
        this.menuName = menuName;
        
    }
   
    
}
