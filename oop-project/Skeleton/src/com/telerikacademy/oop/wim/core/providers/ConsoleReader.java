package com.telerikacademy.oop.wim.core.providers;

import com.telerikacademy.oop.wim.core.contracts.Reader;

import java.util.Scanner;

public class ConsoleReader implements Reader {
    
    private final Scanner scanner;
    
    public ConsoleReader() { scanner = new Scanner(System.in); }
    
    public String readLine() {
        return scanner.nextLine().trim();
    }

    
}
