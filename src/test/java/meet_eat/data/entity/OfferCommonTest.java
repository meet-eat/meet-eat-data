package meet_eat.data.entity;

import meet_eat.data.Report;
import meet_eat.data.entity.user.User;
import meet_eat.data.factory.OfferFactory;
import meet_eat.data.factory.ReportFactory;
import meet_eat.data.factory.TagFactory;
import meet_eat.data.factory.UserFactory;
import meet_eat.data.location.CityLocation;
import meet_eat.data.location.Localizable;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

import static org.junit.Assert.*;

public class OfferCommonTest {

    private static final double PRICE_DELTA = 0.0001;

    private String identifier;
    private Collection<Report> reports;
    private User creator;
    private Set<User> participants;
    private Set<Tag> tags;
    private String name;
    private String description;
    private double price;
    private int maxParticipants;
    private LocalDateTime dateTime;
    private Localizable location;

    @Before
    public void setUp() {
        identifier = "This is my identifier";
        ReportFactory reportFactory = new ReportFactory();
        Report reportOne = reportFactory.getValidObject();
        Report reportTwo = reportFactory.getValidObject();
        reports = new LinkedList<>();
        reports.add(reportOne);
        reports.add(reportTwo);
        UserFactory userFactory = new UserFactory();
        creator = userFactory.getValidObject();
        User participantOne = userFactory.getValidObject();
        User participantTwo = userFactory.getValidObject();
        participants = new HashSet<>();
        participants.add(participantOne);
        participants.add(participantTwo);
        TagFactory tagFactory = new TagFactory();
        Tag tagOne = tagFactory.getValidObject();
        Tag tagTwo = tagFactory.getValidObject();
        tags = new HashSet<>();
        tags.add(tagOne);
        tags.add(tagTwo);
        name = "MyOffer";
        description = "My special test description";
        price = 9d;
        maxParticipants = 5;
        dateTime = LocalDateTime.of(2030, Month.AUGUST, 30, 18, 0);
        location = new CityLocation("Karlsruhe");
    }

    @After
    public void tearDown() {
        identifier = null;
        reports = null;
        creator = null;
        participants = null;
        tags = null;
        name = null;
        description = null;
        price = 0d;
        maxParticipants = 0;
        dateTime = null;
        location = null;
    }

    @Test
    public void testConstructor() {
        // Execution
        Offer offer = new Offer(creator, tags, name, description, price, maxParticipants, dateTime, location);

        // Assertions
        assertNotNull(offer);
        assertEquals(creator, offer.getCreator());
        assertEquals(tags, offer.getTags());
        assertEquals(name, offer.getName());
        assertEquals(description, offer.getDescription());
        assertEquals(price, offer.getPrice(), PRICE_DELTA);
        assertEquals(maxParticipants, offer.getMaxParticipants());
        assertEquals(dateTime, offer.getDateTime());
        assertEquals(location, offer.getLocation());
    }

    @Test(expected = NullPointerException.class)
    public void testConstructorNullCreator() {
        // Execution
        Offer offer = new Offer(null, tags, name, description, price, maxParticipants, dateTime, location);
    }

    @Test(expected = NullPointerException.class)
    public void testConstructorNullTags() {
        // Execution
        Offer offer = new Offer(creator, null, name, description, price, maxParticipants, dateTime, location);
    }

    @Test(expected = NullPointerException.class)
    public void testConstructorNullName() {
        // Execution
        Offer offer = new Offer(creator, tags, null, description, price, maxParticipants, dateTime, location);
    }

