package meet_eat.data.predicate;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Objects;
import java.util.function.BiFunction;

public abstract class BooleanOperator<T extends BiFunction<S, S, Boolean>, S> implements Serializable {

    private static final long serialVersionUID = -347923085311754712L;

    @JsonProperty
    private final T operation;
    @JsonProperty
    private final S referenceValue;

    @JsonCreator
    protected BooleanOperator(@JsonProperty("operation") T operation, @JsonProperty("referenceValue") S referenceValue) {
        this.operation = Objects.requireNonNull(operation);
        this.referenceValue = Objects.requireNonNull(referenceValue);
    }

    @JsonGetter
    public T getOperation() {
        return operation;
    }

    @JsonGetter
    public S getReferenceValue() {
        return referenceValue;
    }

    public Boolean operate(S value) {
        return operation.apply(referenceValue, value);
    }
}
