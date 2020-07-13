package meet_eat.data.entity.user.rating;

import java.util.Objects;

public class Rating {

    private static final String ERROR_MESSAGE_TEMPLATE = "The %s must not be null.";
    private static final String ERROR_MESSAGE_NULL_BASIS = String.format(ERROR_MESSAGE_TEMPLATE, "basis");
    private static final String ERROR_MESSAGE_NULL_VALUE = String.format(ERROR_MESSAGE_TEMPLATE, "value");
    private static final String ERROR_MESSAGE_NULL_REVIEWER = String.format(ERROR_MESSAGE_TEMPLATE, "reviewer");

    private final RatingBasis basis;
    private final RatingValue value;
    private final User reviewer;

    public Rating(RatingBasis basis, RatingValue value, User reviewer) {

        Objects.requireNonNull(basis, ERROR_MESSAGE_NULL_BASIS);
        Objects.requireNonNull(value, ERROR_MESSAGE_NULL_VALUE);
        Objects.requireNonNull(reviewer, ERROR_MESSAGE_NULL_REVIEWER);

        this.basis = basis;
        this.value = value;
        this.reviewer = reviewer;
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