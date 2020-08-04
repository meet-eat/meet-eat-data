package meet_eat.data.entity.user.contact;

import meet_eat.data.entity.user.User;
import meet_eat.data.factory.UserFactory;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ContactRequestCommonTest {

    @Test
    public void testConstructor() {
        // Test data
        UserFactory userFactory = new UserFactory();
        User requester = userFactory.getValidObject();
        User requestedUser = userFactory.getValidObject();

        // Execution
        ContactRequest contactRequest = new ContactRequest(requester, requestedUser);

        // Assertions
        assertNotNull(contactRequest);
        assertEquals(requester, contactRequest.getRequester());
        assertEquals(requestedUser, contactRequest.getRequestedUser());
    }

    @Test(expected = NullPointerException.class)
    public void testConstructorWithNullRequesterAndRequestedUser() {
        // Test data
        UserFactory userFactory = new UserFactory();
        User requestedUser = userFactory.getValidObject();

        // Execution
        ContactRequest contactRequest = new ContactRequest(null, requestedUser);
    }

    @Test(expected = NullPointerException.class)
    public void testConstructorWithRequesterAndNullRequestedUser() {
        // Test data
        UserFactory userFactory = new UserFactory();
        User requester = userFactory.getValidObject();

        // Execution
        ContactRequest contactRequest = new ContactRequest(requester, null);
    }
}
