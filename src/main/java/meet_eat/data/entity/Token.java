package meet_eat.data.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import meet_eat.data.entity.user.User;

import java.util.Objects;

public class Token extends Entity<String> {

    private static final String ERROR_MESSAGE_TEMPLATE_NULL = "The %s must not be null.";
    private static final String ERROR_MESSAGE_NULL_USER = String.format(ERROR_MESSAGE_TEMPLATE_NULL, "user");
    private static final String ERROR_MESSAGE_NULL_VALUE = String.format(ERROR_MESSAGE_TEMPLATE_NULL, "value");

    @JsonProperty
    private final User user;
    @JsonProperty
    private final String value;

    public Token(User user, String value) {
        this.user = Objects.requireNonNull(user, ERROR_MESSAGE_NULL_USER);
        this.value = Objects.requireNonNull(value, ERROR_MESSAGE_NULL_VALUE);
    }

    @JsonCreator
    public Token(@JsonProperty("identifier") String identifier,
                 @JsonProperty("user") User user,
                 @JsonProperty("value") String value) {
        super(identifier);
        this.user = Objects.requireNonNull(user, ERROR_MESSAGE_NULL_USER);
        this.value = Objects.requireNonNull(value, ERROR_MESSAGE_NULL_VALUE);
    }

    @JsonGetter
    public User getUser() {
        return user;
    }

    @JsonGetter
    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Token token = (Token) o;
        return Objects.equals(user, token.user) && Objects.equals(value, token.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), user, value);
    }
}