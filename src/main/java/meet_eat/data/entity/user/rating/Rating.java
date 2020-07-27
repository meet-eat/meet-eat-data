package meet_eat.data.entity.user.rating;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import meet_eat.data.entity.user.User;

public class Rating {

    private static final String ERROR_MESSAGE_TEMPLATE_NULL = "The %s must not be null.";
    private static final String ERROR_MESSAGE_NULL_BASIS = String.format(ERROR_MESSAGE_TEMPLATE_NULL, "basis");
    private static final String ERROR_MESSAGE_NULL_VALUE = String.format(ERROR_MESSAGE_TEMPLATE_NULL, "value");
    private static final String ERROR_MESSAGE_NULL_REVIEWER = String.format(ERROR_MESSAGE_TEMPLATE_NULL, "reviewer");

    @JsonProperty
    private final RatingBasis basis;
    @JsonProperty
    private final RatingValue value;
    @JsonProperty
    private final User reviewer;

    @JsonCreator
    public Rating(@JsonProperty("basis") RatingBasis basis,
                  @JsonProperty("value") RatingValue value,
                  @JsonProperty("reviewer") User reviewer) {
        this.basis = Objects.requireNonNull(basis, ERROR_MESSAGE_NULL_BASIS);
        this.value = Objects.requireNonNull(value, ERROR_MESSAGE_NULL_VALUE);
        this.reviewer = Objects.requireNonNull(reviewer, ERROR_MESSAGE_NULL_REVIEWER);
    }

    @JsonGetter
    public RatingBasis getBasis() {
        return basis;
    }

    @JsonGetter
    public RatingValue getValue() {
        return value;
    }

    @JsonGetter
    public User getReviewer() {
        return reviewer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rating rating = (Rating) o;
        return basis == rating.basis &&
                value == rating.value &&
                Objects.equals(reviewer, rating.reviewer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(basis, value, reviewer);
    }
}