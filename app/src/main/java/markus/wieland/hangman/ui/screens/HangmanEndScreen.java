package markus.wieland.hangman.ui.screens;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import markus.wieland.games.screen.interact_listener.EndScreenInteractListener;
import markus.wieland.games.screen.view.EndScreenView;
import markus.wieland.hangman.HangmanGameResult;
import markus.wieland.hangman.R;

public class HangmanEndScreen extends EndScreenView implements View.OnClickListener {

    private TextView tvWinMessage;
    private TextView tvWordWas;

    public HangmanEndScreen(@NonNull Context context) {
        super(context);
    }

    public HangmanEndScreen(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public HangmanEndScreen(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    public void onClick(View v) {
        ((EndScreenInteractListener) screenInteractListener).onClose();
    }

    @Override
    protected void onNewGameResult() {
        if (gameResult == null) throw new IllegalStateException("GameResult can't be null.");
        if (!(gameResult instanceof HangmanGameResult))
            throw new IllegalArgumentException("Wrong GameResult class.");

        HangmanGameResult hangmanGameResult = (HangmanGameResult) gameResult;
        tvWinMessage.setText(hangmanGameResult.isWin()
                ? R.string.hangman_win
                : R.string.hangman_game_over);
        setBackgroundColor(hangmanGameResult.isWin()
                ? Color.parseColor("#BF4CAF50")
                : Color.parseColor("#BFFF0000"));
        tvWordWas.setText(hangmanGameResult.getOriginalWord());
    }

    @Override
    protected void onBuild() {
        tvWinMessage = findViewById(R.id.activity_hangman_end_screen_word_win);
        tvWordWas = findViewById(R.id.activity_hangman_end_screen_word_was);

        findViewById(R.id.again).setOnClickListener(this);
    }
}
