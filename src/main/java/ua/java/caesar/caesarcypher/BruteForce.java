package ua.java.caesar.caesarcypher;

import ua.java.caesar.caesarcypher.CaesarCypher;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;

public class BruteForce {
    private final Map<Character, Integer> mostUsedLetters = new HashMap<>();

    private char mostFrequentLetterInSource = '\0';
    private final List<Integer> bestKeys = new ArrayList<>();
    public BruteForce() {
        mostUsedLetters.put('e', 0);
        mostUsedLetters.put('t', 0);
        mostUsedLetters.put('a', 0);
    }


    public void writePossibleDecryptionsWithKeys(CaesarCypher caesarCypher, String sourcePath) {
        byte[] bytes;
        char mostFrequentDecryptedLetter;
        try {
            bytes = Files.readAllBytes(Paths.get(sourcePath));
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        mostFrequentLetterInSource = findMostFrequentLetter(bytes);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        for (int key = 0; key < caesarCypher.alphabet.size(); key++) {
            String decryptedText = decryptTextWithKey(caesarCypher, bytes, key);
            mostFrequentDecryptedLetter = findMostFrequentLetter(decryptedText.getBytes());
            if (mostUsedLetters.containsKey(mostFrequentDecryptedLetter)) {
                try {
                    outputStream.write(("Decrypted text with key " + key + ":\n").getBytes());
                    outputStream.write(decryptedText.getBytes());
                    outputStream.write("\n".getBytes());
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
        System.out.println(outputStream.toString());
    }
    private char findMostFrequentLetter(byte[] bytes) {
        Map<Character, Integer> characterFrequencyFromFile = new HashMap<>();
        char mostFrequentLetter = '\0';
        int maxFrequency = 0;

        String content = new String(bytes);
        for (char c : content.toCharArray()) {
            if (Character.isLetter(c)) {
                char lowercaseChar = Character.toLowerCase(c);
                characterFrequencyFromFile.put(lowercaseChar, characterFrequencyFromFile.getOrDefault(lowercaseChar, 0) + 1);
                int frequency = characterFrequencyFromFile.get(lowercaseChar);
                if (frequency > maxFrequency) {
                    mostFrequentLetter = lowercaseChar;
                    maxFrequency = frequency;
                }
            }
        }
        return mostFrequentLetter;
    }

    private String decryptTextWithKey(CaesarCypher caesarCypher, byte[] bytes, int key) {
        String content = new String(bytes);
        return caesarCypher.decrypt(content, key);
    }

    private String getNewFileName(String oldFileName) {
        int dotIndex = oldFileName.lastIndexOf(".");
        return oldFileName.substring(0, dotIndex) + "[BRUTE_FORCED]" + oldFileName.substring(dotIndex);
    }
}
