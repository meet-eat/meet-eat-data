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
        assertEquals(email, email);
        assertNotEquals(null, email);
        assertNotEquals(email, new Object());
        assertEquals(email, emailCopy);
        assertEquals(email.hashCode(), emailCopy.hashCode());
    }
}
