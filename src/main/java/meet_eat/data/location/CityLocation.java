package meet_eat.data.location;

import meet_eat.data.location.service.Geocoding;

import java.util.Objects;

public class CityLocation implements Localizable {

    private static final String UNLOCALIZABLE_CITY = "The given city is not localizable.";

    private String cityName;

    public CityLocation(String cityName) {
        this.cityName = cityName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    @Override
    public SphericalPosition getSphericalPosition() throws UnlocalizableException {
        SphericalPosition sphericalPosition = Geocoding.getSphericalPositionFromCityName(cityName);
        if (Objects.isNull(sphericalPosition)) {
            throw new UnlocalizableException(UNLOCALIZABLE_CITY);
        }
        return sphericalPosition;
    }
}