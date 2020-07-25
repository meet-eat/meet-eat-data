package meet_eat.data;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Page page = (Page) o;
        return index == page.index &&
                size == page.size;
    }

    @Override
    public int hashCode() {
        return Objects.hash(index, size);
    }
}