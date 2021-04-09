package markus.wieland.hangman;

import markus.wieland.games.elements.Coordinate;
import markus.wieland.games.game.grid.GridGameStateField;

public class HangmanGameStateField extends GridGameStateField {

    private final Character character;
    private final HangmanGameBoardFieldState used;

    public HangmanGameStateField(Coordinate coordinate, Character character, HangmanGameBoardFieldState used) {
        super(coordinate);
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