    @Test(expected = NullPointerException.class)
    public void testConstructorNullDescription() {
        // Execution
        Offer offer = new Offer(creator, tags, name, null, price, maxParticipants, dateTime, location);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorIllegalPrice() {
        // Test data
        double illegalPrice = -0.5d;

        // Execution
        Offer offer = new Offer(creator, tags, name, description, illegalPrice, maxParticipants, dateTime, location);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorIllegalMaxParticipants() {
        // Test data
        int illegalParticipants = 0;

        // Execution
        Offer offer = new Offer(creator, tags, name, description, price, illegalParticipants, dateTime, location);
    }

    @Test(expected = NullPointerException.class)
    public void testConstructorNullDateTime() {
        // Execution
        Offer offer = new Offer(creator, tags, name, description, price, maxParticipants, null, location);
    }

    @Test(expected = NullPointerException.class)
    public void testConstructorNullLocation() {
        // Execution
        Offer offer = new Offer(creator, tags, name, description, price, maxParticipants, dateTime, null);
    }

    @Test
    public void testJsonConstructor() {
        // Execution
        Offer offer = new Offer(identifier, reports, creator, participants, tags, name, description, price,
                maxParticipants, dateTime, location);

        // Assertions
        assertNotNull(offer);
        assertEquals(identifier, offer.getIdentifier());
        assertTrue(offer.getReports().containsAll(reports));
        assertEquals(creator, offer.getCreator());
        assertEquals(participants, offer.getParticipants());
        assertEquals(tags, offer.getTags());
        assertEquals(name, offer.getName());
        assertEquals(description, offer.getDescription());
        assertEquals(price, offer.getPrice(), PRICE_DELTA);
        assertEquals(maxParticipants, offer.getMaxParticipants());
        assertEquals(dateTime, offer.getDateTime());
        assertEquals(location, offer.getLocation());
    }

    @Test
    public void testJsonConstructorNullIdentifier() {
        // Execution
        Offer offer = new Offer(null, reports, creator, participants, tags, name, description, price,
                maxParticipants, dateTime, location);

        // Assertions
        assertNotNull(offer);
        assertNull(offer.getIdentifier());
    }

    @Test(expected = NullPointerException.class)
    public void testJsonConstructorNullReports() {
        // Execution
        Offer offer = new Offer(identifier, null, creator, participants, tags, name, description, price,
                maxParticipants, dateTime, location);
    }

    @Test(expected = NullPointerException.class)
    public void testJsonConstructorNullCreator() {
        // Execution
        Offer offer = new Offer(identifier, reports, null, participants, tags, name, description, price,
                maxParticipants, dateTime, location);
    }

    @Test(expected = NullPointerException.class)
    public void testJsonConstructorNullParticipants() {
        // Execution
        Offer offer = new Offer(identifier, reports, creator, null, tags, name, description, price,
                maxParticipants, dateTime, location);
    }

    @Test(expected = NullPointerException.class)
    public void testJsonConstructorNullTags() {
        // Execution
        Offer offer = new Offer(identifier, reports, creator, participants, null, name, description, price,
                maxParticipants, dateTime, location);
    }

    @Test(expected = NullPointerException.class)
    public void testJsonConstructorNullName() {
        // Execution
        Offer offer = new Offer(identifier, reports, creator, participants, tags, null, description, price,
                maxParticipants, dateTime, location);
    }

    @Test(expected = NullPointerException.class)
    public void testJsonConstructorNullDescription() {
        // Execution
        Offer offer = new Offer(identifier, reports, creator, participants, tags, name, null, price,
                maxParticipants, dateTime, location);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testJsonConstructorIllegalPrice() {
        // Test data
        double illegalPrice = -0.5d;

        // Execution
        Offer offer = new Offer(identifier, reports, creator, participants, tags, name, description, illegalPrice,
                maxParticipants, dateTime, location);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testJsonConstructorIllegalMaxParticipants() {
        // Test data
        int illegalParticipants = 0;

        // Execution
        Offer offer = new Offer(identifier, reports, creator, participants, tags, name, description, price,
                illegalParticipants, dateTime, location);
    }

    @Test(expected = NullPointerException.class)
    public void testJsonConstructorNullDateTime() {
        // Execution
        Offer offer = new Offer(identifier, reports, creator, participants, tags, name, description, price,
                maxParticipants, null, location);
    }

    @Test(expected = NullPointerException.class)
    public void testJsonConstructorNullLocation() {
        // Execution
        Offer offer = new Offer(identifier, reports, creator, participants, tags, name, description, price,
                maxParticipants, dateTime, null);
    }

    @Test
    public void testSetName() {
        // Test data
        String name = "The new offer test name";

        // Execution
        Offer offer = new OfferFactory().getValidObject();
        offer.setName(name);

        // Assertions
        assertEquals(name, offer.getName());
    }

    @Test(expected = NullPointerException.class)
    public void testSetNullName() {
        // Execution
        Offer offer = new OfferFactory().getValidObject();
        offer.setName(null);
    }

    @Test
    public void testSetDescription() {
        // Test data
        String description = "A super cool new description.";

        // Execution
        Offer offer = new OfferFactory().getValidObject();
        offer.setDescription(description);

        // Assertions
        assertEquals(description, offer.getDescription());
    }

    @Test(expected = NullPointerException.class)
    public void testSetNullDescription() {
        // Execution
        Offer offer = new OfferFactory().getValidObject();
        offer.setDescription(null);
    }

    @Test
    public void testSetPrice() {
        // Test data
        double price = 10.5d;

        // Execution
        Offer offer = new OfferFactory().getValidObject();
        offer.setPrice(price);

        // Assertions
        assertEquals(price, offer.getPrice(), PRICE_DELTA);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetIllegalPrice() {
        // Test data
        double illegalPrice = -5d;

        // Execution
        Offer offer = new OfferFactory().getValidObject();
        offer.setPrice(illegalPrice);
    }

    @Test
    public void testSetMaxParticipants() {
        // Test data
        int maxParticipants = 20;

        // Execution
        Offer offer = new OfferFactory().getValidObject();
        offer.setMaxParticipants(maxParticipants);

        // Assertions
        assertEquals(maxParticipants, offer.getMaxParticipants());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetIllegalMaxParticipants() {
        // Test data
        int illegalParticipants = 0;

        // Execution
        Offer offer = new OfferFactory().getValidObject();
        offer.setMaxParticipants(illegalParticipants);
    }

    @Test
    public void testSetDateTime() {
        // Test data
        LocalDateTime dateTime = LocalDateTime.of(2050, Month.JANUARY, 1, 15, 0);

        // Execution
        Offer offer = new OfferFactory().getValidObject();
        offer.setDateTime(dateTime);

        // Assertions
        assertEquals(dateTime, offer.getDateTime());
    }

    @Test(expected = NullPointerException.class)
    public void testSetNullDateTime() {
        // Execution
        Offer offer = new OfferFactory().getValidObject();
        offer.setDateTime(null);
    }

    @Test
    public void testSetLocation() {
        // Test data
        CityLocation location = new CityLocation("Stuttgart");

        // Execution
        Offer offer = new OfferFactory().getValidObject();
        offer.setLocation(location);

        // Assertions
        assertEquals(location, offer.getLocation());
    }

    @Test(expected = NullPointerException.class)
    public void testSetNullLocation() {
        // Execution
        Offer offer = new OfferFactory().getValidObject();
        offer.setLocation(null);
    }

    @Test
    public void testAddParticipants() {
        // Test data
        UserFactory userFactory = new UserFactory();
        User userOne = userFactory.getValidObject();
        User userTwo = userFactory.getValidObject();

        // Execution
        Offer offer = new OfferFactory().getValidObject();
        int amountParticipantsBefore = offer.getParticipants().size();
        offer.addParticipant(userOne);
        offer.addParticipant(userTwo);

        // Assertions
        assertNotEquals(amountParticipantsBefore, offer.getParticipants().size());
        assertEquals(offer.getParticipants().size(), amountParticipantsBefore + 2);
        assertTrue(offer.getParticipants().contains(userOne));
        assertTrue(offer.getParticipants().contains(userTwo));
    }

    @Test(expected = NullPointerException.class)
    public void testAddNullParticipants() {
        // Execution
        Offer offer = new OfferFactory().getValidObject();
        offer.addParticipant(null);
    }

    @Test
    public void testAddTags() {
        // Test data
        TagFactory tagFactory = new TagFactory();
        Tag tagOne = tagFactory.getValidObject();
        Tag tagTwo = tagFactory.getValidObject();

        // Execution
        Offer offer = new OfferFactory().getValidObject();
        int amountTagsBefore = offer.getTags().size();
        offer.addTag(tagOne);
        offer.addTag(tagTwo);

        // Assertions
        assertNotEquals(amountTagsBefore, offer.getTags().size());
        assertEquals(offer.getTags().size(), amountTagsBefore + 2);
        assertTrue(offer.getTags().contains(tagOne));
        assertTrue(offer.getTags().contains(tagTwo));
    }

    @Test(expected = NullPointerException.class)
    public void testAddNullTag() {
        // Execution
        Offer offer = new OfferFactory().getValidObject();
        offer.addTag(null);
    }

    @Test
    public void testRemoveParticipant() {
        // Test data
        UserFactory userFactory = new UserFactory();
        User userOne = userFactory.getValidObject();
        User userTwo = userFactory.getValidObject();

        // Execution
        Offer offer = new OfferFactory().getValidObject();
        int amountParticipantsBefore = offer.getParticipants().size();
        offer.addParticipant(userOne);
        offer.addParticipant(userTwo);
        offer.removeParticipant(userOne);

        // Assertions
        assertTrue(offer.getParticipants().contains(userTwo));
        assertTrue(!offer.getParticipants().contains(userOne));
        assertEquals(amountParticipantsBefore + 1, offer.getParticipants().size());
    }

    @Test(expected = NullPointerException.class)
    public void testRemoveNullParticipant() {
        // Execution
        Offer offer = new OfferFactory().getValidObject();
        offer.removeParticipant(null);
    }

    @Test
    public void testRemoveTag() {
        // Test data
        TagFactory tagFactory = new TagFactory();
        Tag tagOne = tagFactory.getValidObject();
        Tag tagTwo = tagFactory.getValidObject();

        // Execution
        Offer offer = new OfferFactory().getValidObject();
        int amountTagsBefore = offer.getTags().size();
        offer.addTag(tagOne);
        offer.addTag(tagTwo);
        offer.removeTag(tagOne);

        // Assertions
        assertTrue(offer.getTags().contains(tagTwo));
        assertTrue(!offer.getTags().contains(tagOne));
        assertEquals(amountTagsBefore + 1, offer.getTags().size());
    }

    @Test(expected = NullPointerException.class)
    public void testRemoveNullTag() {
        // Execution
        Offer offer = new OfferFactory().getValidObject();
        offer.removeTag(null);
    }

    @Test
    public void testOfferEquality() {
        // Execution
        Offer offer = new Offer(identifier, reports, creator, participants, tags, name, description, price,
                maxParticipants, dateTime, location);
        Offer offerCopy = new Offer(offer.getIdentifier(), offer.getReports(), offer.getCreator(),
                offer.getParticipants(), offer.getTags(), offer.getName(), offer.getDescription(), offer.getPrice(),
                offer.getMaxParticipants(), offer.getDateTime(), offer.getLocation());

        // Assertions
        assertNotNull(offer);
        assertNotNull(offerCopy);
        assertEquals(offer, offer);
        assertEquals(offer, offerCopy);
    }
}