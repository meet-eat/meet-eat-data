package meet_eat.data.entity.user.rating;

import java.util.Arrays;
import java.util.Collection;

/**
 * Represents the amount of a given rating.
 */
public enum RatingValue {

    /**
     * A one point rating.
     */
    POINTS_1(1),

    /**
     * A two points rating.
     */
    POINTS_2(2),

    /**
     * A three points rating.
     */
    POINTS_3(3),

    /**
     * A four points rating.
     */
    POINTS_4(4),

    /**
     * A five points rating.
     */
    POINTS_5(5);

    private int integerValue;

    private RatingValue(int integerValue) {
        this.integerValue = integerValue;
    }

    /**
     * Gets the matching rating value based by a given integer value.
     *
     * @param value the integer value
     * @return the rating basis
     */
    public static RatingValue getRatingValueByInteger(int value) {
        Collection<RatingValue> ratingValues = Arrays.asList(RatingValue.class.getEnumConstants());
        return ratingValues
                .stream()
                .filter(x -> x.getIntegerValue() == value)
                .findFirst()
                .orElseThrow();
    }

    /**
     * Gets the integer value of a rating value.
     *
     * @return the integer value
     */
    public int getIntegerValue() {
        return integerValue;
    }
}