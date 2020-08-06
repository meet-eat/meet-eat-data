package meet_eat.data.predicate.numeric;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import meet_eat.data.entity.Offer;
import meet_eat.data.entity.user.User;
import meet_eat.data.predicate.OfferPredicate;

/**
 * Represents an {@link OfferPredicate} for the number of {@link User}s participating in an {@link Offer}.
 */
public class ParticipantsPredicate extends DoubleOperator implements OfferPredicate {

    private static final long serialVersionUID = 3784910666230134079L;

    /**
     * Creates a participants predicate.
     *
     * @param operation      the operation
     * @param referenceValue the reference value
     */
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
