package meet_eat.data.predicate.chrono;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import meet_eat.data.entity.Offer;
import meet_eat.data.predicate.OfferPredicate;

import java.time.LocalDateTime;

public class LocalDateTimePredicate extends ChronoLocalDateTimeOperator implements OfferPredicate {

    private static final long serialVersionUID = 7062600211366077108L;

    /**
     * Constructs a new instance of {@link LocalDateTimePredicate}.
     *
     * @param operation the operation used for testing a certain object
     * @param dateTime  the dateTime object used as reference value within the operation
     */
    @JsonCreator
    public LocalDateTimePredicate(@JsonProperty("operation") ChronoLocalDateTimeOperation operation,
                                  @JsonProperty("referenceValue") LocalDateTime dateTime) {
        super(operation, dateTime);
    }

    @Override
    public boolean test(Offer offer) {
        return operate(offer.getDateTime());
    }
}
