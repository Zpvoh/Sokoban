package Objects;

public class DirectionException extends Exception {
    @Override
    public String getMessage() {
        return "Wrong direction";
    }
}
