package meet_eat.data.entity.user.rating;

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

    public int getIntegerValue() {
        return integerValue;
    }
}