package meet_eat.data.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import meet_eat.data.entity.user.User;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.Objects;

/**
 * Represents a relation between an {@link User} instance and an {@link Offer} instance.
 * This relation is used when an {@link User} wants to participate at a certain {@link Offer}.
 */
public class Participation extends Entity<String> {

    private static final long serialVersionUID = -6039205532276460576L;

    @DBRef
    @JsonProperty
    private final User participant;
    @DBRef
    @JsonProperty
    private final Offer offer;

    /**
     * Constructs a new instance of {@link Participation}.
     *
     * @param participant the {@link User participant} of the given {@link Offer offer}
     * @param offer       the {@link Offer offer} the {@link User user} is participating at
     */
    public Participation(User participant, Offer offer) {
        this.participant = Objects.requireNonNull(participant);
        this.offer = Objects.requireNonNull(offer);
    }

    /**
     * Constructs a new instance of {@link Participation}.
     *
     * @param identifier  the identifier of the {@link Participation} relation
     * @param participant the {@link User participant} of the given {@link Offer offer}
     * @param offer       the {@link Offer offer} the {@link User user} is participating at
     */
    @JsonCreator
    @PersistenceConstructor
    protected Participation(@JsonProperty("identifier") String identifier,
                            @JsonProperty("participant") User participant,
                            @JsonProperty("offer") Offer offer) {
        super(identifier);
        this.participant = participant;
        this.offer = offer;
    }

    /**
     * Gets the {@link User participant} of this {@link Participation} instance.
     *
     * @return the participant
     */
    public User getParticipant() {
        return participant;
    }

    /**
     * Gets the {@link Offer offer} of this {@link Participation} instance.
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
        Participation that = (Participation) o;
        return Objects.equals(participant, that.participant) &&
                Objects.equals(offer, that.offer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), participant, offer);
    }
}
