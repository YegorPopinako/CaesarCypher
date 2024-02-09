package org.example;

import org.example.fileservice.EncryptingType;
import org.example.fileservice.FileReaderWriter;

public class Main {
    public static void main(String[] args) {
        FileReaderWriter fileReaderWriter = new FileReaderWriter();
        fileReaderWriter.Encoder(EncryptingType.ENCRYPT , "src/main/resources/src.txt", 2);
    }
}
