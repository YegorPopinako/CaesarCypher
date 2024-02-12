package ua.java.caesar;

import ua.java.caesar.caesarcypher.CaesarCypher;
import ua.java.caesar.controller.ApplicationController;
import ua.java.caesar.service.EncryptingType;
import ua.java.caesar.service.FileProcessor;
import ua.java.caesar.view.ConsoleViewProvider;

public class CaesarConsoleApplication {
    public static void main(String[] args) {
        FileProcessor fileProcessor = new FileProcessor(new CaesarCypher());
        //fileProcessor.cypherFile(EncryptingType.ENCRYPT, "src/main/resources/src.txt", 6);
        //fileProcessor.cypherFile(EncryptingType.DECRYPT, "src/main/resources/src[ENCRYPTED].txt", 6);
        fileProcessor.cypherFile(EncryptingType.BRUTE_FORCE, "src/main/resources/src[ENCRYPTED].txt", 0);
    }
}
