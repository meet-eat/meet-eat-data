package meet_eat.data.entity;

import java.util.Objects;

public class Tag extends Entity<String> {

    private static final String ERROR_MESSAGE_TEMPLATE_NULL = "The %s must not be null.";
    private static final String ERROR_MESSAGE_NULL_NAME = String.format(ERROR_MESSAGE_TEMPLATE_NULL, "name");

    private String name;

    public Tag(String name) {
        this.name = Objects.requireNonNull(name, ERROR_MESSAGE_NULL_NAME);
    }

    public Tag(String identifier, String name) {
        super(identifier);
        this.name = Objects.requireNonNull(name, ERROR_MESSAGE_NULL_NAME);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = Objects.requireNonNull(name, ERROR_MESSAGE_NULL_NAME);
    }
}