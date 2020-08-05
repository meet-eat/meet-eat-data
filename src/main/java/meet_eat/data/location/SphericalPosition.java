package meet_eat.data.location;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SphericalPosition {

    @JsonProperty("lat")
    private double latitude;
    @JsonProperty("lon")
    private double longitude;

    @JsonCreator
    public SphericalPosition(@JsonProperty("lat") double latitude, @JsonProperty("lon") double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    @JsonIgnore
    public double getLatitudeAsRadians() {
        return Math.toRadians(latitude);
    }

    public double getLongitude() {
        return longitude;
    }

    @JsonIgnore
    public double getLongitudeAsRadians() {
        return Math.toRadians(longitude);
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SphericalPosition that = (SphericalPosition) o;
        return Double.compare(that.latitude, latitude) == 0 &&
                Double.compare(that.longitude, longitude) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(latitude, longitude);
    }
}