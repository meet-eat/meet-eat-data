package meet_eat.data.location;

public class CityLocation implements Localizable {
    
    private static final String ERROR_MESSAGE_NOT_IMPLEMENTED = "This function is not implemented yet.";

    private final String city;

    public CityLocation(String city) {
        this.city = city;
    }

    public String getCity() {
        return city;
    }

    @Override
    public double getDistance(Localizable location) {
        // TODO Implement
        throw new UnsupportedOperationException(ERROR_MESSAGE_NOT_IMPLEMENTED);
    }

    @Override
    public Coordinate getCoordinate() {
        // TODO Implement
        throw new UnsupportedOperationException(ERROR_MESSAGE_NOT_IMPLEMENTED);
    }
}