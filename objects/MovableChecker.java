package objects;

import static objects.People.*;

public class MovableChecker extends MapObject {
    MovableChecker(){

    }

    MovableChecker(int x, int y){
        super(x, y);
    }

    public boolean isWallMovable(Map map, int direction) throws DirectionException {

        for (Wall wall : map.walls) {
            if(!singleMovable(wall, direction))
                return false;
        }

        return true;
    }

    public boolean isMovable(Map map, int direction) throws DirectionException {
        for (Wall wall : map.walls) {
            if(!singleMovable(wall, direction))
                return false;
        }

        for(Box box: map.boxes){
            if(!singleMovable(box, direction))
                return false;
        }

        return true;
    }

    public boolean singleMovable(MapObject object, int direction) throws DirectionException {
        switch (direction) {
            case W:
                if (x - 1 == object.x && y == object.y) {
                    return false;
                }
                break;
            case A:
                if (y - 1 == object.y && x == object.x) {
                    return false;
                }
                break;
            case S:
                if (x + 1 == object.x && y == object.y) {
                    return false;
                }
                break;
            case D:
                if (y + 1 == object.y && x == object.x) {
                    return false;
                }
                break;
            default:
                throw new DirectionException();
        }

        return true;
    }
}
