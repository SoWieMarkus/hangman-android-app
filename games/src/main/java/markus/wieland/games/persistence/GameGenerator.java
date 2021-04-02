package markus.wieland.games.persistence;

public abstract class GameGenerator<S extends GameState> {

    public abstract S generate();

}
