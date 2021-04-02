package markus.wieland.hangman;

import android.graphics.Color;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;

import markus.wieland.games.game.GameBoardField;

public class HangmanGameBoardField extends GameBoardField implements View.OnClickListener {

    private final Character character;
    private final HangmanGameBoardInteractionListener listener;

    private HangmanGameBoardFieldState used;

    public HangmanGameBoardField(@NonNull HangmanGameBoardInteractionListener listener, char character, @NonNull View view) {
        super(view);
        this.character = character;
        this.listener = listener;
        this.used = HangmanGameBoardFieldState.NOT_USED;

        view.setOnClickListener(this);
    }

    @Override
    public void update() {
        Button gameBoardField = (Button) view;
        gameBoardField.setText(character.toString());

        switch (used) {
            case USED_WRONG:
                gameBoardField.setBackgroundColor(Color.parseColor("#FF0000"));
                break;
            case USED_CORRECT:
                gameBoardField.setBackgroundColor(Color.parseColor("#00FF00"));
                break;
            default:
                gameBoardField.setBackgroundColor(Color.parseColor("#F1F1F1"));
                break;
        }

    }

    public void use(HangmanGameBoardFieldState used) {
        this.used = used;
    }

    public Button getView() {
        return (Button) view;
    }

    public Character getCharacter() {
        return character;
    }

    @Override
    public void onClick(View v) {
        if (used == HangmanGameBoardFieldState.NOT_USED)
            listener.onClick(HangmanGameBoardField.this);
    }
}
