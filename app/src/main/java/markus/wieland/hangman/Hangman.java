package markus.wieland.hangman;

import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.List;

import markus.wieland.games.game.Game;
import markus.wieland.games.game.GameEventListener;

public class Hangman extends Game<HangmanGameState, HangmanGameResult> implements HangmanGameBoardInteractionListener {

    private final HangmanWord word;
    private final List<Guess> used;
    private final HangmanGameBoard hangmanGameBoard;

    public Hangman(ConstraintLayout constraintLayout, HangmanGameState hangmanGameState, GameEventListener<HangmanGameResult> gameEventListener) {
        super(gameEventListener);
        this.word = hangmanGameState.getWord();
        this.used = hangmanGameState.getUsedCharacters();
        hangmanGameBoard = new HangmanGameBoard(constraintLayout, this);
        hangmanGameBoard.loadGameState(hangmanGameState);
        hangmanGameBoard.update(getAmountErrors(), word);
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
        hangmanGameBoard.update(getAmountErrors(), word);

        HangmanGameResult result = checkForFinish();
        if (result != null)
            gameEventListener.onGameFinish(result);
    }

    public HangmanGameResult checkForFinish(){
        if (getAmountErrors() >= 11)
            return new HangmanGameResult(false, word.getOriginalWord());
        else if (word.isCompleted())
            return new HangmanGameResult(true, word.getOriginalWord());
        return null;
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
