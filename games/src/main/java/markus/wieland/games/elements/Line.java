package markus.wieland.games.elements;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Line implements Iterable<Coordinate>{

    private final List<Coordinate> coordinates;

    public Line() {
        this.coordinates = new ArrayList<>();
    }

    public void add(Coordinate coordinate) {
        this.coordinates.add(coordinate);
    }

    public Coordinate getCoordinate(int index) {
        return coordinates.get(index);
    }

    public int size() {
        return coordinates.size();
    }

    @NonNull
    @Override
    public Iterator<Coordinate> iterator() {
        return coordinates.iterator();
    }
}
