package markus.wieland.hangman;

import java.io.Serializable;

public class HangmanWord implements Serializable {

    private final String word;

    public HangmanWord(String word) {
        this.word = word.toUpperCase();
    }

    public String getOriginalWord() {
        return word;
    }

    public HangmanGameBoardFieldState checkLetter(char letter) {
        return word.contains(String.valueOf(letter)) ? HangmanGameBoardFieldState.USED_CORRECT : HangmanGameBoardFieldState.USED_WRONG;
    }

    public String getWordAsSpaces(){
        char[] characters = word.toCharArray();
        StringBuilder newWord = new StringBuilder();
        for (int i = 0; i < characters.length; i++) {
            if (i == characters.length - 1) {
                newWord.append("_");
            } else {
                newWord.append("_").append(" ");
            }
        }
        return newWord.toString();
    }

}
