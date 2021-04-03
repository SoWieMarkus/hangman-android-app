package markus.wieland.hangman.ui.screens;

import android.app.Activity;
import android.view.View;
import android.widget.TextView;

import markus.wieland.games.screen.EndScreen;
import markus.wieland.hangman.HangmanGameResult;
import markus.wieland.hangman.R;

public class HangmanEndScreen extends EndScreen implements View.OnClickListener {

    private final TextView tvWinMessage;
    private final TextView tvWordWas;

    public HangmanEndScreen(Activity activity) {
        super(activity.findViewById(R.id.activity_hangman_end_screen));
        tvWinMessage = activity.findViewById(R.id.word_win);
        tvWordWas = activity.findViewById(R.id.word_was);

        activity.findViewById(R.id.again).setOnClickListener(this);
    }

    @Override
    protected void update() {
        if (gameResult == null) throw new IllegalStateException("GameResult can't be null.");
        if (!(gameResult instanceof HangmanGameResult))
            throw new IllegalArgumentException("Wrong GameResult class.");

        HangmanGameResult hangmanGameResult = (HangmanGameResult) gameResult;
        tvWinMessage.setText(hangmanGameResult.isWin()
                ? R.string.hangman_win
                : R.string.hangman_game_over);
        tvWordWas.setText(hangmanGameResult.getOriginalWord());
    }

    @Override
    public void onClick(View v) {

    }
}
