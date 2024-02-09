package org.example.fileservice;

import org.example.caesarcypher.CaesarCypher;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileReaderWriter {
    public void Encoder(EncryptingType type, String sourcePath, int key) {
        try {
            createAndWriteToNewFile(sourcePath, type, key);
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void createAndWriteToNewFile(String sourcePath, EncryptingType type, int key) throws IOException {
        Path source = Paths.get(sourcePath);
        String newFileName = getNewFileName(source.getFileName().toString(), type);
        Path destPath = Paths.get(source.getParent().toString(), newFileName);

        if (!Files.exists(destPath)) {
            Files.createFile(destPath);
        }

        try (BufferedReader reader = Files.newBufferedReader(source);
             BufferedWriter writer = Files.newBufferedWriter(destPath)) {
            String line;
            while ((line = reader.readLine()) != null) {
                String encryptedLine = Encryption(line, type, key);
                writer.write(encryptedLine);
                writer.newLine();
            }
        }
    }

    private String getNewFileName(String oldFileName, EncryptingType type) {
        int dotIndex = oldFileName.lastIndexOf(".");
        return oldFileName.substring(0, dotIndex) + "[" + type + "ED" + "]" + oldFileName.substring(dotIndex);
    }

    public String Encryption(String line, EncryptingType type, int key) {
        CaesarCypher cypher = new CaesarCypher();
         return switch (type) {
            case ENCRYPT -> cypher.Encrypt(line, key);
            case DECRYPT -> cypher.Decrypt(line, key);
            default -> line;
        };
    }
}
