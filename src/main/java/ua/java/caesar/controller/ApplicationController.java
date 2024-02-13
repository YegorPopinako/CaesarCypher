package ua.java.caesar.controller;

import ua.java.caesar.service.EncryptingType;
import ua.java.caesar.service.FileProcessor;
import ua.java.caesar.view.ConsoleViewProvider;

public class ApplicationController {
    private final FileProcessor fileProcessor;
    private final ConsoleViewProvider consoleViewProvider;
    public ApplicationController(FileProcessor fileProcessor, ConsoleViewProvider consoleViewProvider) {
        this.fileProcessor = fileProcessor;
        this.consoleViewProvider = consoleViewProvider;
    }

    public void execute(String[] args) {
        validateParameters(args);
        consoleViewProvider.print("parameters are valid");
        consoleViewProvider.print("executing...:");

        EncryptingType action = EncryptingType.valueOf(args[0]);
        String filePath = convertBackslashesToForward(args[1]);
        System.out.println(filePath);
        int key = (args.length == 3) ? Integer.parseInt(args[2]) : 0;

        fileProcessor.cypherFile(action, filePath, key);
    }

    private void validateParameters(String[] args) {
        if(!isValidArguments(args)) {
            throw new IllegalArgumentException("Incorrect number of arguments");
        }

        String action = args[0];
        EncryptingType type = EncryptingType.valueOf(action);

        if(type == EncryptingType.ENCRYPT || type == EncryptingType.DECRYPT) {
            if(args.length != 3){
                throw new IllegalArgumentException("Incorrect number of arguments");
            }
        }

        if(type == EncryptingType.BRUTE_FORCE) {
            if(args.length != 2){
                throw new IllegalArgumentException("Incorrect number of arguments");
            }
        }
    }

    private boolean isValidArguments(String[] args) {
        return (args.length == 2 || args.length == 3);
    }

    public static String convertBackslashesToForward(String filePath) {
        return filePath.replace("\\", "/").replace("?", "");
    }
}
