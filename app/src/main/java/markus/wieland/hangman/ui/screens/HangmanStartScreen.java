package markus.wieland.hangman.ui.screens;

import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import markus.wieland.games.screen.StartScreen;
import markus.wieland.hangman.HangmanConfiguration;
import markus.wieland.hangman.HangmanGenerator;
import markus.wieland.hangman.R;
import markus.wieland.hangman.models.HangmanWord;

public class HangmanStartScreen extends StartScreen implements View.OnClickListener {

    private final EditText editTextWordInput;

    private boolean randomWord;

    public HangmanStartScreen(Activity activity) {
        super(activity.findViewById(R.id.activity_hangman_start_screen));
        Button buttonStartWithCustomWord = activity.findViewById(R.id.activity_hangman_start_screen_start);
        Button buttonStartWithRandomWord = activity.findViewById(R.id.activity_hangman_start_screen_random_word);

        this.editTextWordInput = activity.findViewById(R.id.activity_hangman_start_screen_enter_word);
        this.randomWord = false;

        buttonStartWithCustomWord.setOnClickListener(this);
        buttonStartWithRandomWord.setOnClickListener(v -> randomWord());
    }

    @Override
    protected HangmanConfiguration getConfiguration() {
        if (randomWord) return new HangmanConfiguration(null);
        return new HangmanConfiguration(new HangmanWord(editTextWordInput.getText().toString()));
    }

    public void randomWord() {
        randomWord = true;
        close();
    }

    @Override
    public void onClick(View v) {
        String word = editTextWordInput.getText().toString();

        //TODO ERROR Messages
        if (word.isEmpty()) return;
        if (!HangmanGenerator.doesMatchPattern(word)) return;
        randomWord = false;
        close();
    }
}
