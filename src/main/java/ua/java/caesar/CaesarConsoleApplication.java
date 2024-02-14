package ua.java.caesar;

import ua.java.caesar.caesarcypher.CaesarCypher;
import ua.java.caesar.controller.ApplicationController;
import ua.java.caesar.service.FileProcessor;
import ua.java.caesar.view.ConsoleViewProvider;

public class CaesarConsoleApplication {
    public static void main(String[] args) {
        int C = 2;
        FileProcessor fileProcessor = new FileProcessor(new CaesarCypher());
        ConsoleViewProvider consoleViewProvider = new ConsoleViewProvider();
        new ApplicationController(fileProcessor, consoleViewProvider).execute(args);
    }
}
