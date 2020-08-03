package meet_eat.data.predicate.chrono;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import meet_eat.data.predicate.BooleanOperator;

import java.time.chrono.ChronoLocalDateTime;

public class ChronoLocalDateTimeOperator extends BooleanOperator<ChronoLocalDateTimeOperation, ChronoLocalDateTime<?>> {

    private static final long serialVersionUID = -1337704230400134159L;

    @JsonCreator
    public ChronoLocalDateTimeOperator(@JsonProperty("operation") ChronoLocalDateTimeOperation operation,
                                       @JsonProperty("referenceValue") ChronoLocalDateTime<?> referenceValue) {
        super(operation, referenceValue);
    }
}

