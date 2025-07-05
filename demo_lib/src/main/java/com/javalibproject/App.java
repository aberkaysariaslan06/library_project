package com.javalibproject;

import java.io.BufferedReader;
import java.io.Console;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.javalibproject.Menu.ConsoleReader;
import com.javalibproject.Menu.MainLoginMenu;
import com.javalibproject.Menu.Menu;
import com.javalibproject.Menu.MenuOptions;


public class App 
{
    public static void main( String[] args ) 
    {
       
      Menu mainMenu = new MainLoginMenu();
      mainMenu.execute();
   
        
    }
}
