package meet_eat.data.factory;

import meet_eat.data.Report;
import meet_eat.data.entity.Offer;
import meet_eat.data.entity.Tag;
import meet_eat.data.entity.user.Email;
import meet_eat.data.entity.user.Password;
import meet_eat.data.entity.user.User;
import meet_eat.data.entity.user.rating.Rating;
import meet_eat.data.location.Localizable;
import meet_eat.data.location.UnlocalizableException;
import org.junit.*;

import static org.junit.Assert.assertFalse;

public class FactoryTest {

    @Test
    public void testEmailFactory() {
        // Execution
        EmailFactory emailFactory = new EmailFactory();
        Email email1 = emailFactory.getValidObject();
        Email email2 = emailFactory.getValidObject();

        // Assertions
        assertFalse(email1.equals(email2));
    }

    @Test
    public void testPasswordFactory() {
        // Execution
        PasswordFactory passwordFactory = new PasswordFactory();
        Password password1 = passwordFactory.getValidObject();
        Password password2 = passwordFactory.getValidObject();

        // Assertions
        assertFalse(password1.equals(password2));
    }

    @Test
    public void testLocationFactory() throws UnlocalizableException {
        // Execution
        LocationFactory locationFactory = new LocationFactory();
        Localizable location1 = locationFactory.getValidObject();
        Localizable location2 = locationFactory.getValidObject();

        // Assertions
        assertFalse(location1.equals(location2));
    }

    @Test
    public void testRatingFactory() {
        // Execution
        RatingFactory ratingFactory = new RatingFactory();
        Rating rating1 = ratingFactory.getValidObject();
        Rating rating2 = ratingFactory.getValidObject();

        // Assertions
        assertFalse(rating1.equals(rating2));
    }

    @Test
    public void testReportFactory() {
        // Execution
        ReportFactory reportFactory = new ReportFactory();
        Report report1 = reportFactory.getValidObject();
        Report report2 = reportFactory.getValidObject();

        // Assertions
        assertFalse(report1.equals(report2));
    }

    @Test
    public void testTagFactory() {
        // Execution
        TagFactory tagFactory = new TagFactory();
        Tag tag1 = tagFactory.getValidObject();
        Tag tag2 = tagFactory.getValidObject();

        // Assertions
        assertFalse(tag1.equals(tag2));
    }

    @Ignore
    @Test
    public void testUserFactory() {
        // Execution
        UserFactory userFactory = new UserFactory();
        User user1 = userFactory.getValidObject();
        User user2 = userFactory.getValidObject();

        // Assertions
        assertFalse(user1.equals(user2));
    }

    @Test
    public void testOfferFactory() throws UnlocalizableException {
        // Execution
        OfferFactory offerFactory = new OfferFactory();
        Offer offer1 = offerFactory.getValidObject();
        Offer offer2 = offerFactory.getValidObject();

        // Assertions
        assertFalse(offer1.equals(offer2));
    }
}
