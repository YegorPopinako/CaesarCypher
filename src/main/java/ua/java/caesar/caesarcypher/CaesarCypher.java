package ua.java.caesar.caesarcypher;

import ua.java.caesar.alphabets.Alphabet;

import java.util.List;


public class CaesarCypher {
    private final List<Character> ALPHABET;
    private final BruteForce BRUTEFORCE = new BruteForce();

    public CaesarCypher() {
        this(Alphabet.ALPHABET);
    }

    public CaesarCypher(List<Character> alphabet) {
        this.ALPHABET = alphabet;
    }

    public String encrypt(String string, int key) {
        return encode(string, key);
    }

    public String decrypt(String string, int key) {
        return encode(string, -key);
    }

    public void bruteForce(String sourcePath) {
        BRUTEFORCE.writePossibleDecryptionsWithKeys(sourcePath);
    }

    public List<Character> getALPHABET() {
        return ALPHABET;
    }


    private String encode(String string, int key) {
        char[] charArray = string.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            for (Character character : ALPHABET) {
                if (charArray[i] == character) {
                    int newIndex = (ALPHABET.indexOf(character) + key + ALPHABET.size()) % ALPHABET.size();
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
            replaced = ALPHABET.get(newIndex);
            Character.toUpperCase(replaced);
        } else if (Character.isLowerCase(character)) {
            replaced = ALPHABET.get(newIndex);
            Character.toLowerCase(replaced);
        }
        return replaced;
    }

}
