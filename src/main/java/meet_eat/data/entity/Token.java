package meet_eat.data.entity;

import meet_eat.data.entity.user.User;

import java.util.Objects;

public class Token extends Entity {

    private static final String ERROR_MESSAGE_TEMPLATE_NULL = "The %s must not be null.";
    private static final String ERROR_MESSAGE_NULL_USER = String.format(ERROR_MESSAGE_TEMPLATE_NULL, "user");
    private static final String ERROR_MESSAGE_NULL_VALUE = String.format(ERROR_MESSAGE_TEMPLATE_NULL, "value");

    private final User user;
    private final String value;

    public Token(User user, String value) {
        this.user = Objects.requireNonNull(user, ERROR_MESSAGE_NULL_USER);
        this.value = Objects.requireNonNull(value, ERROR_MESSAGE_NULL_VALUE);
    }

    public Token(String identifier, User user, String value) {
        super(identifier);
        this.user = Objects.requireNonNull(user, ERROR_MESSAGE_NULL_USER);
        this.value = Objects.requireNonNull(value, ERROR_MESSAGE_NULL_VALUE);
    }

    public User getUser() {
        return user;
    }

    public String getValue() {
        return value;
    }
}