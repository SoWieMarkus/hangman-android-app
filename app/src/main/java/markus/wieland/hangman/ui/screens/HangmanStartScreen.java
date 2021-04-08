package markus.wieland.hangman.ui.screens;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import markus.wieland.games.screen.view.StartScreenView;
import markus.wieland.hangman.HangmanConfiguration;
import markus.wieland.hangman.HangmanGenerator;
import markus.wieland.hangman.R;
import markus.wieland.hangman.models.HangmanWord;

public class HangmanStartScreen extends StartScreenView implements View.OnClickListener {

    private EditText editTextWordInput;

    private boolean randomWord;

    public HangmanStartScreen(@NonNull Context context) {
        super(context);
    }

    public HangmanStartScreen(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public HangmanStartScreen(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
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

    @Override
    protected void onBuild() {
        Button buttonStartWithCustomWord = findViewById(R.id.activity_hangman_start_screen_start);
        Button buttonStartWithRandomWord = findViewById(R.id.activity_hangman_start_screen_random_word);

        this.editTextWordInput = findViewById(R.id.activity_hangman_start_screen_enter_word);
        this.randomWord = false;

        buttonStartWithCustomWord.setOnClickListener(this);
        buttonStartWithRandomWord.setOnClickListener(v -> randomWord());
    }
}
