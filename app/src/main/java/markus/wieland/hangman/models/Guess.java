package markus.wieland.hangman.models;

import java.io.Serializable;

import markus.wieland.hangman.HangmanGameBoardFieldState;

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
