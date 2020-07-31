package meet_eat.data;

import meet_eat.data.entity.user.Email;
import meet_eat.data.entity.user.Password;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LoginCredentialCommonTest {

    @Test
    public void testConstructor() {
        // Test data
        String testEmail = "test@meet-eat.com";
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
        String testEmail = "test@meet.eat.com";
        Email email = new Email(testEmail);

        // Execution
        LoginCredential loginCredential = new LoginCredential(email, null);
    }
}
