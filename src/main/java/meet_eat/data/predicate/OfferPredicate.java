package meet_eat.data.predicate;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import meet_eat.data.entity.Offer;
import meet_eat.data.predicate.chrono.LocalDateTimePredicate;
import meet_eat.data.predicate.collection.TagPredicate;
import meet_eat.data.predicate.localizable.LocalizablePredicate;
import meet_eat.data.predicate.numeric.ParticipantsPredicate;
import meet_eat.data.predicate.numeric.PricePredicate;
import meet_eat.data.predicate.numeric.RatingPredicate;
import meet_eat.data.predicate.string.DescriptionPredicate;
import meet_eat.data.predicate.string.NamePredicate;

import java.io.Serializable;
import java.util.function.Predicate;

/**
 * Represents a {@link Predicate} for an {@link Offer} object.
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS)
@JsonSubTypes({
        @JsonSubTypes.Type(value = LocalizablePredicate.class),
        @JsonSubTypes.Type(value = LocalDateTimePredicate.class),
        @JsonSubTypes.Type(value = PricePredicate.class),
        @JsonSubTypes.Type(value = RatingPredicate.class),
        @JsonSubTypes.Type(value = ParticipantsPredicate.class),
        @JsonSubTypes.Type(value = NamePredicate.class),
        @JsonSubTypes.Type(value = DescriptionPredicate.class),
        @JsonSubTypes.Type(value = TagPredicate.class)
})
public interface OfferPredicate extends Serializable, Predicate<Offer> {

}
