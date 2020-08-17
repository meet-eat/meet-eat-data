package meet_eat.data.entity;

import meet_eat.data.entity.user.User;
import meet_eat.data.factory.UserFactory;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class SubscriptionCommonTest {

    @Test
    public void testConstructor() {
        // Test data
        UserFactory userFactory = new UserFactory();
        User source = userFactory.getValidObject();
        User target = userFactory.getValidObject();

        // Execution
        Subscription subscription = new Subscription(source, target);

        // Assertions
        assertNotNull(subscription);
        assertNotNull(subscription.getSourceUser());
        assertNotNull(subscription.getTargetUser());
        assertEquals(source, subscription.getSourceUser());
        assertEquals(target, subscription.getTargetUser());
    }

    @Test(expected = NullPointerException.class)
    public void testConstructorNullSourceUser() {
        // Test data
        UserFactory userFactory = new UserFactory();
        User target = userFactory.getValidObject();

        // Execution
        Subscription subscription = new Subscription(null, target);
    }

    @Test(expected = NullPointerException.class)
    public void testConstructorNullTargetUser() {
        // Test data
        UserFactory userFactory = new UserFactory();
        User source = userFactory.getValidObject();

        // Execution
        Subscription subscription = new Subscription(source, null);
    }

    @Test
    public void testJsonConstructor() {
        // Test data
        String identifier = "vns23h0ds";
        UserFactory userFactory = new UserFactory();
        User source = userFactory.getValidObject();
        User target = userFactory.getValidObject();

        // Execution
        Subscription subscription = new Subscription(identifier, source, target);

        // Assertions
        assertNotNull(subscription);
        assertNotNull(subscription.getIdentifier());
        assertNotNull(subscription.getSourceUser());
        assertNotNull(subscription.getTargetUser());
        assertEquals(identifier, subscription.getIdentifier());
        assertEquals(source, subscription.getSourceUser());
        assertEquals(target, subscription.getTargetUser());
    }

    @Test(expected = NullPointerException.class)
    public void testJsonConstructorNullSourceUser() {
        // Test data
        String identifier = "vns23h0ds";
        UserFactory userFactory = new UserFactory();
        User target = userFactory.getValidObject();

        // Execution
        Subscription subscription = new Subscription(identifier, null, target);
    }

    @Test(expected = NullPointerException.class)
    public void testJsonConstructorNullTargetUser() {
        // Test data
        String identifier = "vns23h0ds";
        UserFactory userFactory = new UserFactory();
        User source = userFactory.getValidObject();

        // Execution
        Subscription subscription = new Subscription(identifier, source, null);
    }
}
