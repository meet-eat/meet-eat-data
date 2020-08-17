package meet_eat.data.entity.relation;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonGetter;
import meet_eat.data.entity.Entity;

import java.io.Serializable;
import java.util.Objects;

/**
 * Defines abstract <b>directed</b> associations between {@link Entity} instances.
 *
 * @param <T> the type of the source {@link Entity}
 * @param <S> the type of the target {@link Entity}
 * @param <U> the type of the identifier
 */
public abstract class EntityRelation<T extends Entity<?>, S extends Entity<?>, U extends Serializable> extends Entity<U> {

    private final T source;
    private final S target;

    /**
     * Creates a new {@link EntityRelation} between a source- and target {@link Entity}.
     *
     * @param source the source {@link Entity}
     * @param target the target {@link Entity}
     */
    protected EntityRelation(T source, S target) {
        this.source = source;
        this.target = target;
    }

    /**
     * Creates a new {@link EntityRelation} between a source- and target {@link Entity}.
     *
     * @param identifier the identifier
     * @param source     the source {@link Entity}
     * @param target     the target {@link Entity}
     */
    @JsonCreator
    protected EntityRelation(U identifier, T source, S target) {
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
