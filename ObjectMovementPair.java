import objects.MovableChecker;

public class ObjectMovementPair {
    private MovableChecker object;
    private int movement;

    public ObjectMovementPair(){

    }

    public ObjectMovementPair(MovableChecker object, int movement){
        this.object=object;
        this.movement=movement;
    }
}
