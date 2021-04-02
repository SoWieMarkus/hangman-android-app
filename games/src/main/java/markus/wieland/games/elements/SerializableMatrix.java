package markus.wieland.games.elements;

import java.io.Serializable;

public class SerializableMatrix<E extends Serializable> extends Matrix<E> implements Serializable {

    public SerializableMatrix(int sizeX, int sizeY) {
        super(sizeX, sizeY);
    }

}
