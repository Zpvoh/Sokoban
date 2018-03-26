package objects;

public interface Movable {
    public void move(int direction) throws DirectionException;
    public boolean push(Box object, int direction) throws DirectionException;
    public boolean related(Box object, int direction) throws DirectionException;
}
