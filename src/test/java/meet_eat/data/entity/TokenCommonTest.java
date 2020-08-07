package meet_eat.data.entity;

import meet_eat.data.entity.user.User;
import meet_eat.data.factory.TokenFactory;
import meet_eat.data.factory.UserFactory;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.*;

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

    @Test
    public void testConstructorNullIdentifier() {
        // Test data
        UserFactory userFactory = new UserFactory();
        User user = userFactory.getValidObject();
        String value = "ACrazyValue";

        // Execution
        Token token = new Token(null, user, value);

        // Assertion
        assertNotNull(token);
        assertNull(token.getIdentifier());
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

    @Test
    public void testEquals() {
        // Execution
        Token token = new TokenFactory().getValidObject();
        Token tokenCopy = new Token(token.getIdentifier(), token.getUser(), token.getValue());

        // Assertions
        assertTrue(token.equals(token));
        assertFalse(token.equals(null));
        assertFalse(token.equals(new Object()));
        assertTrue(token.equals(tokenCopy));
        assertEquals(token.hashCode(), tokenCopy.hashCode());
    }
}
