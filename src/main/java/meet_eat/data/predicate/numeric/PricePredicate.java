package meet_eat.data.predicate.numeric;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import meet_eat.data.entity.Offer;
import meet_eat.data.predicate.OfferPredicate;

public class PricePredicate extends DoubleOperator implements OfferPredicate {

    private static final long serialVersionUID = -9147038172467909868L;

    @JsonCreator
    public PricePredicate(@JsonProperty("operation") DoubleOperation operation,
                          @JsonProperty("referenceValue") double priceBound) {
        super(operation, priceBound);
    }

    @Override
    public boolean test(Offer offer) {
        return operate(offer.getPrice());
    }
}
