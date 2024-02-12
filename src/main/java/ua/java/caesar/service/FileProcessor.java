package ua.java.caesar.service;

import ua.java.caesar.caesarcypher.CaesarCypher;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileProcessor {
    private final CaesarCypher caesarCypher;

    public FileProcessor(CaesarCypher caesarCypher){
        this.caesarCypher = caesarCypher;
    }


    public void cypherFile(EncryptingType type, String sourcePath, int key) {
        if (type == EncryptingType.BRUTE_FORCE){
            bruteForce(caesarCypher, sourcePath);
        }
        else{
            try {
                createAndWriteToNewFile(sourcePath, type, key);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void bruteForce(CaesarCypher caesarCypher, String filePath){
        caesarCypher.bruteForce(caesarCypher, filePath);
    }

    private void createAndWriteToNewFile(String sourcePath, EncryptingType type, int key) throws IOException {
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
                String encryptedLine = getEncryptingType(line, type, key);
                writer.write(encryptedLine);
                writer.newLine();
            }
        }
    }

    private String getNewFileName(String oldFileName, EncryptingType type) {
        int dotIndex = oldFileName.lastIndexOf(".");
        String prefix = oldFileName.substring(0, dotIndex);
        String extension = oldFileName.substring(dotIndex);

        String tag = (type == EncryptingType.ENCRYPT) ? "[ENCRYPTED]" : "[DECRYPTED]";
        String oppositeTag = (type == EncryptingType.DECRYPT) ? "[DECRYPTED]" : "[ENCRYPTED]";

        if (prefix.contains("[ENCRYPTED]") || prefix.contains("[DECRYPTED]")) {
            prefix = prefix.replace("[ENCRYPTED]", oppositeTag).replace("[DECRYPTED]", oppositeTag);
        } else {
            prefix += tag;
        }
        return prefix + extension;
    }

    private String getEncryptingType(String line, EncryptingType type, int key) {
        return switch (type) {
            case ENCRYPT -> caesarCypher.encrypt(line, key);
            case DECRYPT -> caesarCypher.decrypt(line, key);
            default -> line;
        };
    }
}
