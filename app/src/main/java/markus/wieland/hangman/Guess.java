package markus.wieland.hangman;

import java.io.Serializable;

public class Guess implements Serializable {
    private final Character character;
    private final HangmanGameBoardFieldState used;

    public Guess(Character character, HangmanGameBoardFieldState used) {
        this.character = character;
        this.used = used;
    }

    public Character getCharacter() {
        return character;
    }

    public HangmanGameBoardFieldState getUsed() {
        return used;
    }
}
