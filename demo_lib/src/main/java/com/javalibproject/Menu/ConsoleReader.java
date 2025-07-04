package com.javalibproject.Menu;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.Buffer;

public class ConsoleReader {
    /*
     * Her zaman farkli menulerde de kullanilabilecek oldugu icin bu fonksiyon ayrilabilir.
     */

    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    public static String readLine() {
        try {
            return reader.readLine();
        } catch (Exception e) {
            System.err.println("Error reading input: " + e.getMessage());
            return null;
        }
}
}

