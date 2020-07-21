package meet_eat.data.location;

import meet_eat.data.location.service.Geocoding;

import java.util.Objects;

public class PostcodeLocation implements Localizable {

    private static final String ERROR_MESSAGE_TEMPLATE_NULL = "The %s must not be null.";
    private static final String ERROR_MESSAGE_NULL_POSTCODE = String.format(ERROR_MESSAGE_TEMPLATE_NULL, "postcode");
    private static final String UNLOCALIZABLE_POSTCODE = "The given postcode is not localizable.";

    private String postcode;

    public PostcodeLocation(String postcode) {
        Objects.requireNonNull(postcode, ERROR_MESSAGE_NULL_POSTCODE);
        this.postcode = postcode;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        Objects.requireNonNull(postcode, ERROR_MESSAGE_NULL_POSTCODE);
        this.postcode = postcode;
    }

    @Override
    public SphericalPosition getSphericalPosition() throws UnlocalizableException {
        SphericalPosition sphericalPosition = Geocoding.getSphericalPositionFromPostcode(postcode);
        if (Objects.isNull(sphericalPosition)) {
            throw new UnlocalizableException(UNLOCALIZABLE_POSTCODE);
        }
        return sphericalPosition;
    }
}