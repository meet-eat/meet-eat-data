package meet_eat.data.location;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class SphericalLocation implements Localizable {

    private static final String ERROR_MESSAGE_TEMPLATE_NULL = "The %s must not be null.";
    private static final String ERROR_MESSAGE_NULL_SPHERICAL_POSITION = String.format(ERROR_MESSAGE_TEMPLATE_NULL, "sphericalPosition");

    @JsonProperty
    private SphericalPosition sphericalPosition;

    @JsonCreator
    public SphericalLocation(@JsonProperty("sphericalPosition") SphericalPosition sphericalPosition) {
        this.sphericalPosition = Objects.requireNonNull(sphericalPosition, ERROR_MESSAGE_NULL_SPHERICAL_POSITION);
    }

    @Override
    public SphericalPosition getSphericalPosition() {
        return sphericalPosition;
    }

    public void setSphericalPosition(SphericalPosition sphericalPosition) {
        this.sphericalPosition = Objects.requireNonNull(sphericalPosition, ERROR_MESSAGE_NULL_SPHERICAL_POSITION);
    }
}