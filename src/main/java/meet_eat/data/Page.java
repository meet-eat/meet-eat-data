package meet_eat.data;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Page {

    private static final String ERROR_MESSAGE_ILLEGAL_INDEX = "Page index must not be less than zero.";
    private static final String ERROR_MESSAGE_ILLEGAL_SIZE = "Page size must not be less than one.";

    public static final int INDEX_LOWER_BOUND = 0;
    public static final int SIZE_LOWER_BOUND = 1;

    @JsonProperty
    private final int index;
    @JsonProperty
    private final int size;

    @JsonCreator
    public Page(@JsonProperty("index") int index, @JsonProperty("size") int size) {
        if (index < INDEX_LOWER_BOUND) {
            throw new IllegalArgumentException(ERROR_MESSAGE_ILLEGAL_INDEX);
        } else if (size < SIZE_LOWER_BOUND) {
            throw new IllegalArgumentException(ERROR_MESSAGE_ILLEGAL_SIZE);
        }
        
        this.index = index;
        this.size = size;
    }

    public int getIndex() {
        return index;
    }

    public int getSize() {
        return size;
    }
}