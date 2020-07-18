package meet_eat.data.location;

public class CoordinateLocation implements Localizable {

    private Coordinate coordinate;

    public CoordinateLocation(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    @Override
    public Coordinate getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }
}