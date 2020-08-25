package meet_eat.data.entity.relation.rating;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import meet_eat.data.entity.Offer;
import meet_eat.data.entity.relation.EntityRelation;
import meet_eat.data.entity.user.User;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.Objects;

/**
 * Represents the rating of a {@link User user}, which he/she has received from other {@link User users}.
 * On the one hand as a {@link RatingBasis#GUEST guest}, on the other as a {@link RatingBasis#HOST host}.
 */
public class Rating extends EntityRelation<User, User, String> {

    private static final long serialVersionUID = 908006033527302372L;

    private static final String ERROR_MESSAGE_TEMPLATE_NULL = "The %s must not be null.";
    private static final String ERROR_MESSAGE_NULL_OFFER = String.format(ERROR_MESSAGE_TEMPLATE_NULL, "offer");
    private static final String ERROR_MESSAGE_NULL_VALUE = String.format(ERROR_MESSAGE_TEMPLATE_NULL, "value");

    @DBRef
    @JsonProperty
    private final Offer offer;
    @JsonProperty
    private final RatingBasis basis;
    @JsonProperty
    private final RatingValue value;

    /**
     * Creates a new instance of {@link Rating}.
     *
     * @param source the {@link User reviewer}
     * @param target the {@link User reviewed user}
     * @param offer  the {@link Offer offer} in which both {@link User users} met.
     * @param value  the given {@link RatingValue}
     */
    private Rating(User source, User target, Offer offer, RatingValue value) {
        super(source, target);
        this.offer = offer;
        basis = getBasisByCreator(source, offer);
        this.value = value;
    }

    /**
     * Creates a new instance of {@link Rating}.
     *
     * @param identifier the identifier
     * @param source     the {@link User reviewer}
     * @param target     the {@link User reviewed user}
     * @param offer      the {@link Offer offer} in which both {@link User users} met.
     * @param value      the given {@link RatingValue}
     */
    @JsonCreator
    @PersistenceConstructor
    protected Rating(@JsonProperty("identifier") String identifier,
                     @JsonProperty("source") User source,
                     @JsonProperty("target") User target,
                     @JsonProperty("offer") Offer offer,
                     @JsonProperty("value") RatingValue value) {
        super(identifier, source, target);
        this.offer = offer;
        basis = getBasisByCreator(source, offer);
        this.value = value;
    }

    /**
     * Creates a new host {@link Rating rating}.
     *
     * @param guest the {@link User guest user}
     * @param offer the {@link Offer offer} in which the {@link User guest} participated
     * @param value the value which was rated by the {@link User guest}
     * @return the new host {@link Rating rating}
     */
    public static Rating createHostRating(User guest, Offer offer, RatingValue value) {
        Objects.requireNonNull(offer, ERROR_MESSAGE_NULL_OFFER);
        Objects.requireNonNull(value, ERROR_MESSAGE_NULL_VALUE);
        return new Rating(guest, offer.getCreator(), offer, value);
    }

    /**
     * Creates a new guest {@link Rating rating}.
     *
     * @param guest the {@link User guest user}
     * @param offer the {@link Offer offer} in which the {@link User guest} participated
     * @param value the value which was rated by the {@link User host}
     * @return the new guest {@link Rating rating}
     */
    public static Rating createGuestRating(User guest, Offer offer, RatingValue value) {
        Objects.requireNonNull(offer, ERROR_MESSAGE_NULL_OFFER);
        Objects.requireNonNull(value, ERROR_MESSAGE_NULL_VALUE);
        return new Rating(offer.getCreator(), guest, offer, value);
    }

    /**
     * Gets the offer.
     *
     * @return the offer
     */
    @JsonGetter
    public Offer getOffer() {
        return offer;
    }

    /**
     * Gets the rating basis.
     *
     * @return the rating basis
     */
    @JsonGetter
    public RatingBasis getBasis() {
        return basis;
    }

    /**
     * Gets the rating value.
     *
     * @return the rating value
     */
    @JsonGetter
    public RatingValue getValue() {
        return value;
    }

    /**
     * Checks whether the {@link User reviewer} of a {@link Rating rating} was the {@link RatingBasis#HOST host}
     * of an {@link Offer offer} or the participating {@link RatingBasis#GUEST guest}.
     *
     * @param reviewer the reviewer
     * @param offer    the offer
     * @return {@link RatingBasis#GUEST} if the reviewer is creator of the offer
     * and thus rating a guest, {@link RatingBasis#HOST} otherwise
     */
    private RatingBasis getBasisByCreator(User reviewer, Offer offer) {
        return reviewer.equals(offer.getCreator()) ? RatingBasis.GUEST : RatingBasis.HOST;
    }
}