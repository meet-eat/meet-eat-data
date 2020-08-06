package meet_eat.data.comparator;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import meet_eat.data.entity.Offer;
import meet_eat.data.location.Localizable;
import meet_eat.data.location.UnlocalizableException;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Objects;

public class OfferComparator implements Serializable, Comparator<Offer> {

    private static final long serialVersionUID = 3846969499567330524L;

    @JsonProperty
    private final OfferComparableField field;
    @JsonProperty
    private final Localizable location;

    @JsonCreator
    public OfferComparator(@JsonProperty("field") OfferComparableField field,
                           @JsonProperty("location") Localizable location) {
        this.field = Objects.requireNonNull(field);
        this.location = Objects.requireNonNull(location);
    }

    @JsonGetter
    public OfferComparableField getField() {
        return field;
    }

    @JsonGetter
    public Localizable getLocation() {
        return location;
    }

    @Override
    public int compare(Offer offerFst, Offer offerSnd) {
        switch (field) {
            case TIME:
                return offerFst.getDateTime().compareTo(offerSnd.getDateTime());
            case PRICE:
                return Double.compare(offerFst.getPrice(), offerSnd.getPrice());
            case RATING:
                return Double.compare(offerFst.getCreator().getHostRating(), offerSnd.getCreator().getHostRating());
            case PARTICIPANTS:
                return Integer.compare(offerFst.getParticipants().size(), offerSnd.getParticipants().size());
            case DISTANCE:
                double distanceFst = getDistance(offerFst.getLocation());
                double distanceSnd = getDistance(offerSnd.getLocation());
                return Double.compare(distanceFst, distanceSnd);
            default:
                throw new UnsupportedOperationException();
        }
    }

    @JsonIgnore
    private double getDistance(Localizable localizable) {
        double distance;
        try {
            distance = localizable.getDistance(location);
        } catch (UnlocalizableException exception) {
            distance = Double.MAX_VALUE;
        }
        return distance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OfferComparator that = (OfferComparator) o;
        return field == that.field &&
                Objects.equals(location, that.location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(field, location);
    }
}
