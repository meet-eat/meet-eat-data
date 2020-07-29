package meet_eat.data.entity.user;

import static org.junit.Assert.*;
import org.junit.Test;

public class PasswordCommonTest {

    @Test
    public void testCreatePassword() {
        // Test data
        String passwordValue = "aAbcdefghijk!123";

        // Execution
        Password password = Password.createHashedPassword(passwordValue);

        // Assertions
        assertNotNull(password);
        assertNotNull(password.getHash());
        assertNotEquals(0, password.getHash().length());
    }

    @Test
    public void testDerivePassword() {
        // Test data
        String passwordValue = "aAbcdefghijk!123";
        String salt = "ABC12345!";
        int iterations = 100000;

        // Execution
        Password password = Password.createHashedPassword(passwordValue);
        Password derivedPassword = password.derive(salt, iterations);

        // Assertions
        assertNotNull(password);
        assertNotNull(password.getHash());
        assertNotNull(derivedPassword);
        assertNotNull(derivedPassword.getHash());
        assertNotNull(derivedPassword.getSalt());
        assertNotNull(derivedPassword.getIterations());
        assertNotEquals(0, derivedPassword.getHash().length());
        assertTrue(password.matches(derivedPassword));
    }

    @Test
    public void testDerivePasswordMultipleDerivations() {
        // Test data
        String passwordValue = "aAbcdefghijk!123";
        String salt = "ABC12345!";
        int iterations = 100000;

        // Execution
        Password password = Password.createHashedPassword(passwordValue);
        Password derivedPasswordFst = password.derive(salt, iterations);
        Password derivedPasswordSnd = password.derive(salt, iterations);

        // Assertions
        assertNotEquals(derivedPasswordFst, derivedPasswordSnd);
        assertTrue(password.matches(derivedPasswordFst));
        assertTrue(password.matches(derivedPasswordSnd));
    }

    @Test
    public void testGenerateSaltRandomness() {
        // Execution
        String salt = Password.generateSalt();

        // Assertions
        assertNotEquals(Password.generateSalt(), salt);
    }

    @Test
    public void testGenerateSaltNotNull() {
        // Execution
        String salt = Password.generateSalt();

        // Assertions
        assertNotNull(salt);
        assertNotEquals(0, salt.length());
    }
}
