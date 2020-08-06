package meet_eat.data.comparator;

import meet_eat.data.entity.Offer;
import meet_eat.data.location.Localizable;
import meet_eat.data.location.UnlocalizableException;
import org.springframework.cglib.core.Local;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Objects;

public class OfferComparator implements Serializable, Comparator<Offer> {

    private static final long serialVersionUID = 3846969499567330524L;

    private final OfferComparableField field;
    private final Localizable location;

    public OfferComparator(OfferComparableField field, Localizable location) {
        this.field = Objects.requireNonNull(field);
        this.location = Objects.requireNonNull(location);
    }

    public OfferComparableField getField() {
        return field;
    }

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

    private double getDistance(Localizable localizable) {
        double distance;
        try {
            distance = localizable.getDistance(location);
        } catch (UnlocalizableException exception) {
            distance = Double.MAX_VALUE;
        }
        return distance;
    }
}
