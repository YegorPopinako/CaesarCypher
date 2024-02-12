package ua.java.caesar.view;

import java.util.Scanner;

public class ConsoleViewProvider {
    public String read(){
        Scanner scanner = new Scanner(System.in);

        return scanner.next();
    }

    public void print(String message){
        System.out.println(message);
    }
}
