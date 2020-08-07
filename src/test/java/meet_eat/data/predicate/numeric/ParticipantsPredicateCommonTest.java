package meet_eat.data.predicate.numeric;

import meet_eat.data.entity.Offer;
import meet_eat.data.factory.OfferFactory;
import meet_eat.data.factory.UserFactory;
import org.junit.Test;

import static org.junit.Assert.*;

public class ParticipantsPredicateCommonTest {

    @Test
    public void testConstructor() {
        // Test data
        DoubleOperation operation = DoubleOperation.EQUAL;
        double reference = 1d;

        // Execution
        ParticipantsPredicate participantsPredicate = new ParticipantsPredicate(operation, reference);

        // Assertions
        assertNotNull(participantsPredicate);
        assertNotNull(participantsPredicate.getOperation());
        assertNotNull(participantsPredicate.getReferenceValue());
    }

    @Test(expected = NullPointerException.class)
    public void testConstructorWithNullOperation() {
        // Test data
        double reference = 1d;

        // Execution
        ParticipantsPredicate participantsPredicate = new ParticipantsPredicate(null, reference);
    }

    @Test
    public void testOperate() {
        // Test data
        DoubleOperation operation = DoubleOperation.EQUAL;
        double reference = 2d;
        OfferFactory offerFactory = new OfferFactory();
        Offer offerOne = offerFactory.getValidObject();
        Offer offerTwo = offerFactory.getValidObject();

        UserFactory userFactory = new UserFactory();
        offerOne.addParticipant(userFactory.getValidObject());

        // Execution
        ParticipantsPredicate participantsPredicate = new ParticipantsPredicate(operation, reference);

        // Assertions
        assertFalse(participantsPredicate.test(offerOne));
        assertTrue(participantsPredicate.test(offerTwo));
    }
}
