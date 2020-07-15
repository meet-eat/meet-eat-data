package meet_eat.data.location;

public class CoordinateLocation implements Localizable {
    
    private static final String ERROR_MESSAGE_NOT_IMPLEMENTED = "This function is not implemented yet.";

    private final Coordinate coordinate;

    public CoordinateLocation(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    @Override
    public double getDistance(Localizable location) {
        // TODO Implement
        throw new UnsupportedOperationException(ERROR_MESSAGE_NOT_IMPLEMENTED);
    }
}