package markus.wieland.hangman;

import java.util.ArrayList;
import java.util.List;

import markus.wieland.games.persistence.GameState;

public class HangmanGameState extends GameState {

    private final String word;
    private final List<Character> usedCharacters;

    public HangmanGameState(String word) {
        this.word = word;
        this.usedCharacters = new ArrayList<>();
    }

    public HangmanGameState(String word, List<Character> usedCharacters) {
        this.word = word;
        this.usedCharacters = usedCharacters;
    }

    public String getWord() {
        return word;
    }

    public List<Character> getUsedCharacters() {
        return usedCharacters;
    }
}
