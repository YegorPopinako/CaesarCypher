package ua.java;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileEncoderService {
    private final CaesarCypher caesarCypher;

    public FileEncoderService(CaesarCypher caesarCypher){
        this.caesarCypher = caesarCypher;
    }

    public void encodeFile(String sourcePath, EncryptingType type, int key) {
        try {
            createAndWriteToNewFile(sourcePath, type, key);
        } catch (IOException e) {
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
                String encryptedLine = getEncryptingType(line, type, key);
                writer.write(encryptedLine);
                writer.newLine();
            }
        }
    }

    private String getNewFileName(String oldFileName, EncryptingType type) {
        int dotIndex = oldFileName.lastIndexOf(".");
        return oldFileName.substring(0, dotIndex) + "[" + type + "ED" + "]" + oldFileName.substring(dotIndex);
    }

    public String getEncryptingType(String line, EncryptingType type, int key) {
        return switch (type) {
            case ENCRYPT -> caesarCypher.encrypt(line, key);
            case DECRYPT -> caesarCypher.decrypt(line, key);
            default -> line;
        };
    }
}
