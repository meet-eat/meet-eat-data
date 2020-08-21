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

/**
 * Implements the {@link Comparator} functionality for {@link Offer} objects.
 */
public class OfferComparator implements Serializable, Comparator<Offer> {

    private static final long serialVersionUID = 3846969499567330524L;

    @JsonProperty
    private final OfferComparableField field;
    @JsonProperty
    private final Localizable location;

    /**
     * Creates a {@link Comparator} for {@link Offer} object comparison.
     *
     * @param field    the field to be compared
     * @param location the location needed for distance calculations
     */
    @JsonCreator
    public OfferComparator(@JsonProperty("field") OfferComparableField field,
                           @JsonProperty("location") Localizable location) {
        this.field = Objects.requireNonNull(field);
        this.location = Objects.requireNonNull(location);
    }

    /**
     * Gets the field.
     *
     * @return the field
     */
    @JsonGetter
    public OfferComparableField getField() {
        return field;
    }

    /**
     * Gets the location.
     *
     * @return the location
     */
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
                //return Double.compare(offerFst.getCreator().getHostRating(), offerSnd.getCreator().getHostRating());
                throw new UnsupportedOperationException();
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

    /**
     * Calculates the distance between the given {@link OfferComparator#location} and a {@link Localizable} location of an {@link Offer}.
     *
     * @param localizable the offer location
     * @return the distance between the given {@link OfferComparator#location} and the {@link Offer}
     */
    @JsonIgnore
    private double getDistance(Localizable localizable) {
        double distance;
        try {
            distance = location.getDistance(localizable);
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
