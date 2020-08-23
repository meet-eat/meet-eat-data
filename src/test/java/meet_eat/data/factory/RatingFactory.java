package meet_eat.data.factory;

import meet_eat.data.entity.Offer;
import meet_eat.data.entity.Tag;
import meet_eat.data.entity.relation.rating.Rating;
import meet_eat.data.entity.relation.rating.RatingValue;
import meet_eat.data.entity.user.User;
import meet_eat.data.location.CityLocation;
import meet_eat.data.location.Localizable;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;

@Deprecated
public class RatingFactory extends ObjectFactory<Rating> {

    private final UserFactory userFactory;
    private final TagFactory tagFactory;

    public RatingFactory() {
        super();
        userFactory = new UserFactory();
        tagFactory = new TagFactory();
    }

    @Override
    protected Rating createObject() {
        User reviewer = userFactory.getValidObject();
        User reviewedUser = userFactory.getValidObject();
        // Offer
        Set<Tag> tags = new HashSet<>();
        tags.add(tagFactory.getValidObject());
        tags.add(tagFactory.getValidObject());
        String name = "Test offer j64he";
        String description = "My description.";
        double price = 5d;
        int maxParticipants = 5;
        LocalDateTime dateTime = LocalDateTime.of(2050, Month.OCTOBER, 16, 15, 0);
        Localizable location = new CityLocation("Karlsruhe");
        Offer offer = new Offer(reviewedUser, tags, name, description, price, maxParticipants, dateTime, location);
        RatingValue value = getRandomEnumValue(RatingValue.class);
        return Rating.createHostRating(reviewer, offer, value);
    }

    private <T extends Enum<T>> T getRandomEnumValue(Class<T> clazz) {
        List<T> values = Arrays.asList(clazz.getEnumConstants());
        Collections.shuffle(values);
        Optional<T> optional = values.stream().findFirst();
        if (!optional.isPresent()) {
            throw new NoSuchElementException();
        }
        return optional.get();
    }
}
