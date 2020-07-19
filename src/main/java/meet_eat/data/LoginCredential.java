package meet_eat.data;

import meet_eat.data.entity.user.Email;
import meet_eat.data.entity.user.Password;

import java.util.Objects;

public class LoginCredential {

    private static final String ERROR_MESSAGE_TEMPLATE_NULL = "The %s must not be null.";
    private static final String ERROR_MESSAGE_NULL_EMAIL = String.format(ERROR_MESSAGE_TEMPLATE_NULL, "email address");
    private static final String ERROR_MESSAGE_NULL_PASSWORD = String.format(ERROR_MESSAGE_TEMPLATE_NULL, "password");

    private final Email email;
    private final Password password;

    public LoginCredential(Email email, Password password) {
        Objects.requireNonNull(email, ERROR_MESSAGE_NULL_EMAIL);
        Objects.requireNonNull(password, ERROR_MESSAGE_NULL_PASSWORD);

        this.email = email;
        this.password = password;
    }

    public Email getEmail() {
        return email;
    }

    public Password getPassword() {
        return password;
    }
}