package meet_eat.data.entity.user;

import meet_eat.data.factory.EmailFactory;
import org.junit.Test;

import static org.junit.Assert.*;

public class EmailCommonTest {

    @Test
    public void testToString() {
        // Test data
        String emailAddress = "test@example.com";

        // Execution
        Email email = new Email(emailAddress);

        // Assertions
        assertEquals(emailAddress, new Email(emailAddress).toString());
    }

    @Test
    public void testEquals() {
        // Execution
        Email email = new EmailFactory().getValidObject();
        Email emailCopy = new Email(email.toString());

        // Assertions
        assertTrue(email.equals(email));
        assertFalse(email.equals(null));
        assertFalse(email.equals(new Object()));
        assertTrue(email.equals(emailCopy));
        assertEquals(email.hashCode(), emailCopy.hashCode());
    }
}
