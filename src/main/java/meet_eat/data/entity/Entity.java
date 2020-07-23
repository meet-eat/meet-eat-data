package meet_eat.data.entity;

import java.util.Objects;

public abstract class Entity<U> {

    private static final String ERROR_MESSAGE_TEMPLATE_NULL = "The %s must not be null.";
    private static final String ERROR_MESSAGE_NULL_IDENTIFIER = String.format(ERROR_MESSAGE_TEMPLATE_NULL, "identifier");

    private final U identifier;

    protected Entity() {
        this.identifier = null;
    }

    protected Entity(U identifier) {
        this.identifier = Objects.requireNonNull(identifier, ERROR_MESSAGE_NULL_IDENTIFIER);
    }

    public U getIdentifier() {
        return identifier;
    }
}