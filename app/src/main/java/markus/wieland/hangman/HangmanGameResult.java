package markus.wieland.hangman;

import markus.wieland.games.game.GameResult;

public class HangmanGameResult implements GameResult {

    private final boolean win;
    private final String originalWord;

    public HangmanGameResult(boolean win, String originalWord) {
        this.win = win;
        this.originalWord = originalWord;
    }

    public boolean isWin() {
        return win;
    }

    public String getOriginalWord() {
        return originalWord;
    }
}
