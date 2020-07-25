package meet_eat.data.location;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import meet_eat.data.location.service.Geocoding;

import java.util.Objects;

public class CityLocation implements Localizable {

    private static final String ERROR_MESSAGE_TEMPLATE_NULL = "The %s must not be null.";
    private static final String ERROR_MESSAGE_NULL_CITY_NAME = String.format(ERROR_MESSAGE_TEMPLATE_NULL, "cityName");
    private static final String UNLOCALIZABLE_CITY = "The given city is not localizable.";

    @JsonProperty
    private String cityName;

    @JsonCreator
    public CityLocation(@JsonProperty("cityName") String cityName) {
        this.cityName = Objects.requireNonNull(cityName, ERROR_MESSAGE_NULL_CITY_NAME);
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = Objects.requireNonNull(cityName, ERROR_MESSAGE_NULL_CITY_NAME);
    }

    @Override
    @JsonIgnore
    public SphericalPosition getSphericalPosition() throws UnlocalizableException {
        SphericalPosition sphericalPosition = Geocoding.getSphericalPositionFromCityName(cityName);
        if (Objects.isNull(sphericalPosition)) {
            throw new UnlocalizableException(UNLOCALIZABLE_CITY);
        }
        return sphericalPosition;
    }
}