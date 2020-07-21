package meet_eat.data.entity.user;

import java.util.Objects;
import java.util.function.Function;

public class Password {

    /**
     * Password properties:
     * <p>
     * <ul>
     * <li> At least one lower-/uppercase letter
     * <li> At least one digit
     * <li> At least one special character
     * <li> Minimum|Maximum length of 8|32 characters
     * </ul>
     * <p>
     */
    private static final String REGEX_PASSWORD = "^(?=.*[a-z])"
            + "(?=.*[A-Z])"
            + "(?=.*\\d)"
            + "(?=.*[!#$%&*_+,-./:;'<=>?@^|~(){}])"
            + ".{8,32}$";
    private static final String ERROR_MESSAGE_ILLEGAL_PASSWORD = "The password must comply with password guidelines.";
    private static final Function<String, String> HASH_FUNCTION = new Function<String, String>() {
        @Override
        public String apply(String s) {
            throw new UnsupportedOperationException("Method not implemented.");
        }
    };

    private final String hash;

    public Password(String password) {
        if (!isLegalPassword(password)) {
            throw new IllegalArgumentException(ERROR_MESSAGE_ILLEGAL_PASSWORD);
        }
        hash = HASH_FUNCTION.apply(password);
    }

    public String getHash() {
        return hash;
    }

    public static boolean isLegalPassword(String password) {
        if (Objects.isNull(password)) {
            return false;
        }
        return password.matches(REGEX_PASSWORD);
    }
}
