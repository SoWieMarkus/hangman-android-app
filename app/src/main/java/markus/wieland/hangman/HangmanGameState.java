package markus.wieland.hangman;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import markus.wieland.games.persistence.GameState;

public class HangmanGameState extends GameState implements Serializable {

    private final HangmanWord word;
    private final List<Guess> usedCharacters;

    public HangmanGameState(HangmanWord word) {
        this.word = word;
        this.usedCharacters = new ArrayList<>();
    }

    public HangmanGameState(HangmanWord word, List<Guess> usedCharacters) {
        this.word = word;
        this.usedCharacters = usedCharacters;
    }

    public HangmanWord getWord() {
        return word;
    }

    public List<Guess> getUsedCharacters() {
        return usedCharacters;
    }
}
