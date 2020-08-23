package meet_eat.data;

import meet_eat.data.entity.user.Email;
import meet_eat.data.entity.user.Password;
import meet_eat.data.factory.EmailFactory;
import meet_eat.data.factory.PasswordFactory;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

public class LoginCredentialCommonTest {

    @Test
    public void testConstructor() {
        // Test data
        String testEmail = "test@example.com";
        Email email = new Email(testEmail);
        String testPassword = "MySpecial*Password1";
        Password password = Password.createHashedPassword(testPassword);

        // Execution
        LoginCredential loginCredential = new LoginCredential(email, password);

        // Assertions
        assertEquals(email, loginCredential.getEmail());
        assertEquals(password, loginCredential.getPassword());
    }

    @Test(expected = NullPointerException.class)
    public void testConstructorNullEmail() {
        // Test data
        String testPassword = "MySpecial*Password1";
        Password password = Password.createHashedPassword(testPassword);

        // Execution
        LoginCredential loginCredential = new LoginCredential(null, password);
    }

    @Test(expected = NullPointerException.class)
    public void testConstructorNullPassword() {
        // Test data
        String testEmail = "test@example.com";
        Email email = new Email(testEmail);

        // Execution
        LoginCredential loginCredential = new LoginCredential(email, null);
    }

    @Test
    public void testEquals() {
        // Execution
        LoginCredential loginCredential = new LoginCredential(new EmailFactory().getValidObject(), new PasswordFactory().getValidObject());
        LoginCredential loginCredentialCopy = new LoginCredential(loginCredential.getEmail(), loginCredential.getPassword());

        // Assertions
        assertEquals(loginCredential, loginCredential);
        assertNotEquals(null, loginCredential);
        assertNotEquals(loginCredential, new Object());
        assertEquals(loginCredential, loginCredentialCopy);
        assertEquals(loginCredential.hashCode(), loginCredentialCopy.hashCode());
    }
}
