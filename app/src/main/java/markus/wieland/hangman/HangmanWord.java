package markus.wieland.hangman;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class HangmanWord implements Serializable {

    private final String word;
    private final String originalWord;
    private final List<Character> correctChars;

    public HangmanWord(String word) {
        this.word = word.toUpperCase();
        this.originalWord = word;
        this.correctChars = new ArrayList<>();
    }

    public String getOriginalWord() {
        return originalWord;
    }

    public HangmanGameBoardFieldState checkLetter(char letter) {
        if (word.contains(String.valueOf(letter))) {
            correctChars.add(letter);
        }
        return word.contains(String.valueOf(letter))
                ? HangmanGameBoardFieldState.USED_CORRECT
                : HangmanGameBoardFieldState.USED_WRONG;
    }

    public String getWordWithSpaces() {
        char[] characters = word.toCharArray();
        StringBuilder newWord = new StringBuilder();
        for (int i = 0; i < characters.length; i++) {
            newWord.append(correctChars.contains(characters[i]) ? characters[i] : "_");
            if (i != characters.length - 1) newWord.append(" ");
        }
        return newWord.toString();
    }


    public boolean isCompleted() {
        char[] characters = word.toCharArray();
        for (Character character : characters) {
            if (!correctChars.contains(character)) return false;
        }
        return true;
    }

}
