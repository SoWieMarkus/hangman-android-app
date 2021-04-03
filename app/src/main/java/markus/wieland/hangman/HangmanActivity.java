package markus.wieland.hangman;

import android.os.Bundle;
import android.os.PersistableBundle;

import androidx.annotation.Nullable;

import markus.wieland.games.GameActivity;
import markus.wieland.games.game.GameConfiguration;
import markus.wieland.games.game.GameEventListener;
import markus.wieland.games.game.Highscore;
import markus.wieland.games.persistence.GameGenerator;
import markus.wieland.games.persistence.GameSaver;
import markus.wieland.games.screen.EndScreen;
import markus.wieland.games.screen.StartScreen;
import markus.wieland.hangman.ui.screens.HangmanEndScreen;
import markus.wieland.hangman.ui.screens.HangmanStartScreen;

public class HangmanActivity extends GameActivity<Highscore, HangmanGameState, HangmanGameResult, Hangman> implements GameEventListener<HangmanGameResult> {

    public HangmanActivity() {
        super(R.layout.activity_hangman);
    }

    @Override
    public void onGameStart() {
        game.setEnableKeyboard(true);
    }

    @Override
    public void onGamePause() {
        // No need to pause hangman game
    }

    @Override
    protected StartScreen initializeStartScreen() {
        return new HangmanStartScreen(this);
    }

    @Override
    protected EndScreen initializeEndScreen() {
        return new HangmanEndScreen(this);
    }

    @Override
    protected GameGenerator<HangmanGameState> initializeGenerator(GameConfiguration configuration) {
        if (!(configuration instanceof HangmanConfiguration)) throw new IllegalArgumentException("Wrong configuration loaded. Was: "
                + configuration.getClass().getName() + " but expected: " + HangmanConfiguration.class.getName());
        HangmanConfiguration hangmanConfiguration = (HangmanConfiguration) configuration;
        if (hangmanConfiguration.getHangmanWord() == null) return new HangmanGenerator(this);
        initializeGame(new HangmanGameState(hangmanConfiguration.getHangmanWord()));
        return null;
    }

    @Override
    protected GameSaver<HangmanGameState, Highscore> initializeGameSaver() {
        return null;
    }

    @Override
    protected void initializeGame(HangmanGameState hangmanGameState) {
        game = new Hangman(findViewById(R.id.background),hangmanGameState, this);
        game.start();
    }
}