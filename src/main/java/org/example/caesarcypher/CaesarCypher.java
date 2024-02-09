package org.example.caesarcypher;

import org.example.alphabets.Alphabets;

import java.util.ArrayList;
import java.util.List;

public class CaesarCypher {
    public String Encrypt(String string, int key) {
        char[] charArray = string.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            List<Character> alphabet = chooseAlphabet(charArray[i]);
            for (Character character : alphabet) {
                if (charArray[i] == character) {
                    int newIndex = (alphabet.indexOf(character) + key) % 26;
                    charArray[i] = alphabet.get(newIndex);
                    break;
                }
            }
        }
        return String.valueOf(charArray);
    }

    public String Decrypt(String string, int key) {
        char[] charArray = string.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            List<Character> alphabet = chooseAlphabet(charArray[i]);
            for (Character character : alphabet) {
                if (charArray[i] == character) {
                    int newIndex = (alphabet.indexOf(character) - key + 26) % 26;
                    charArray[i] = alphabet.get(newIndex);
                    break;
                }
            }
        }
        return String.valueOf(charArray);
    }

    public List<Character> chooseAlphabet(char character){
        ArrayList<Character> alphabet;
        if(Character.isUpperCase(character)) {
            alphabet= new ArrayList<>(Alphabets.ALPHABET_UPPER);
        }
        else{
            alphabet= new ArrayList<>(Alphabets.ALPHABET_LOWER);
        }
        return alphabet;
    }
}
