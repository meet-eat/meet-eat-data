package meet_eat.data.predicate.string;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import meet_eat.data.entity.Offer;
import meet_eat.data.predicate.OfferPredicate;

public class DescriptionPredicate extends StringOperator implements OfferPredicate {

    private static final long serialVersionUID = 1761548413420048622L;

    @JsonCreator
    public DescriptionPredicate(@JsonProperty("operation") StringOperation operation,
                                @JsonProperty("referenceValue") String referenceValue) {
        super(operation, referenceValue);
    }

    @Override
    public boolean test(Offer offer) {
        return operate(offer.getDescription());
    }
}
