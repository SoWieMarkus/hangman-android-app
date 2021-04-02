package markus.wieland.hangman;

import android.widget.LinearLayout;

import markus.wieland.games.game.GameBoard;
import markus.wieland.games.game.GameBoardInteractionListener;

public class HangmanGameBoard extends GameBoard<LinearLayout, HangmanGameBoardField, HangmanGameState> implements HangmanGameBoardInteractionListener {

    public HangmanGameBoard(LinearLayout linearLayout, GameBoardInteractionListener<HangmanGameBoardField> gameBoardInteractionListener) {
        super(1, 26, linearLayout, gameBoardInteractionListener);
    }

    @Override
    protected void initializeLines() {

    }

    @Override
    protected void initializeFields() {

    }

    @Override
    public boolean checkForWin(int player) {
        return false;
    }

    @Override
    public boolean checkForTie() {
        return false;
    }

    @Override
    protected void loadGameState(HangmanGameState gameState) {
        for (HangmanGameBoardField field : matrix) {
        }
    }

    @Override
    public void onClick(HangmanGameBoardField hangmanGameBoardField) {
        ((HangmanGameBoardInteractionListener)gameBoardInteractionListener).onClick(hangmanGameBoardField);
    }
}
