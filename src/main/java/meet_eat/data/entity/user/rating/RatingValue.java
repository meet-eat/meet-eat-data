package meet_eat.data.entity.user.rating;

import java.util.*;

public enum RatingValue {

    POINTS_1(1),

    POINTS_2(2),

    POINTS_3(3),

    POINTS_4(4),

    POINTS_5(5);

    private int integerValue;

    private RatingValue(int integerValue) {
        this.integerValue = integerValue;
    }

    public static RatingValue getRatingValueByInteger(int value) {
        Collection<RatingValue> ratingValues = Arrays.asList(RatingValue.class.getEnumConstants());
        return ratingValues
                .stream()
                .filter(x -> x.getIntegerValue() == value)
                .findFirst()
                .orElseThrow();
    }

    public int getIntegerValue() {
        return integerValue;
    }
}