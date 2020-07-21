package meet_eat.data.entity;

import java.util.Objects;

public abstract class Entity {

    private static final String ERROR_MESSAGE_TEMPLATE_NULL = "The %s must not be null.";
    private static final String ERROR_MESSAGE_NULL_IDENTIFIER = String.format(ERROR_MESSAGE_TEMPLATE_NULL, "identifier");

    private final String identifier;

    protected Entity() {
        this.identifier = null;
    }

    protected Entity(String identifier) {
        this.identifier = Objects.requireNonNull(identifier, ERROR_MESSAGE_NULL_IDENTIFIER);
    }

    public String getIdentifier() {
        return identifier;
    }
}