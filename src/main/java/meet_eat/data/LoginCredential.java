package meet_eat.data;

import java.util.Objects;

public class LoginCredential {
    
    private static final String ERROR_MESSAGE_TEMPLATE_NULL = "The %s must not be null.";
    private static final String ERROR_MESSAGE_NULL_EMAIL = String.format(ERROR_MESSAGE_TEMPLATE_NULL, "email address");
    private static final String ERROR_MESSAGE_NULL_PASSWORD = String.format(ERROR_MESSAGE_TEMPLATE_NULL, "password");
    private static final String ERROR_MESSAGE_ILLEGAL_EMAIL = "The email address must comply with RFC 5322.";
    private static final String ERROR_MESSAGE_ILLEGAL_PASSWORD = "The password must comply with the password gui.";
    private static final String REGEX_EMAIL = "";
    private static final String REGEX_PASSWORD = "";

    private final String email;
    private final String password;

    public LoginCredential(String email, String password) {
        Objects.requireNonNull(email, ERROR_MESSAGE_NULL_EMAIL);
        Objects.requireNonNull(password, ERROR_MESSAGE_NULL_PASSWORD);

        if (!isValidEmailAddress(email)) {
            throw new IllegalArgumentException(ERROR_MESSAGE_ILLEGAL_EMAIL);
        } else if (!isValidPassword(password)) {
            throw new IllegalArgumentException(ERROR_MESSAGE_ILLEGAL_PASSWORD);
        }

        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    private boolean isValidEmailAddress(String email) {
        return email.matches(REGEX_EMAIL);
    }

    private boolean isValidPassword(String password) {
        return password.matches(REGEX_PASSWORD);
    }

}