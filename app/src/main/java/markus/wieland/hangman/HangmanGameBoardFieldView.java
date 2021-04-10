package markus.wieland.hangman;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.util.TypedValue;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.button.MaterialButton;

import markus.wieland.games.elements.Coordinate;
import markus.wieland.games.game.grid.GridGameBoardFieldView;
import markus.wieland.games.game.view.GameStateField;

public class HangmanGameBoardFieldView extends MaterialButton implements GridGameBoardFieldView {

    private Character character;
    private HangmanGameBoardFieldState used;
    private Coordinate coordinate;

    public HangmanGameBoardFieldView(@NonNull Context context) {
        super(context);
    }

    public HangmanGameBoardFieldView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public HangmanGameBoardFieldView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public Character getCharacter() {
        return character;
    }

    public HangmanGameBoardFieldState getUsed() {
        return used;
    }

    public void update(){
        setText(character.toString());

        int color;

        switch (used) {
            case USED_WRONG:
                color = getContext().getColor(R.color.hangman_used_wrong);
                break;
            case USED_CORRECT:
                color = getContext().getColor(R.color.hangman_used_correct);
                break;
            default:
                TypedValue typedValue = new TypedValue();
                Resources.Theme theme = getContext().getTheme();
                theme.resolveAttribute(R.attr.hangmanGameBoardViewBackgroundColor, typedValue, true);
                color = typedValue.data;
                break;
        }

        setBackgroundTintList(ColorStateList.valueOf(color));
    }

    public void use(HangmanGameBoardFieldState hangmanGameStateField){
        this.used = hangmanGameStateField;
    }

    @Override
    public void load(GameStateField stateField) {
        HangmanGameStateField hangmanGameStateField = (HangmanGameStateField) stateField;
        this.coordinate = hangmanGameStateField.getCoordinate();
        this.character = hangmanGameStateField.getCharacter();
        this.used = hangmanGameStateField.getUsed();
    }

    public HangmanGameStateField getGameStateField(){
        return new HangmanGameStateField(getCoordinate(), character, used);
    }

    @Override
    public Coordinate getCoordinate() {
        return coordinate;
    }
}
