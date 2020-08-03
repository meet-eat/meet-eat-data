package meet_eat.data.predicate.string;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import meet_eat.data.predicate.BooleanOperator;

public class StringOperator extends BooleanOperator<StringOperation, String> {

    private static final long serialVersionUID = 43015093667164186L;

    @JsonCreator
    protected StringOperator(@JsonProperty("operation") StringOperation operation,
                             @JsonProperty("referenceValue") String referenceValue) {
        super(operation, referenceValue);
    }
}
