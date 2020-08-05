package meet_eat.data.factory;

import meet_eat.data.entity.user.User;
import meet_eat.data.entity.user.rating.Rating;
import meet_eat.data.entity.user.rating.RatingBasis;
import meet_eat.data.entity.user.rating.RatingValue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class RatingFactory extends ObjectFactory<Rating> {

    private UserFactory userFactory;

    public RatingFactory() {
        super();
        userFactory = new UserFactory();
    }

    @Override
    protected Rating createObject() {
        RatingBasis basis = getRandomEnumValue(RatingBasis.class);
        RatingValue value = getRandomEnumValue(RatingValue.class);
        User user = userFactory.getValidObject();
        return new Rating(basis, value, user);
    }

    private <T extends Enum<T>> T getRandomEnumValue(Class<T> clazz) {
        List<T> values = Arrays.asList(clazz.getEnumConstants());
        Collections.shuffle(values);
        return values.stream().findFirst().get();
    }
}
