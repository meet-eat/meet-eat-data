package meet_eat.data.entity;

import meet_eat.data.entity.user.User;
import meet_eat.data.factory.TokenFactory;
import meet_eat.data.factory.UserFactory;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertFalse;

public class TokenCommonTest {

    @Test
    public void testConstructorWithUserAndValue() {
        // Test data
        UserFactory userFactory = new UserFactory();
        User user = userFactory.getValidObject();
        String value = "ThisIsAValue";

        // Execution
        Token token = new Token(user, value);

        // Assertions
        assertNotNull(token);
    }

    @Test
    public void testConstructor() {
        // Execution
        Token token = new TokenFactory().getValidObject();

        // Assertion
        assertNotNull(token);
    }

    @Ignore
    @Test(expected = NullPointerException.class)
    public void testConstructorNullIdentifier() {
        // Test data
        UserFactory userFactory = new UserFactory();
        User user = userFactory.getValidObject();
        String value = "ACrazyValue";

        // Execution
        Token token = new Token(null, user, value);
    }

    @Test(expected = NullPointerException.class)
    public void testConstructorNullUser() {
        // Test data
        String identifier = "AnIdentifier";
        String value = "ValueIsNotNull";

        // Execution
        Token token = new Token(identifier, null, value);
    }

    @Test(expected = NullPointerException.class)
    public void testConstructorNullValue() {
        // Test data
        String identifier = "AnotherIdentifier";
        UserFactory userFactory = new UserFactory();
        User user = userFactory.getValidObject();

        // Execution
        Token token = new Token(identifier, user, null);
    }

    @Test
    public void testTokenNotEqual() {
        // Execution
        TokenFactory tokenFactory = new TokenFactory();
        Token token1 = tokenFactory.getValidObject();
        Token token2 = tokenFactory.getValidObject();

        // Assertion
        assertFalse(token1.equals(token2));
    }
}
