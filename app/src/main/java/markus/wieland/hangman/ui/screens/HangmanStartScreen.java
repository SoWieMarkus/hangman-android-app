package markus.wieland.hangman.ui.screens;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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
        String word = editTextWordInput.getText().toString().trim();
        if (word.isEmpty()){
            Toast.makeText(getContext(), getContext().getString(R.string.hangman_error_empty_word), Toast.LENGTH_SHORT).show();
            return;
        }
        if (!HangmanGenerator.doesMatchPattern(word)){
            Toast.makeText(getContext(), getContext().getString(R.string.hangman_error_wrong_pattern), Toast.LENGTH_SHORT).show();
            return;
        }
        randomWord = false;
        InputMethodManager imm = (InputMethodManager)getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getWindowToken(), 0);
        close();
    }

    @Override
    protected void onBuild() {
        setBackgroundColor(getContext().getColor(R.color.start));

        Button buttonStartWithCustomWord = findViewById(R.id.activity_hangman_start_screen_start);
        Button buttonStartWithRandomWord = findViewById(R.id.activity_hangman_start_screen_random_word);

        this.editTextWordInput = findViewById(R.id.activity_hangman_start_screen_enter_word);
        this.randomWord = false;

        buttonStartWithCustomWord.setOnClickListener(this);
        buttonStartWithRandomWord.setOnClickListener(v -> randomWord());
    }
}
