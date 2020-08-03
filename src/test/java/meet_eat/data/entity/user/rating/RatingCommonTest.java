package meet_eat.data.entity.user.rating;

import meet_eat.data.entity.user.User;
import meet_eat.data.factory.UserFactory;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RatingCommonTest {

    @Test(expected = NullPointerException.class)
    public void testConstructorBasisNullBasis() {
        // Test data
        RatingValue value = RatingValue.POINTS_1;
        UserFactory userFactory = new UserFactory();
        User user = userFactory.getValidObject();

        // Execution
        Rating rating = new Rating(null, value, user);
    }

    @Test(expected = NullPointerException.class)
    public void testConstructorNullValue() {
        // Test data
        RatingBasis basis = RatingBasis.GUEST;
        UserFactory userFactory = new UserFactory();
        User user = userFactory.getValidObject();

        // Execution
        Rating rating = new Rating(basis, null, user);
    }

    @Test(expected = NullPointerException.class)
    public void testConstructorNullReviewer() {
        // Test data
        RatingBasis basis = RatingBasis.HOST;
        RatingValue value = RatingValue.POINTS_2;

        // Execution
        Rating rating = new Rating(basis, value, null);
    }

    @Test
    public void testGetter() {
        // Test data
        RatingBasis basis = RatingBasis.HOST;
        RatingValue value = RatingValue.POINTS_5;
        UserFactory userFactory = new UserFactory();
        User user = userFactory.getValidObject();

        // Execution
        Rating rating = new Rating(basis, value, user);

        // Assertions
        assertEquals(basis, rating.getBasis());
        assertEquals(value, rating.getValue());
        assertEquals(user, rating.getReviewer());
    }
}
