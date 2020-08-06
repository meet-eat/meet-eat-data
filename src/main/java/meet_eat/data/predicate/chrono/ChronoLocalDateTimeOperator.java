package meet_eat.data.predicate.chrono;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import meet_eat.data.predicate.BooleanOperator;
import meet_eat.data.predicate.string.StringOperation;

import java.time.chrono.ChronoLocalDateTime;

/**
 * Represents an executor of a {@link ChronoLocalDateTimeOperation} for {@link ChronoLocalDateTime} values.
 */
public class ChronoLocalDateTimeOperator extends BooleanOperator<ChronoLocalDateTimeOperation, ChronoLocalDateTime<?>> {

    private static final long serialVersionUID = -1337704230400134159L;

    /**
     * Creates the chrono local date time operator.
     * @param operation the operation
     * @param referenceValue the reference value
     */
    @JsonCreator
    public ChronoLocalDateTimeOperator(@JsonProperty("operation") ChronoLocalDateTimeOperation operation,
                                       @JsonProperty("referenceValue") ChronoLocalDateTime<?> referenceValue) {
        super(operation, referenceValue);
    }
}

