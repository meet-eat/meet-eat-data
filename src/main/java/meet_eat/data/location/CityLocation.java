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
    public Coordinate getCoordinate() throws UnlocalizableException {
        Coordinate coordinate = Geocoding.getCoordinateFromCityName(cityName);
        if (Objects.isNull(coordinate)) {
            throw new UnlocalizableException(UNLOCALIZABLE_CITY);
        }
        return coordinate;
    }
}