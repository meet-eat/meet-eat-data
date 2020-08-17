package meet_eat.data.entity;

import meet_eat.data.entity.user.User;
import meet_eat.data.factory.OfferFactory;
import meet_eat.data.factory.UserFactory;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ParticipationCommonTest {

    @Test
    public void testConstructor() {
        // Test data
        User participant = new UserFactory().getValidObject();
        Offer offer = new OfferFactory().getValidObject();

        // Execution
        Participation participation = new Participation(participant, offer);

        // Assertions
        assertNotNull(participation);
        assertNotNull(participation.getParticipant());
        assertNotNull(participation.getOffer());
        assertEquals(participant, participation.getParticipant());
        assertEquals(offer, participation.getOffer());
    }

    @Test(expected = NullPointerException.class)
    public void testConstructorNullParticipant() {
        // Test data
        Offer offer = new OfferFactory().getValidObject();

        // Execution
        Participation participation = new Participation(null, offer);
    }

    @Test(expected = NullPointerException.class)
    public void testConstructorNullOffer() {
        // Test data
        User participant = new UserFactory().getValidObject();

        // Execution
        Participation participation = new Participation(participant, null);
    }

    @Test
    public void testJsonConstructor() {
        // Test data
        String identifier = "gj029jsa";
        User participant = new UserFactory().getValidObject();
        Offer offer = new OfferFactory().getValidObject();

        // Execution
        Participation participation = new Participation(identifier, participant, offer);

        // Assertions
        assertNotNull(participation);
        assertNotNull(participation.getIdentifier());
        assertNotNull(participation.getParticipant());
        assertNotNull(participation.getOffer());
        assertEquals(identifier, participation.getIdentifier());
        assertEquals(participant, participation.getParticipant());
        assertEquals(offer, participation.getOffer());
    }

    @Test(expected = NullPointerException.class)
    public void testJsonConstructorNullParticipant() {
        // Test data
        String identifier = "gj029jsa";
        Offer offer = new OfferFactory().getValidObject();

        // Execution
        Participation participation = new Participation(identifier, null, offer);
    }

    @Test(expected = NullPointerException.class)
    public void testJsonConstructorNullOffer() {
        // Test data
        String identifier = "gj029jsa";
        User participant = new UserFactory().getValidObject();

        // Execution
        Participation participation = new Participation(identifier, participant, null);
    }
}
