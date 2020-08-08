package meet_eat.data.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import meet_eat.data.entity.user.User;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.Objects;

public class Subscription extends Entity<String> {

    private static final long serialVersionUID = 862640034570532907L;

    @DBRef
    @JsonProperty
    private final User sourceUser;
    @DBRef
    @JsonProperty
    private final User targetUser;

    public Subscription(User sourceUser, User targetUser) {
        this.sourceUser = Objects.requireNonNull(sourceUser);
        this.targetUser = Objects.requireNonNull(targetUser);
    }

    @JsonCreator
    @PersistenceConstructor
    protected Subscription(@JsonProperty("identifier") String identifier,
                           @JsonProperty("sourceUser") User sourceUser,
                           @JsonProperty("targetUser") User targetUser) {
        super(identifier);
        this.sourceUser = sourceUser;
        this.targetUser = targetUser;
    }

    @JsonGetter
    public User getSourceUser() {
        return sourceUser;
    }

    @JsonGetter
    public User getTargetUser() {
        return targetUser;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Subscription that = (Subscription) o;
        return Objects.equals(sourceUser, that.sourceUser) &&
                Objects.equals(targetUser, that.targetUser);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), sourceUser, targetUser);
    }
}
