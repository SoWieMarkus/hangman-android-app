package markus.wieland.hangman;

import markus.wieland.games.game.Game;
import markus.wieland.games.game.GameEventListener;
import markus.wieland.hangman.models.HangmanWord;

public class Hangman extends Game<HangmanGameState, HangmanGameResult> implements HangmanGameBoardInteractionListener {

    private final HangmanWord word;
    private final HangmanGameBoardView hangmanGameBoard;

    public Hangman(HangmanGameBoardView hangmanGameBoard, HangmanGameState hangmanGameState, GameEventListener<HangmanGameResult> gameEventListener) {
        super(gameEventListener);
        this.word = hangmanGameState.getWord();
        this.hangmanGameBoard = hangmanGameBoard;
        this.hangmanGameBoard.setGameBoardInteractionListener(this);
        this.hangmanGameBoard.loadGameState(hangmanGameState);
        this.hangmanGameBoard.updateHangmanWord(word);
        this.hangmanGameBoard.updateHangmanImage();
        this.hangmanGameBoard.update();
    }

    @Override
    public HangmanGameState getGameState() {
        return new HangmanGameState(hangmanGameBoard.getGameState(), word);
    }

    @Override
    public HangmanGameResult getResult() {
        return checkForFinish();
    }

    public HangmanGameResult checkForFinish() {
        if (hangmanGameBoard.getAmountOfErrors() >= 11)
            return new HangmanGameResult(false, word.getOriginalWord());
        else if (word.isCompleted())
            return new HangmanGameResult(true, word.getOriginalWord());
        return null;
    }

    public void setEnableKeyboard(boolean enable) {
        hangmanGameBoard.enableKeyboard(enable);
    }

    @Override
    public void onClick(HangmanGameBoardFieldView hangmanGameBoardField) {
        HangmanGameBoardFieldState state = word.checkLetter(hangmanGameBoardField.getCharacter());
        hangmanGameBoardField.use(state);
        hangmanGameBoardField.update();
        hangmanGameBoard.updateHangmanWord(word);
        hangmanGameBoard.updateHangmanImage();

        HangmanGameResult result = getResult();
        if (result != null)
            finish(result);
    }
}
