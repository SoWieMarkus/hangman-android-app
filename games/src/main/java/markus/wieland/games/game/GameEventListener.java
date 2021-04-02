package markus.wieland.games.game;

public interface GameEventListener {

    void onGameStart();
    void onGamePause();
    void onGameFinish();
}
