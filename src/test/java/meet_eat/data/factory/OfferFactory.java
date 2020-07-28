package meet_eat.data.factory;

import meet_eat.data.Report;
import meet_eat.data.entity.Offer;
import meet_eat.data.entity.Tag;
import meet_eat.data.entity.user.User;
import meet_eat.data.location.Localizable;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;

public class OfferFactory extends ObjectFactory<Offer> {

    private static final int AMOUNT_REPORTS = 2;
    private static final int AMOUNT_TAGS = 2;
    private static final double TEST_PRICE = 5d;
    private static final int TEST_AMOUNT_PARTICIPANTS = 5;

    private ReportFactory reportFactory;
    private UserFactory userFactory;
    private TagFactory tagFactory;
    private LocationFactory locationFactory;

    public OfferFactory() {
        reportFactory = new ReportFactory();
        userFactory = new UserFactory();
        tagFactory = new TagFactory();
        locationFactory = new LocationFactory();
    }

    @Override
    protected Offer createObject() {
        String identifier = Integer.toString(objectCounter);
        Collection<Report> reports = new LinkedList<>();
        for (int i = 0; i < AMOUNT_REPORTS; i++) {
            reports.add(reportFactory.getValidObject());
        }
        User creator = userFactory.getValidObject();
        Collection<Tag> tags = new HashSet<>();
        for (int i = 0; i < AMOUNT_TAGS; i++) {
            tags.add(tagFactory.getValidObject());
        }
        String name = "TestOffer" + objectCounter;
        String description = "This is test offer number " + objectCounter;
        double price = TEST_PRICE;
        int maxParticipants = TEST_AMOUNT_PARTICIPANTS;
        LocalDateTime dateTime = LocalDateTime.of(LocalDate.EPOCH, LocalTime.NOON);
        Localizable location = locationFactory.getValidObject();
        return new Offer(identifier, reports, creator, tags, name, description, price, maxParticipants, dateTime,
                location);
    }
}
