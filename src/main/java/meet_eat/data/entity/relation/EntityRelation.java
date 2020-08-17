package meet_eat.data.entity.relation;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import meet_eat.data.entity.Bookmark;
import meet_eat.data.entity.Entity;
import meet_eat.data.entity.Participation;
import meet_eat.data.entity.Subscription;

import java.io.Serializable;
import java.util.Objects;

/**
 * Defines an abstract <b>directed</b> association between {@link Entity} instances.
 *
 * @param <T> the type of the source {@link Entity}
 * @param <S> the type of the target {@link Entity}
 * @param <U> the type of the identifier
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS)
@JsonSubTypes({
        @JsonSubTypes.Type(value = Bookmark.class),
        @JsonSubTypes.Type(value = Participation.class),
        @JsonSubTypes.Type(value = Subscription.class)
})
public abstract class EntityRelation<T extends Entity<?>, S extends Entity<?>, U extends Serializable> extends Entity<U> {

    @JsonProperty
    private final T source;
    @JsonProperty
    private final S target;

    /**
     * Creates a new {@link EntityRelation} between a source- and target {@link Entity}.
     *
     * @param source the source {@link Entity}
     * @param target the target {@link Entity}
     */
    public EntityRelation(T source, S target) {
        this.source = Objects.requireNonNull(source);
        this.target = Objects.requireNonNull(target);
    }

    /**
     * Creates a new {@link EntityRelation} between a source- and target {@link Entity}.
     *
     * @param identifier the identifier
     * @param source     the source {@link Entity}
     * @param target     the target {@link Entity}
     */
    @JsonCreator
    protected EntityRelation(@JsonProperty("identifier") U identifier,
                             @JsonProperty("source") T source,
                             @JsonProperty("target") S target) {
        super(identifier);
        this.source = source;
        this.target = target;
    }

    /**
     * Gets the source {@link Entity}.
     *
     * @return the source entity
     */
    @JsonGetter
    public T getSource() {
        return source;
    }

    /**
     * Gets the target {@link Entity}
     *
     * @return the target entity
     */
    @JsonGetter
    public S getTarget() {
        return target;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        EntityRelation<?, ?, ?> that = (EntityRelation<?, ?, ?>) o;
        return Objects.equals(source, that.source) &&
                Objects.equals(target, that.target);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), source, target);
    }
}
