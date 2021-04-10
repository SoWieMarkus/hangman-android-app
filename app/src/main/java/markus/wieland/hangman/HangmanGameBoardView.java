package markus.wieland.hangman;

import android.content.Context;
import android.content.res.Configuration;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;

import markus.wieland.games.elements.SerializableMatrix;
import markus.wieland.games.game.GameResult;
import markus.wieland.games.game.grid.GridGameBoardView;
import markus.wieland.games.persistence.GameState;
import markus.wieland.hangman.models.HangmanWord;

public class HangmanGameBoardView extends GridGameBoardView<HangmanGameBoardFieldView> implements View.OnClickListener {

    protected static final char[] CHARACTERS = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',};

    private ImageView imageViewHangmanProgress;
    private TextView textViewHangmanWord;

    public HangmanGameBoardView(@NonNull Context context) {
        super(context);
    }

    public HangmanGameBoardView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public HangmanGameBoardView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected int getSizeX() {
        return CHARACTERS.length;
    }

    public void updateHangmanWord(HangmanWord word) {
        textViewHangmanWord.setText(word.getWordWithSpaces());
    }

    public void updateHangmanImage() {

        boolean isDarkThemeOn = (getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK) == Configuration.UI_MODE_NIGHT_YES;
        String fileName = "h" + getAmountOfErrors();
        if (isDarkThemeOn)
            fileName += "_dark";

        Glide.with(getContext()).load(getContext().getResources().getIdentifier(fileName,
                "drawable",
                getContext().getPackageName())).into(imageViewHangmanProgress);
    }

    public SerializableMatrix<HangmanGameStateField> getGameState() {
        SerializableMatrix<HangmanGameStateField> hangmanGameStateField = new SerializableMatrix<>(getSizeX(), getSizeY());
        for (HangmanGameBoardFieldView view : matrix) {
            hangmanGameStateField.set(view.getCoordinate(), view.getGameStateField());
        }
        return hangmanGameStateField;
    }

    public void enableKeyboard(boolean enable) {
        for (HangmanGameBoardFieldView view : matrix) {
            view.setEnabled(enable);
        }
    }

    public int getAmountOfErrors() {
        int amountOfErrors = 0;
        for (HangmanGameBoardFieldView view : matrix) {
            if (view.getUsed().equals(HangmanGameBoardFieldState.USED_WRONG)) amountOfErrors++;
        }
        return amountOfErrors;
    }

    @Override
    protected int getSizeY() {
        return 1;
    }

    @Override
    protected void initializeFields() {

        imageViewHangmanProgress = findViewById(R.id.hangman_image);
        textViewHangmanWord = findViewById(R.id.hangman_word);
    }

    @Override
    protected GameResult checkGameForFinish() {
        return null;
    }

    @Override
    protected void loadGameState(GameState gameState) {
        for (int i = 0; i < CHARACTERS.length; i++) {
            HangmanGameBoardFieldView fieldView = findViewById(getContext()
                    .getResources()
                    .getIdentifier(String.valueOf(CHARACTERS[i]).toLowerCase(), "id", getContext().getPackageName()));
            fieldView.setOnClickListener(this);
            matrix.set(i, 0, fieldView);
        }

        HangmanGameState hangmanGameState = (HangmanGameState) gameState;
        for (HangmanGameStateField stateField : hangmanGameState) {
            matrix.get(stateField.getCoordinate()).load(stateField);
        }
    }

    public void update() {
        for (HangmanGameBoardFieldView view : matrix) {
            view.update();
        }
    }

    @Override
    public void onClick(View view) {
        HangmanGameBoardFieldView hangmanGameBoardFieldView = (HangmanGameBoardFieldView) view;
        if (hangmanGameBoardFieldView.getUsed().equals(HangmanGameBoardFieldState.NOT_USED))
            ((HangmanGameBoardInteractionListener) gameBoardInteractionListener).onClick(hangmanGameBoardFieldView);
    }
}
