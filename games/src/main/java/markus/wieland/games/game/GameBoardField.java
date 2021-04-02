package markus.wieland.games.game;

import android.view.View;

public abstract class GameBoardField {

    public static final int FREE = -1;

    protected int value;
    protected final View view;

    public GameBoardField(View view) {
        this.view = view;
        this.value = FREE;
    }

    public boolean isEmpty() {
        return value == FREE;
    }

    public abstract void update();

    public void setValue(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
