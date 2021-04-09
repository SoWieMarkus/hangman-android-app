package markus.wieland.hangman;

import markus.wieland.games.elements.Coordinate;
import markus.wieland.games.elements.SerializableMatrix;
import markus.wieland.games.game.grid.GridGameState;
import markus.wieland.hangman.models.HangmanWord;

public class HangmanGameState extends GridGameState<HangmanGameStateField> {

    private final HangmanWord word;

    public HangmanGameState(SerializableMatrix<HangmanGameStateField> matrix, HangmanWord word) {
        super(matrix);
        this.word = word;
    }

    public HangmanGameState(HangmanWord word) {
        super(getDefaultMatrix());
        this.word = word;
    }

    private static SerializableMatrix<HangmanGameStateField> getDefaultMatrix() {
        SerializableMatrix<HangmanGameStateField> hangmanGameStateField = new SerializableMatrix<>(26, 1);
        for (int i = 0; i < HangmanGameBoardView.CHARACTERS.length; i++) {
            hangmanGameStateField.set(i, 0, new HangmanGameStateField(new Coordinate(i, 0), HangmanGameBoardView.CHARACTERS[i], HangmanGameBoardFieldState.NOT_USED));
        }
        return hangmanGameStateField;
    }

    public HangmanWord getWord() {
        return word;
    }
}
