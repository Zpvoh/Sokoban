package Objects;

public abstract class MapObject {
    protected int x;
    protected int y;

    public MapObject(){

    }

    public MapObject(int x, int y){
        this.x=x;
        this.y=y;
    }


    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
}
