package meet_eat.data.location;

public class PostCodeLocation implements Localizable {

    private static final String ERROR_MESSAGE_NOT_IMPLEMENTED = "This function is not implemented yet.";

    private final String postCode;

    public PostCodeLocation(String postCode) {
        this.postCode = postCode;
    }

    public String getPostCode() {
        return postCode;
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