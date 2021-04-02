package markus.wieland.games.elements;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Matrix<E> implements Iterable<E> {

    private final List<List<E>> matrixObject;
    private final int sizeX;
    private final int sizeY;

    public Matrix(int sizeX, int sizeY) {
        this.matrixObject = new ArrayList<>();

        for (int i = 0; i < sizeY; i++) {
            matrixObject.add(new ArrayList<>());
            for (int j = 0; j < sizeX; j++) {
                matrixObject.get(i).add(null);
            }
        }

        this.sizeX = sizeX;
        this.sizeY = sizeY;
    }

    public void set(int x, int y, E e) {
        check(x, y);
        this.matrixObject.get(y).set(x, e);
    }

    private void check(int x, int y) {
        if (x >= sizeX || y >= sizeY)
            throw new IllegalArgumentException(
                    "x: " + x + ", y: " + y + " isn't inside the matrix size. (" + sizeX + "," + sizeY + ")");

    }

    public E get(int x, int y) {
        check(x, y);
        return matrixObject.get(y).get(x);
    }

    public E get(Coordinate coordinate) {
        return get(coordinate.getX(), coordinate.getY());
    }


    @NonNull
    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private int currentX = 0;
            private int currentY = 0;

            @Override
            public boolean hasNext() {
                return (currentX < sizeX && currentY < sizeY);
            }

            @Override
            public E next() {
                E e = get(currentX, currentY);
                if (currentX == 8) {
                    currentX = 0;
                    currentY++;
                } else {
                    currentX++;
                }
                return e;
            }
        };
    }
}
