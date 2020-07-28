package meet_eat.data.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.PersistenceConstructor;

import java.util.Objects;

public class Tag extends Entity<String> {

    private static final String ERROR_MESSAGE_TEMPLATE_NULL = "The %s must not be null.";
    private static final String ERROR_MESSAGE_NULL_NAME = String.format(ERROR_MESSAGE_TEMPLATE_NULL, "name");

    @JsonProperty
    private String name;

    public Tag(String name) {
        this.name = Objects.requireNonNull(name, ERROR_MESSAGE_NULL_NAME);
    }

    @JsonCreator
    @PersistenceConstructor
    public Tag(@JsonProperty("identifier") String identifier, @JsonProperty("name") String name) {
        super(identifier);
        this.name = Objects.requireNonNull(name, ERROR_MESSAGE_NULL_NAME);
    }

    @JsonGetter
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = Objects.requireNonNull(name, ERROR_MESSAGE_NULL_NAME);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Tag tag = (Tag) o;
        return Objects.equals(name, tag.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name);
    }
}