package meet_eat.data.predicate.numeric;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import meet_eat.data.entity.Offer;
import meet_eat.data.predicate.OfferPredicate;

public class ParticipantsPredicate extends DoubleOperator implements OfferPredicate {

    private static final long serialVersionUID = 3784910666230134079L;

    @JsonCreator
    public ParticipantsPredicate(@JsonProperty("operation") DoubleOperation operation,
                                 @JsonProperty("referenceValue") int referenceValue) {
        super(operation, (double) referenceValue);
    }

    @Override
    public boolean test(Offer offer) {
        return operate((double) offer.getParticipants().size());
    }
}
