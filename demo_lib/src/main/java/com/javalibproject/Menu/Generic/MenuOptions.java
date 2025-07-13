package com.javalibproject.Menu.Generic;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class MenuOptions {
    
    private final String userInput;
    private final String title;
    private final MenuOptionHandler handler;

    
}
