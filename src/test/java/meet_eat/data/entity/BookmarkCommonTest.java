package meet_eat.data.entity;

import meet_eat.data.entity.user.User;
import meet_eat.data.factory.OfferFactory;
import meet_eat.data.factory.UserFactory;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class BookmarkCommonTest {

    @Test
    public void testConstructor() {
        // Test data
        User user = new UserFactory().getValidObject();
        Offer offer = new OfferFactory().getValidObject();

        // Execution
        Bookmark bookmark = new Bookmark(user, offer);

        // Assertions
        assertNotNull(bookmark);
        assertNotNull(bookmark.getUser());
        assertNotNull(bookmark.getOffer());
        assertEquals(user, bookmark.getUser());
        assertEquals(offer, bookmark.getOffer());
    }

    @Test(expected = NullPointerException.class)
    public void testConstructorNullUser() {
        // Test data
        Offer offer = new OfferFactory().getValidObject();

        // Execution
        Bookmark bookmark = new Bookmark(null, offer);
    }

    @Test(expected = NullPointerException.class)
    public void testConstructorNullOffer() {
        // Test data
        User user = new UserFactory().getValidObject();

        // Execution
        Bookmark bookmark = new Bookmark(user, null);
    }

    @Test
    public void testJsonConstructor() {
        // Test data
        String identifier = "9834zncf498";
        User user = new UserFactory().getValidObject();
        Offer offer = new OfferFactory().getValidObject();

        // Execution
        Bookmark bookmark = new Bookmark(identifier, user, offer);

        // Assertions
        assertNotNull(bookmark);
        assertNotNull(bookmark.getIdentifier());
        assertNotNull(bookmark.getUser());
        assertNotNull(bookmark.getOffer());
        assertEquals(identifier, bookmark.getIdentifier());
        assertEquals(user, bookmark.getUser());
        assertEquals(offer, bookmark.getOffer());
    }
}
