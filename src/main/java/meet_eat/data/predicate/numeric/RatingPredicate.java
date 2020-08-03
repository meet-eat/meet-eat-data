package meet_eat.data.predicate.numeric;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import meet_eat.data.entity.Offer;
import meet_eat.data.predicate.OfferPredicate;

public class RatingPredicate extends DoubleOperator implements OfferPredicate {

    private static final long serialVersionUID = -836110461118942920L;

    @JsonCreator
    public RatingPredicate(@JsonProperty("operation") DoubleOperation operation,
                           @JsonProperty("referenceValue") Double referenceValue) {
        super(operation, referenceValue);
    }

    @Override
    public boolean test(Offer offer) {
        return operate(offer.getCreator().getHostRating());
    }
}
