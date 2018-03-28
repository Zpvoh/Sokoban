package Objects;

public class People extends MovableChecker implements Movable {
    public static final int W=0;
    public static final int A=1;
    public static final int S=2;
    public static final int D=3;

    public People(int x, int y) {
        super(x, y);
    }

    @Override
    public void move(int direction) throws DirectionException {
        switch (direction){
            case W:
                x--;
                break;
            case A:
                y--;
                break;
            case S:
                x++;
                break;
            case D:
                y++;
                break;
            default:
                throw new DirectionException();
        }
    }

    @Override
    public boolean push(Box object, int direction) throws DirectionException {

        if(related(object, direction)) {
            switch (direction) {
                case W:
                    object.setX(object.getX() - 1);
                    return true;
                case A:
                    object.setY(object.getY() - 1);
                    return true;
                case S:
                    object.setX(object.getX() + 1);
                    return true;
                case D:
                    object.setY(object.getY() + 1);
                    return true;
                default:
                    throw new DirectionException();
            }
        }

        return false;
    }

    @Override
    public boolean related(Box object, int direction) throws DirectionException {
        switch (direction) {
            case W:
                if(object.getX()==x-1 && object.getY()==y) {
                    return true;
                }
                break;
            case A:
                if(object.getY()==y-1 && object.getX()==x) {
                    return true;
                }
                break;
            case S:
                if(object.getX()==x+1 && object.getY()==y) {
                    return true;
                }
                break;
            case D:
                if(object.getY()==y+1 && object.getX()==x) {
                    return true;
                }
                break;
            default:
                throw new DirectionException();
        }
        return false;
    }
}
