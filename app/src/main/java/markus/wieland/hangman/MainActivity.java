package markus.wieland.hangman;

import android.widget.Toast;

import markus.wieland.defaultappelements.uielements.activities.DefaultActivity;
import markus.wieland.games.game.GameEventListener;

public class MainActivity extends DefaultActivity implements GameEventListener<HangmanGameResult> {

    private Hangman hangman;

    public MainActivity() {
        super(R.layout.activity_main);
    }

    @Override
    public void bindViews() {

    }

    @Override
    public void initializeViews() {

    }

    @Override
    public void execute() {
        hangman = new Hangman(findViewById(R.id.background), new HangmanGenerator(this).generate(), this);
    }

    @Override
    public void onGameStart() {

    }

    @Override
    public void onGamePause() {

    }

    @Override
    public void onGameFinish(HangmanGameResult hangmanGameResult) {
        Toast.makeText(this, "" + hangmanGameResult.isWin(), Toast.LENGTH_SHORT).show();
    }
}