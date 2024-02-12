package ua.java.caesar.caesarcypher;

import ua.java.caesar.alphabets.Alphabet;

import java.util.List;


public class CaesarCypher {
    final List<Character> alphabet;

    private final BruteForce bruteForce = new BruteForce();

    public CaesarCypher() {
        this(Alphabet.ALPHABET);
    }

    public CaesarCypher(List<Character> alphabet) {
        this.alphabet = alphabet;
    }

    public String encrypt(String string, int key) {
        return encode(string, key);
    }

    public String decrypt(String string, int key) {
        return encode(string, -key);
    }

    public void bruteForce(CaesarCypher caesarCypher, String sourcePath) {
        bruteForce.writePossibleDecryptionsWithKeys(caesarCypher, sourcePath);
    }

    private String encode(String string, int key) {
        char[] charArray = string.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            for (Character character : alphabet) {
                if (charArray[i] == character) {
                    int newIndex = (alphabet.indexOf(character) + key + alphabet.size()) % alphabet.size();
                    char replaced = setCaseToCharacter(charArray[i], newIndex);
                    charArray[i] = replaced;
                    break;
                }
            }
        }
        return String.valueOf(charArray);
    }

    private char setCaseToCharacter(char character, int newIndex) {
        char replaced = character;
        if (Character.isUpperCase(character)) {
            replaced = alphabet.get(newIndex);
            Character.toUpperCase(replaced);
        } else if (Character.isLowerCase(character)) {
            replaced = alphabet.get(newIndex);
            Character.toLowerCase(replaced);
        }
        return replaced;
    }
}
