package markus.wieland.games.game;

import android.content.Context;
import android.view.ViewGroup;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import markus.wieland.games.persistence.GameState;
import markus.wieland.games.elements.Line;
import markus.wieland.games.elements.Matrix;

public abstract class GameBoard<C extends ViewGroup, E extends GameBoardField, S extends GameState> implements Iterable<E> {

    protected final GameBoardInteractionListener<E> gameBoardInteractionListener;
    protected final Matrix<E> matrix;
    protected final List<Line> lines;
    protected final C layout;

    public GameBoard(int sizeX, int sizeY, C c, GameBoardInteractionListener<E> gameBoardInteractionListener) {
        this.layout = c;
        if (sizeX < 1) throw new IllegalArgumentException("sizeX must be bigger or equal to 1.");
        if (sizeY < 1) throw new IllegalArgumentException("sizeY must be bigger or equal to 1.");
        this.matrix = new Matrix<>(sizeX, sizeY);
        this.gameBoardInteractionListener = gameBoardInteractionListener;
        lines = new ArrayList<>();

        initializeLines();
        initializeFields();
    }

    protected void initializeLines(){}

    protected abstract void initializeFields();

    public abstract boolean checkForWin(int player);

    public abstract boolean checkForTie();

    protected abstract void loadGameState(S gameState);

    public Matrix<E> getMatrix() {
        return matrix;
    }

    @NonNull
    @Override
    public Iterator<E> iterator() {
        return matrix.iterator();
    }

    @IdRes
    public int getIdByString(Context context, String id){
        return context.getResources().getIdentifier(id, "id", context.getPackageName());
    }
}
