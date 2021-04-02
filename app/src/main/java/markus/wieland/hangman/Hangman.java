package markus.wieland.hangman;

import java.util.List;

import markus.wieland.games.game.Game;
import markus.wieland.games.game.GameEventListener;

public class Hangman extends Game<HangmanGameState> implements HangmanGameBoardInteractionListener {

    private final HangmanWord word;
    private final List<Guess> used;
    private final HangmanGameBoard hangmanGameBoard;

    public Hangman(HangmanGameState hangmanGameState, GameEventListener gameEventListener) {
        super(gameEventListener);
        this.word = hangmanGameState.getWord();
        this.used = hangmanGameState.getUsedCharacters();
        hangmanGameBoard = new HangmanGameBoard(null, this);
        hangmanGameBoard.loadGameState(hangmanGameState);
    }

    @Override
    public HangmanGameState getGameState() {
        return new HangmanGameState(word, used);
    }

    @Override
    public void onClick(HangmanGameBoardField hangmanGameBoardField) {
        HangmanGameBoardFieldState state = word.checkLetter(hangmanGameBoardField.getCharacter());
        used.add(new Guess(hangmanGameBoardField.getCharacter(), state));
        hangmanGameBoardField.use(state);
        hangmanGameBoardField.update();
        hangmanGameBoard.showHangman(getAmountErrors());
    }

    public void setEnableKeyboard(boolean enable) {
        for (HangmanGameBoardField field : hangmanGameBoard) {
            field.getView().setEnabled(enable);
        }
    }

    public int getAmountErrors() {
        int errors = 0;
        for (Guess guess : used) {
            if (guess.getUsed().equals(HangmanGameBoardFieldState.USED_WRONG)) errors++;
        }
        return errors;
    }
}
