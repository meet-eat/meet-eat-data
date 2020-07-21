package meet_eat.data.location;

import java.util.Objects;

public class SphericalLocation implements Localizable {

    private static final String ERROR_MESSAGE_TEMPLATE_NULL = "The %s must not be null.";
    private static final String ERROR_MESSAGE_NULL_SPHERICAL_POSITION = String.format(ERROR_MESSAGE_TEMPLATE_NULL, "sphericalPosition");

    private SphericalPosition sphericalPosition;

    public SphericalLocation(SphericalPosition sphericalPosition) {
        Objects.requireNonNull(sphericalPosition, ERROR_MESSAGE_NULL_SPHERICAL_POSITION);
        this.sphericalPosition = sphericalPosition;
    }

    @Override
    public SphericalPosition getSphericalPosition() {
        return sphericalPosition;
    }

    public void setSphericalPosition(SphericalPosition sphericalPosition) {
        Objects.requireNonNull(sphericalPosition, ERROR_MESSAGE_NULL_SPHERICAL_POSITION);
        this.sphericalPosition = sphericalPosition;
    }
}