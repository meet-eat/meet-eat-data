package meet_eat.data.entity.user.rating;

import java.util.Objects;

import meet_eat.data.entity.user.User;

public class Rating {

    private static final String ERROR_MESSAGE_TEMPLATE_NULL = "The %s must not be null.";
    private static final String ERROR_MESSAGE_NULL_BASIS = String.format(ERROR_MESSAGE_TEMPLATE_NULL, "basis");
    private static final String ERROR_MESSAGE_NULL_VALUE = String.format(ERROR_MESSAGE_TEMPLATE_NULL, "value");
    private static final String ERROR_MESSAGE_NULL_REVIEWER = String.format(ERROR_MESSAGE_TEMPLATE_NULL, "reviewer");

    private final RatingBasis basis;
    private final RatingValue value;
    private final User reviewer;

    public Rating(RatingBasis basis, RatingValue value, User reviewer) {
        this.basis = Objects.requireNonNull(basis, ERROR_MESSAGE_NULL_BASIS);
        this.value = Objects.requireNonNull(value, ERROR_MESSAGE_NULL_VALUE);
        this.reviewer = Objects.requireNonNull(reviewer, ERROR_MESSAGE_NULL_REVIEWER);
    }

    public RatingBasis getBasis() {
        return basis;
    }

    public RatingValue getValue() {
        return value;
    }

    public User getReviewer() {
        return reviewer;
    }
}