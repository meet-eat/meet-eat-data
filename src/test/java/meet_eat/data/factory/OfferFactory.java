package meet_eat.data.factory;

import meet_eat.data.entity.Offer;
import meet_eat.data.entity.Tag;
import meet_eat.data.entity.user.User;
import meet_eat.data.location.Localizable;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Collection;
import java.util.HashSet;

public class OfferFactory extends ObjectFactory<Offer> {

    private static final int TAG_AMOUNT = 3;
    private static final double TEST_PRICE = 5d;
    private static final int TEST_AMOUNT_PARTICIPANTS = 5;

    private UserFactory userFactory;
    private TagFactory tagFactory;
    private LocationFactory locationFactory;
    private Collection<Tag> tags;

    public OfferFactory() {
        userFactory = new UserFactory();
        tagFactory = new TagFactory();
        locationFactory = new LocationFactory();
        tags = new HashSet<Tag>();
    }

    @Override
    protected Offer createObject() {
        User creator = userFactory.getValidObject();
        for (int i = 0; i < TAG_AMOUNT; i++) {
            tags.add(tagFactory.getValidObject());
        }
        String name = "TestOffer" + objectCounter;
        String description = "This is test offer number " + objectCounter;
        double price = TEST_PRICE;
        int maxParticipants = TEST_AMOUNT_PARTICIPANTS;
        LocalDateTime dateTime = LocalDateTime.of(LocalDate.EPOCH, LocalTime.NOON);
        Localizable location = locationFactory.getValidObject();
        return new Offer(creator, tags, name, description, price, maxParticipants, dateTime, location);
    }
}
