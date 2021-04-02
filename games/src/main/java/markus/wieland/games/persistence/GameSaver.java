package markus.wieland.games.persistence;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import markus.wieland.games.game.Highscore;

public class GameSaver<S extends GameState, H extends Highscore> {

    protected final String key;
    protected final String valueHighscore;
    protected final String valueState;

    private final Class<S> sClass;
    private final Class<H> hClass;
    private final SharedPreferences sharedPreferences;
    private final Gson gson;

    public GameSaver(Class<S> sClass, Class<H> hClass, String key, String valueState, String valueHighscore, Context context) {
        this.key = key;
        this.sClass = sClass;
        this.hClass = hClass;
        this.valueState = valueState;
        this.valueHighscore = valueHighscore;
        gson = new Gson();
        sharedPreferences = context.getSharedPreferences(key, Context.MODE_PRIVATE);
    }

    public GameSaver(Class<S> sClass, Class<H> hClass, Context context) {
        this(sClass, hClass, "markus.wieland.games.KEY", "markus.wieland.games.VALUE", "markus.wieland.games.HIGHSCORE", context);
    }

    public S getGameState() {
        String gameState = sharedPreferences.getString(valueState, null);
        if (gameState == null) return null;
        return gson.fromJson(gameState, sClass);
    }

    public H getHighScore() {
        String gameState = sharedPreferences.getString(valueHighscore, null);
        if (gameState == null) return null;
        return gson.fromJson(gameState, hClass);
    }


    public void delete() {
        storeString(valueState, "");
    }

    public void save(S s, H h) {
        storeString(valueState, gson.toJson(s));
        storeString(valueHighscore, gson.toJson(h));
    }

    private void storeString(String key, String string) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, string);
        editor.apply();
    }

}
