package meet_eat.data.predicate.collection;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import meet_eat.data.entity.Offer;
import meet_eat.data.entity.Tag;
import meet_eat.data.predicate.OfferPredicate;

import java.util.Collection;

public class TagPredicate extends CollectionOperator implements OfferPredicate {

    private static final long serialVersionUID = 5677783409137877370L;

    @JsonCreator
    public TagPredicate(@JsonProperty("operation") CollectionOperation operation,
                        @JsonProperty("referenceValue") Collection<Tag> tags) {
        super(operation, tags);
    }

    @Override
    public boolean test(Offer offer) {
        return operate(offer.getTags());
    }
}
