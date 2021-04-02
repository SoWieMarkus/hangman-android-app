package markus.wieland.hangman;

import java.util.List;

import markus.wieland.games.game.Game;
import markus.wieland.games.game.GameEventListener;

public class Hangman extends Game<HangmanGameState> implements HangmanGameBoardInteractionListener {

    private final String word;
    private final List<Character> used;
    private final HangmanGameBoard hangmanGameBoard;

    public Hangman(HangmanGameState hangmanGameState, GameEventListener gameEventListener) {
        super(gameEventListener);
        this.word = hangmanGameState.getWord().toUpperCase();
        this.used = hangmanGameState.getUsedCharacters();
        hangmanGameBoard = new HangmanGameBoard(null, this);
    }

    @Override
    public HangmanGameState getGameState() {
        return new HangmanGameState(word, used);
    }

    @Override
    public void onClick(HangmanGameBoardField hangmanGameBoardField) {
        used.add(hangmanGameBoardField.getCharacter());

    }
}
