package meet_eat.data.location;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import meet_eat.data.location.geometry.Haversine;

@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS)
@JsonSubTypes({
        @JsonSubTypes.Type(value = CityLocation.class),
        @JsonSubTypes.Type(value = SphericalLocation.class),
        @JsonSubTypes.Type(value = PostcodeLocation.class)
})
public interface Localizable {

    @JsonIgnore
    public SphericalPosition getSphericalPosition() throws UnlocalizableException;

    @JsonIgnore
    public default double getDistance(Localizable localizable) throws UnlocalizableException {
        return Haversine.applyHaversineFormula(this.getSphericalPosition(), localizable.getSphericalPosition());
    }
}