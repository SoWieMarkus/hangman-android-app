package markus.wieland.hangman;

import android.content.Context;

import androidx.core.os.ConfigurationCompat;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import markus.wieland.games.persistence.GameGenerator;

public class HangmanGenerator extends GameGenerator<HangmanGameState> {

    private static final List<String> WORDS = new ArrayList<>();

    private final Context context;
    private final Random random;

    public HangmanGenerator(Context context) {
        this.context = context;
        this.random = new Random();
    }

    @Override
    public HangmanGameState generate() {
        if (WORDS.isEmpty()) {
            loadWords();
        }
        String word;
        do {
            word = WORDS.get(random.nextInt(WORDS.size()));
        } while (!word.matches("[a-zA-Z]+"));

        return new HangmanGameState(new HangmanWord(word));
    }

    private void loadWords() {
        try {
            WORDS.clear();
            Locale current = ConfigurationCompat.getLocales(context.getResources().getConfiguration()).get(0);
            String fileName = current.getCountry().equalsIgnoreCase("de")
                    ? "words.txt"
                    : "words_de.txt";
            BufferedReader reader = new BufferedReader(new InputStreamReader(context.getAssets().open(fileName)));
            String word;
            while ((word = reader.readLine()) != null) {
                WORDS.add(word);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
