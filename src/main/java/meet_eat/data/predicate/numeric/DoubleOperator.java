package meet_eat.data.predicate.numeric;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import meet_eat.data.predicate.BooleanOperator;

public class DoubleOperator extends BooleanOperator<DoubleOperation, Double> {

    private static final long serialVersionUID = 5754645240999500816L;

    @JsonCreator
    public DoubleOperator(@JsonProperty("operation") DoubleOperation operation,
                          @JsonProperty("referenceValue") Double referenceValue) {
        super(operation, referenceValue);
    }
}
