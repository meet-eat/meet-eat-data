package meet_eat.data.comparator;

import meet_eat.data.entity.Offer;
import meet_eat.data.entity.user.User;
import meet_eat.data.entity.user.rating.Rating;
import meet_eat.data.entity.user.rating.RatingBasis;
import meet_eat.data.entity.user.rating.RatingValue;
import meet_eat.data.factory.LocationFactory;
import meet_eat.data.factory.OfferFactory;
import meet_eat.data.factory.UserFactory;
import meet_eat.data.location.*;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class OfferComparatorCommonTest {

    private class OfferComparatorMock extends OfferComparator {

        public OfferComparatorMock(OfferComparableField field, Localizable localizable) {
            super(field, localizable);
        }
    }

    @Test
    public void testConstructor() {
        // Test data
        OfferComparableField field = OfferComparableField.DISTANCE;
        Localizable location = new LocationFactory().getValidObject();

        // Execution
        OfferComparatorMock comparator = new OfferComparatorMock(field, location);

        // Assertions
        assertNotNull(comparator);
        assertNotNull(comparator.getField());
        assertNotNull(comparator.getLocation());
    }

    @Test(expected = NullPointerException.class)
    public void testConstructorNullField() {
        // Test data
        Localizable location = new LocationFactory().getValidObject();

        // Execution
        OfferComparatorMock comparator = new OfferComparatorMock(null, location);
    }

    @Test(expected = NullPointerException.class)
    public void testConstructorNullLocation() {
        // Test data
        OfferComparableField field = OfferComparableField.DISTANCE;

        // Execution
        OfferComparatorMock comparator = new OfferComparatorMock(field, null);
    }

    @Test
    public void testPrice() {
        // Test data
        OfferComparableField price = OfferComparableField.PRICE;
        Localizable location = new LocationFactory().getValidObject();
        OfferFactory offerFactory = new OfferFactory();
        Offer offerOne = offerFactory.getValidObject();
        Offer offerTwo = offerFactory.getValidObject();
        Offer offerThree = offerFactory.getValidObject();
        Offer offerFour = offerFactory.getValidObject();
        Offer offerFive = offerFactory.getValidObject();
        offerOne.setPrice(5d);
        offerTwo.setPrice(8d);
        offerThree.setPrice(2d);
        offerFour.setPrice(11d);
        offerFive.setPrice(20d);
        List<Offer> list = new ArrayList<>();
        list.add(offerOne);
        list.add(offerTwo);
        list.add(offerThree);
        list.add(offerFour);
        list.add(offerFive);

        // Execution
        OfferComparatorMock comparator = new OfferComparatorMock(price, location);

        // Assertions
        assertEquals(offerOne, list.get(0));
        assertEquals(offerTwo, list.get(1));
        assertEquals(offerThree, list.get(2));
        assertEquals(offerFour, list.get(3));
        assertEquals(offerFive, list.get(4));

        // Sort list from small to big
        Collections.sort(list, comparator);

        assertEquals(offerThree, list.get(0));
        assertEquals(offerOne, list.get(1));
        assertEquals(offerTwo, list.get(2));
        assertEquals(offerFour, list.get(3));
        assertEquals(offerFive, list.get(4));

        // Reverse list order from big to small
        Collections.sort(list, comparator.reversed());

        assertEquals(offerFive, list.get(0));
        assertEquals(offerFour, list.get(1));
        assertEquals(offerTwo, list.get(2));
        assertEquals(offerOne, list.get(3));
        assertEquals(offerThree, list.get(4));
    }

    @Test
    public void testTime() {
        // Test data
        OfferComparableField time = OfferComparableField.TIME;
        Localizable location = new LocationFactory().getValidObject();
        OfferFactory offerFactory = new OfferFactory();
        Offer offerOne = offerFactory.getValidObject();
        Offer offerTwo = offerFactory.getValidObject();
        Offer offerThree = offerFactory.getValidObject();
        Offer offerFour = offerFactory.getValidObject();
        Offer offerFive = offerFactory.getValidObject();
        offerOne.setDateTime(LocalDateTime.of(2000, Month.JANUARY, 1, 0, 0));
        offerTwo.setDateTime(LocalDateTime.of(1950, Month.JANUARY, 1, 0, 0));
        offerThree.setDateTime(LocalDateTime.of(1975, Month.JANUARY, 1, 0, 0));
        offerFour.setDateTime(LocalDateTime.of(2010, Month.JANUARY, 1, 0, 0));
        offerFive.setDateTime(LocalDateTime.of(1940, Month.JANUARY, 1, 0, 0));
        List<Offer> list = new ArrayList<>();
        list.add(offerOne);
        list.add(offerTwo);
        list.add(offerThree);
        list.add(offerFour);
        list.add(offerFive);

        // Execution
        OfferComparatorMock comparator = new OfferComparatorMock(time, location);

        // Assertions
        assertEquals(offerOne, list.get(0));
        assertEquals(offerTwo, list.get(1));
        assertEquals(offerThree, list.get(2));
        assertEquals(offerFour, list.get(3));
        assertEquals(offerFive, list.get(4));

        // Sort list from early to late
        Collections.sort(list, comparator);

        assertEquals(offerFive, list.get(0));
        assertEquals(offerTwo, list.get(1));
        assertEquals(offerThree, list.get(2));
        assertEquals(offerOne, list.get(3));
        assertEquals(offerFour, list.get(4));

        // Reverse list order from late to early
        Collections.sort(list, comparator.reversed());

        assertEquals(offerFour, list.get(0));
        assertEquals(offerOne, list.get(1));
        assertEquals(offerThree, list.get(2));
        assertEquals(offerTwo, list.get(3));
        assertEquals(offerFive, list.get(4));
    }

    @Test
    public void testDistance() {
        // Test data
        OfferComparableField distance = OfferComparableField.DISTANCE;
        Localizable location = new CityLocation("Karlsruhe");
        OfferFactory offerFactory = new OfferFactory();
        Offer offerOne = offerFactory.getValidObject();
        Offer offerTwo = offerFactory.getValidObject();
        Offer offerThree = offerFactory.getValidObject();
        Offer offerFour = offerFactory.getValidObject();
        Offer offerFive = offerFactory.getValidObject();
        offerOne.setLocation(new CityLocation("Stuttgart"));
        offerTwo.setLocation(new CityLocation("München"));
        // Berlin Mitte
        offerThree.setLocation(new PostcodeLocation("10115"));
        // Hamburg
        offerFour.setLocation(new SphericalLocation(new SphericalPosition(53.550341, 10.000654)));
        // Bad Herrenalb
        offerFive.setLocation(new PostcodeLocation("76332"));

        List<Offer> list = new ArrayList<>();
        list.add(offerOne);
        list.add(offerTwo);
        list.add(offerThree);
        list.add(offerFour);
        list.add(offerFive);

        // Execution
        OfferComparatorMock comparator = new OfferComparatorMock(distance, location);

        // Assertions
        assertEquals(offerOne, list.get(0));
        assertEquals(offerTwo, list.get(1));
        assertEquals(offerThree, list.get(2));
        assertEquals(offerFour, list.get(3));
        assertEquals(offerFive, list.get(4));

        /**
         * Approx. distances from Karlsruhe to:
         * Stuttgart        - 62,48 km
         * München          - 252,90 km
         * Berlin Mitte     - 525,56 km
         * Hamburg          - 516,82 km
         * Bad Herrenalb    - 24,04 km
         */

        // Sort list from close to far
        Collections.sort(list, comparator);

        assertEquals(offerFive, list.get(0));
        assertEquals(offerOne, list.get(1));
        assertEquals(offerTwo, list.get(2));
        assertEquals(offerFour, list.get(3));
        assertEquals(offerThree, list.get(4));

        // Reverse list order from far to close
        Collections.sort(list, comparator.reversed());

        assertEquals(offerThree, list.get(0));
        assertEquals(offerFour, list.get(1));
        assertEquals(offerTwo, list.get(2));
        assertEquals(offerOne, list.get(3));
        assertEquals(offerFive, list.get(4));
    }

    @Test
    public void testDistanceUnlocalizable() {
        // Test data
        OfferComparableField distance = OfferComparableField.DISTANCE;
        Localizable location = new CityLocation("Karlsruhe");
        OfferFactory offerFactory = new OfferFactory();
        Offer offerOne = offerFactory.getValidObject();
        Offer offerTwo = offerFactory.getValidObject();
        Offer offerThree = offerFactory.getValidObject();
        Offer offerFour = offerFactory.getValidObject();
        Offer offerFive = offerFactory.getValidObject();
        offerOne.setLocation(new CityLocation("Stuttgart"));
        offerTwo.setLocation(new CityLocation("abc123"));
        offerThree.setLocation(new CityLocation("Berlin"));
        offerFour.setLocation(new CityLocation("Hamburg"));
        offerFive.setLocation(new CityLocation("Bad Herrenalb"));

        List<Offer> list = new ArrayList<>();
        list.add(offerOne);
        list.add(offerTwo);
        list.add(offerThree);
        list.add(offerFour);
        list.add(offerFive);

        // Execution
        OfferComparatorMock comparator = new OfferComparatorMock(distance, location);
        Collections.sort(list, comparator);

        // Assertions
        assertEquals(offerFive, list.get(0));
        assertEquals(offerOne, list.get(1));
        assertEquals(offerFour, list.get(2));
        assertEquals(offerThree, list.get(3));
        assertEquals(offerTwo, list.get(4));
    }

    @Test
    public void testParticipants() {
        // Test data
        OfferComparableField participants = OfferComparableField.PARTICIPANTS;
        Localizable location = new LocationFactory().getValidObject();
        UserFactory userFactory = new UserFactory();
        User user1 = userFactory.getValidObject();
        User user2 = userFactory.getValidObject();
        User user3 = userFactory.getValidObject();
        User user4 = userFactory.getValidObject();
        User user5 = userFactory.getValidObject();
        User user6 = userFactory.getValidObject();
        User user7 = userFactory.getValidObject();
        User user8 = userFactory.getValidObject();
        User user9 = userFactory.getValidObject();
        User user10 = userFactory.getValidObject();
        // Every offer has an initial participant amount of OfferFactory.AMOUNT_PARTICIPANTS
        OfferFactory offerFactory = new OfferFactory();
        Offer offerOne = offerFactory.getValidObject();
        Offer offerTwo = offerFactory.getValidObject();
        Offer offerThree = offerFactory.getValidObject();
        Offer offerFour = offerFactory.getValidObject();
        Offer offerFive = offerFactory.getValidObject();
        offerOne.addParticipant(user1);
        offerOne.addParticipant(user2);
        offerOne.addParticipant(user3);
        offerOne.addParticipant(user4);
        offerTwo.addParticipant(user5);
        offerTwo.addParticipant(user6);
        offerTwo.addParticipant(user7);
        offerThree.addParticipant(user8);
        offerThree.addParticipant(user9);
        offerFour.addParticipant(user10);

        List<Offer> list = new ArrayList<>();
        list.add(offerOne);
        list.add(offerTwo);
        list.add(offerThree);
        list.add(offerFour);
        list.add(offerFive);

        // Execution
        OfferComparatorMock comparator = new OfferComparatorMock(participants, location);

        // Assertions
        assertEquals(offerOne, list.get(0));
        assertEquals(offerTwo, list.get(1));
        assertEquals(offerThree, list.get(2));
        assertEquals(offerFour, list.get(3));
        assertEquals(offerFive, list.get(4));

        // Sort list from low to high participation
        Collections.sort(list, comparator);

        assertEquals(offerFive, list.get(0));
        assertEquals(offerFour, list.get(1));
        assertEquals(offerThree, list.get(2));
        assertEquals(offerTwo, list.get(3));
        assertEquals(offerOne, list.get(4));

        // Reverse list order from high to low participation
        Collections.sort(list, comparator.reversed());

        assertEquals(offerOne, list.get(0));
        assertEquals(offerTwo, list.get(1));
        assertEquals(offerThree, list.get(2));
        assertEquals(offerFour, list.get(3));
        assertEquals(offerFive, list.get(4));
    }

    @Test
    public void testRating() {
        // Test data
        OfferComparableField rating = OfferComparableField.RATING;
        Localizable location = new LocationFactory().getValidObject();
        OfferFactory offerFactory = new OfferFactory();
        Offer offerOne = offerFactory.getValidObject();
        Offer offerTwo = offerFactory.getValidObject();
        Offer offerThree = offerFactory.getValidObject();
        Offer offerFour = offerFactory.getValidObject();
        Offer offerFive = offerFactory.getValidObject();

        UserFactory userFactory = new UserFactory();
        // Average host rating: 3.6
        offerOne.getCreator().addRating(new Rating(RatingBasis.HOST, RatingValue.POINTS_5, userFactory.getValidObject()));
        offerOne.getCreator().addRating(new Rating(RatingBasis.HOST, RatingValue.POINTS_2, userFactory.getValidObject()));
        offerOne.getCreator().addRating(new Rating(RatingBasis.HOST, RatingValue.POINTS_5, userFactory.getValidObject()));
        offerOne.getCreator().addRating(new Rating(RatingBasis.HOST, RatingValue.POINTS_1, userFactory.getValidObject()));
        offerOne.getCreator().addRating(new Rating(RatingBasis.HOST, RatingValue.POINTS_5, userFactory.getValidObject()));
        // Average host rating: 3.4
        offerTwo.getCreator().addRating(new Rating(RatingBasis.HOST, RatingValue.POINTS_4, userFactory.getValidObject()));
        offerTwo.getCreator().addRating(new Rating(RatingBasis.HOST, RatingValue.POINTS_4, userFactory.getValidObject()));
        offerTwo.getCreator().addRating(new Rating(RatingBasis.HOST, RatingValue.POINTS_2, userFactory.getValidObject()));
        offerTwo.getCreator().addRating(new Rating(RatingBasis.HOST, RatingValue.POINTS_4, userFactory.getValidObject()));
        offerTwo.getCreator().addRating(new Rating(RatingBasis.HOST, RatingValue.POINTS_3, userFactory.getValidObject()));
        // Average host rating: 2.2
        offerThree.getCreator().addRating(new Rating(RatingBasis.HOST, RatingValue.POINTS_3, userFactory.getValidObject()));
        offerThree.getCreator().addRating(new Rating(RatingBasis.HOST, RatingValue.POINTS_3, userFactory.getValidObject()));
        offerThree.getCreator().addRating(new Rating(RatingBasis.HOST, RatingValue.POINTS_1, userFactory.getValidObject()));
        offerThree.getCreator().addRating(new Rating(RatingBasis.HOST, RatingValue.POINTS_3, userFactory.getValidObject()));
        offerThree.getCreator().addRating(new Rating(RatingBasis.HOST, RatingValue.POINTS_1, userFactory.getValidObject()));
        // Average host rating: 3.0
        offerFour.getCreator().addRating(new Rating(RatingBasis.HOST, RatingValue.POINTS_2, userFactory.getValidObject()));
        offerFour.getCreator().addRating(new Rating(RatingBasis.HOST, RatingValue.POINTS_2, userFactory.getValidObject()));
        offerFour.getCreator().addRating(new Rating(RatingBasis.HOST, RatingValue.POINTS_5, userFactory.getValidObject()));
        offerFour.getCreator().addRating(new Rating(RatingBasis.HOST, RatingValue.POINTS_4, userFactory.getValidObject()));
        offerFour.getCreator().addRating(new Rating(RatingBasis.HOST, RatingValue.POINTS_2, userFactory.getValidObject()));
        // Average host rating: 1.2
        offerFive.getCreator().addRating(new Rating(RatingBasis.HOST, RatingValue.POINTS_1, userFactory.getValidObject()));
        offerFive.getCreator().addRating(new Rating(RatingBasis.HOST, RatingValue.POINTS_1, userFactory.getValidObject()));
        offerFive.getCreator().addRating(new Rating(RatingBasis.HOST, RatingValue.POINTS_1, userFactory.getValidObject()));
        offerFive.getCreator().addRating(new Rating(RatingBasis.HOST, RatingValue.POINTS_2, userFactory.getValidObject()));
        offerFive.getCreator().addRating(new Rating(RatingBasis.HOST, RatingValue.POINTS_1, userFactory.getValidObject()));

        List<Offer> list = new ArrayList<>();
        list.add(offerOne);
        list.add(offerTwo);
        list.add(offerThree);
        list.add(offerFour);
        list.add(offerFive);

        // Execution
        OfferComparatorMock comparator = new OfferComparatorMock(rating, location);

        // Assertions
        assertEquals(offerOne, list.get(0));
        assertEquals(offerTwo, list.get(1));
        assertEquals(offerThree, list.get(2));
        assertEquals(offerFour, list.get(3));
        assertEquals(offerFive, list.get(4));

        // Sort list from low to high participation
        Collections.sort(list, comparator);

        assertEquals(offerFive, list.get(0));
        assertEquals(offerThree, list.get(1));
        assertEquals(offerFour, list.get(2));
        assertEquals(offerTwo, list.get(3));
        assertEquals(offerOne, list.get(4));

        // Sort list from high to low participation
        Collections.sort(list, comparator.reversed());

        assertEquals(offerOne, list.get(0));
        assertEquals(offerTwo, list.get(1));
        assertEquals(offerFour, list.get(2));
        assertEquals(offerThree, list.get(3));
        assertEquals(offerFive, list.get(4));
    }
}
