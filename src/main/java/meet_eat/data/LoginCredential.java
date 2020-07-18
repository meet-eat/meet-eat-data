package meet_eat.data;

import meet_eat.data.entity.user.Password;

import java.util.Objects;

public class LoginCredential {

    private static final String ERROR_MESSAGE_TEMPLATE_NULL = "The %s must not be null.";
    private static final String ERROR_MESSAGE_NULL_EMAIL = String.format(ERROR_MESSAGE_TEMPLATE_NULL, "email address");
    private static final String ERROR_MESSAGE_NULL_PASSWORD = String.format(ERROR_MESSAGE_TEMPLATE_NULL, "password");
    private static final String ERROR_MESSAGE_ILLEGAL_EMAIL = "The email address must comply with RFC 5322.";
    private static final String REGEX_EMAIL = "(?:[a-z0-9!#$%&'*+\\/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+\\/=?^_`{|}~-]+)"
            + "*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")"
            + "@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:(2(5[0-5]|[0-4][0-9])"
            + "|1[0-9][0-9]|[1-9]?[0-9]))\\.){3}(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9])"
            + "|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";

    private final String email;
    private final Password password;

    public LoginCredential(String email, Password password) {
        Objects.requireNonNull(email, ERROR_MESSAGE_NULL_EMAIL);
        Objects.requireNonNull(password, ERROR_MESSAGE_NULL_PASSWORD);

        if (!isLegalEmailAddress(email)) {
            throw new IllegalArgumentException(ERROR_MESSAGE_ILLEGAL_EMAIL);
        }

        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public Password getPassword() {
        return password;
    }

    public static boolean isLegalEmailAddress(String email) {
        return email.matches(REGEX_EMAIL);
    }
}