package ua.java.caesar.caesarcypher;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BruteForce {
    private final List<Character> mostUsedLetters = List.of('e', 't', 'a');
    CaesarCypher caesarCypher = new CaesarCypher();

    public void writePossibleDecryptionsWithKeys(String sourcePath) {
        String newFileName = getNewFileName(sourcePath);
        byte[] bytes = readAllBytes(sourcePath);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        decryptWithPassingKeys(caesarCypher, bytes, outputStream);
        writeDecryprions(newFileName, outputStream);
    }

    private void writeDecryprions(String newFileName, ByteArrayOutputStream outputStream) {
        try {
            Files.write(Paths.get(newFileName), outputStream.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private byte[] readAllBytes(String sourcePath) {
        byte[] bytes;
        try {
            bytes = Files.readAllBytes(Paths.get(sourcePath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return bytes;
    }

    private void decryptWithPassingKeys(CaesarCypher caesarCypher, byte[] bytes, ByteArrayOutputStream outputStream) {
        char mostFrequentDecryptedLetter;
        for (int key = 0; key < caesarCypher.getAlphabet().size(); key++) {
            String decryptedText = decryptTextWithKey(caesarCypher, bytes, key);
            mostFrequentDecryptedLetter = findMostFrequentLetter(decryptedText.getBytes());
            if (mostUsedLetters.contains(mostFrequentDecryptedLetter)) {
                try {
                    outputStream.write(("Decrypted text with key " + key + ":\n").getBytes());
                    outputStream.write(decryptedText.getBytes());
                    outputStream.write("\n".getBytes());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private char findMostFrequentLetter(byte[] bytes) {
        Map<Character, Integer> characterFrequencyFromFile = new HashMap<>();
        char mostFrequentLetter = '\0';
        int maxFrequency = 0;

        String content = new String(bytes);
        for (char c : content.toCharArray()) {
            if (Character.isLetter(c)) {
                characterFrequencyFromFile.put(c, characterFrequencyFromFile.getOrDefault(c, 0) + 1);
                int frequency = characterFrequencyFromFile.get(c);
                if (frequency > maxFrequency) {
                    mostFrequentLetter = c;
                    maxFrequency = frequency;
                }
            }
        }
        return mostFrequentLetter;
    }

    private String decryptTextWithKey(CaesarCypher caesarCypher, byte[] bytes, int key) {
        String content = new String(bytes);
        StringBuilder decryptedText = new StringBuilder();
        for (int i = 0; i < content.length(); i++) {
            char originalChar = content.charAt(i);
            char decryptedChar;
            if (Character.isLetter(originalChar)) {
                char lowercaseChar = Character.toLowerCase(originalChar);
                int index = caesarCypher.getAlphabet().indexOf(lowercaseChar);
                int decryptedIndex = (index - key + caesarCypher.getAlphabet().size()) % caesarCypher.getAlphabet().size();
                char decryptedLowercaseChar = caesarCypher.getAlphabet().get(decryptedIndex);
                decryptedChar = Character.toLowerCase(decryptedLowercaseChar);
            } else {
                decryptedChar = originalChar;
            }
            decryptedText.append(decryptedChar);
        }
        return decryptedText.toString();
    }

    private String getNewFileName(String oldFileName) {
        int dotIndex = oldFileName.lastIndexOf(".");
        return oldFileName.substring(0, dotIndex) + "[BRUTE_FORCED]" + oldFileName.substring(dotIndex);
    }
}
