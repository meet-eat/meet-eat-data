package meet_eat.data.predicate.numeric;

import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class RatingPredicateCommonTest {

    @Test
    public void testConstructor() {
        // Test data
        DoubleOperation operation = DoubleOperation.EQUAL;
        double reference = 1d;

        // Execution
        RatingPredicate ratingPredicate = new RatingPredicate(operation, reference);

        // Assertions
        assertNotNull(ratingPredicate);
        assertNotNull(ratingPredicate.getOperation());
        assertNotNull(ratingPredicate.getReferenceValue());
    }

    @Test(expected = NullPointerException.class)
    public void testConstructorWithNullOperation() {
        // Test data
        double reference = 1d;

        // Execution
        new RatingPredicate(null, reference);
    }

    @Test(expected = NullPointerException.class)
    public void testConstructorWithNullReferenceValue() {
        // Test data
        DoubleOperation operation = DoubleOperation.EQUAL;

        // Execution
        new RatingPredicate(operation, null);
    }

    @Ignore
    @Test
    public void testOperate() {
        // Test data
        DoubleOperation operation = DoubleOperation.LESS;
        double reference = 3.5;

        /*

        OfferFactory offerFactory = new OfferFactory();
        Offer offerOne = offerFactory.getValidObject();
        Offer offerTwo = offerFactory.getValidObject();

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

        // Execution
        RatingPredicate ratingPredicate = new RatingPredicate(operation, reference);

        // Assertions
        assertFalse(ratingPredicate.test(offerOne));
        assertTrue(ratingPredicate.test(offerTwo));

        */
    }
}
