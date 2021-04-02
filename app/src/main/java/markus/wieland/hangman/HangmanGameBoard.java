package markus.wieland.hangman;

import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import markus.wieland.games.game.GameBoard;
import markus.wieland.games.game.GameBoardInteractionListener;

public class HangmanGameBoard extends GameBoard<ConstraintLayout, HangmanGameBoardField, HangmanGameState> implements HangmanGameBoardInteractionListener {

    private ImageView imageView;
    private TextView textView;

    private static final char[] CHARACTERS = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',};

    public HangmanGameBoard(ConstraintLayout linearLayout, GameBoardInteractionListener<HangmanGameBoardField> gameBoardInteractionListener) {
        super(1, 26, linearLayout, gameBoardInteractionListener);
    }

    @Override
    protected void initializeFields() {
        for (int i = 0; i < 26; i++) {
            matrix.set(1,i, new HangmanGameBoardField(this,CHARACTERS[i],findViewById(getIdByString(layout.getContext(), String.valueOf(CHARACTERS[i])))));
        }
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
            for (Guess guess : gameState.getUsedCharacters()) {
                if (field.getCharacter().equals(guess.getCharacter())) {
                    field.use(guess.getUsed());
                    break;
                }
            }
        }
    }

    @Override
    public void onClick(HangmanGameBoardField hangmanGameBoardField) {
        ((HangmanGameBoardInteractionListener)gameBoardInteractionListener).onClick(hangmanGameBoardField);
    }

    public void showHangman(int amountErrors){

    }
}
