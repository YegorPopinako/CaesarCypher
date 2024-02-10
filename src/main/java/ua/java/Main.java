package ua.java;

public class Main {
    public static void main(String[] args) {
        FileEncoderService fileEncoderService = new FileEncoderService(new CaesarCypher(Alphabet.ALPHABET));
        fileEncoderService.encodeFile("src/main/resources/src.txt", EncryptingType.ENCRYPT, 3);
    }
}
