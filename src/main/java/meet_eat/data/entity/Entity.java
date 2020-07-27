package meet_eat.data.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import meet_eat.data.location.CityLocation;
import meet_eat.data.location.PostcodeLocation;
import meet_eat.data.location.SphericalLocation;

import java.util.Objects;

@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS)
@JsonSubTypes({
        @JsonSubTypes.Type(value = Tag.class),
        @JsonSubTypes.Type(value = Token.class),
        @JsonSubTypes.Type(value = ReportableEntity.class)
})
public abstract class Entity<U> {

    private static final String ERROR_MESSAGE_TEMPLATE_NULL = "The %s must not be null.";
    private static final String ERROR_MESSAGE_NULL_IDENTIFIER = String.format(ERROR_MESSAGE_TEMPLATE_NULL, "identifier");

    @JsonProperty
    private final U identifier;

    protected Entity() {
        this.identifier = null;
    }

    @JsonCreator
    protected Entity(@JsonProperty("identifier") U identifier) {
        this.identifier = Objects.requireNonNull(identifier, ERROR_MESSAGE_NULL_IDENTIFIER);
    }

    @JsonGetter
    public U getIdentifier() {
        return identifier;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Entity<?> entity = (Entity<?>) o;
        return Objects.equals(identifier, entity.identifier);
    }

    @Override
    public int hashCode() {
        return Objects.hash(identifier);
    }
}