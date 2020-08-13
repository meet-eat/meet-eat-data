package meet_eat.data.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import meet_eat.data.entity.user.User;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.Objects;

/**
 * Represents a relation between an {@link User} instance and an {@link Offer} instance.
 * This relation is used when an {@link User} needs to find a certain {@link Offer} again afterwards.
 */
public class Bookmark extends Entity<String> {

    private static final long serialVersionUID = 3929846360647477484L;

    @DBRef
    @JsonProperty
    private final User user;
    @DBRef
    @JsonProperty
    private final Offer offer;

    /**
     * Constructs a new instance of {@link Bookmark}.
     *
     * @param user  the {@link User user} bookmarking the given {@link Offer offer}
     * @param offer the {@link Offer offer} to be bookmarked by the given {@link User user}
     */
    public Bookmark(User user, Offer offer) {
        this.user = Objects.requireNonNull(user);
        this.offer = Objects.requireNonNull(offer);
    }

    /**
     * Constructs a new instance of {@link Bookmark}.
     *
     * @param identifier the identifier of the {@link Bookmark} relation
     * @param user       the {@link User user} bookmarking the given {@link Offer offer}
     * @param offer      the {@link Offer offer} to be bookmarked by the given {@link User user}
     */
    @JsonCreator
    @PersistenceConstructor
    protected Bookmark(@JsonProperty("identifier") String identifier,
                       @JsonProperty("user") User user,
                       @JsonProperty("offer") Offer offer) {
        super(identifier);
        this.user = user;
        this.offer = offer;
    }

    /**
     * Gets the {@link User user} of this {@link Bookmark} instance.
     *
     * @return the user
     */
    public User getUser() {
        return user;
    }

    /**
     * Gets the {@link Offer offer} of this {@link Bookmark} instance.
     *
     * @return the offer
     */
    public Offer getOffer() {
        return offer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Bookmark bookmark = (Bookmark) o;
        return Objects.equals(user, bookmark.user) &&
                Objects.equals(offer, bookmark.offer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), user, offer);
    }
}
