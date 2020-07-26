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
}
