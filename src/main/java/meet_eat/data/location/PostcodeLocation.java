package meet_eat.data.location;

import meet_eat.data.location.service.Geocoding;

import java.util.Objects;

public class PostcodeLocation implements Localizable {

    private static final String UNLOCALIZABLE_POSTCODE = "The given postcode is not localizable.";

    private String postcode;

    public PostcodeLocation(String postcode) {
        this.postcode = postcode;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    @Override
    public Coordinate getCoordinate() throws UnlocalizableException {
        Coordinate coordinate = Geocoding.getCoordinateFromPostcode(postcode);
        if (Objects.isNull(coordinate)) {
            throw new UnlocalizableException(UNLOCALIZABLE_POSTCODE);
        }
        return coordinate;
    }
}