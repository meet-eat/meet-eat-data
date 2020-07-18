package meet_eat.data;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class LoginCredentialPasswordTest {

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                {"abcdefghijk", false},
                {"password", false},
                {"Passwort1234", false},
                {"IchBinEinGlücklichesPasswort1!", true},
                {" ", false},
                {"", false},
                {"Test Test !234", false},
                {"12345678!", false},
                {"Einvalides!Passwort234", true},
                {"A!156aB", false},
                {"A!156aBc", true},
                {"aA!1eeeeeeeeeeeeeeeeeeeeeeeeeeee", true},
                {"aA!1eeeeeeeeeeeeeeeeeeeeeeeeeeeee", false},
                {"lowerUpperNumber1Special!", true},
                {"?lowerUpperNumber1Special!", true},
                {"123?lowerUpperNumber1Special!", true},
                {"123?lowerUpperNumber1Special!456", true},
                {"_123?lowerUpperN1Special!456", true},
                {"_1AbCdEfG", false},
                {"_1AbCdEf!", true}
        });
    }

    private final String password;
    private final boolean isValid;

    public LoginCredentialPasswordTest(String password, boolean isValid) {
        this.password = password;
        this.isValid = isValid;
    }

    @Test
    public void testIsLegalPassword() {
        // Assertions
        assertEquals(LoginCredential.isLegalPassword(this.password), this.isValid);
    }
}