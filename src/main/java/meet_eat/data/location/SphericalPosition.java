package meet_eat.data.location;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SphericalPosition {

    private double latitude;
    private double longitude;

    @JsonCreator
    public SphericalPosition(@JsonProperty("lat") double latitude, @JsonProperty("lon") double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLatitudeAsRadians() {
        return Math.toRadians(latitude);
    }

    public double getLongitude() {
        return longitude;
    }

    public double getLongitudeAsRadians() {
        return Math.toRadians(latitude);
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}