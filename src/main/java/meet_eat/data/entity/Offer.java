package meet_eat.data.entity;

import java.time.LocalDateTime;
import java.util.*;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import meet_eat.data.Report;
import meet_eat.data.entity.user.User;
import meet_eat.data.location.Localizable;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.DBRef;

public class Offer extends ReportableEntity<String> {

    private static final long serialVersionUID = -5026750026998176586L;

    private static final String ERROR_MESSAGE_TEMPLATE_NULL = "The %s must not be null.";
    private static final String ERROR_MESSAGE_NULL_CREATOR = String.format(ERROR_MESSAGE_TEMPLATE_NULL, "creator");
    private static final String ERROR_MESSAGE_NULL_PARTICIPANTS = String.format(ERROR_MESSAGE_TEMPLATE_NULL, "participants");
    private static final String ERROR_MESSAGE_NULL_PARTICIPANT = String.format(ERROR_MESSAGE_TEMPLATE_NULL, "participant");
    private static final String ERROR_MESSAGE_NULL_TAGS = String.format(ERROR_MESSAGE_TEMPLATE_NULL, "tags");
    private static final String ERROR_MESSAGE_NULL_TAG = String.format(ERROR_MESSAGE_TEMPLATE_NULL, "tag");
    private static final String ERROR_MESSAGE_NULL_NAME = String.format(ERROR_MESSAGE_TEMPLATE_NULL, "name");
    private static final String ERROR_MESSAGE_NULL_DESCRIPTION = String.format(ERROR_MESSAGE_TEMPLATE_NULL, "description");
    private static final String ERROR_MESSAGE_NULL_DATE_TIME = String.format(ERROR_MESSAGE_TEMPLATE_NULL, "dateTime");
    private static final String ERROR_MESSAGE_NULL_LOCATION = String.format(ERROR_MESSAGE_TEMPLATE_NULL, "location");
    private static final String ERROR_MESSAGE_ILLEGAL_PRICE = "The price must be greater or equals than 0.";
    private static final String ERROR_MESSAGE_ILLEGAL_MAX_PARTICIPANTS = "At least one individual has to participate.";

    private static final double DEFAULT_MIN_PRICE = 0d;
    private static final int DEFAULT_MIN_PARTICIPANTS = 1;

    @DBRef
    @JsonProperty
    private final User creator;
    @DBRef
    @JsonProperty
    private final Set<User> participants;
    @DBRef
    @JsonProperty
    private final Set<Tag> tags;

    @JsonProperty
    private String name;
    @JsonProperty
    private String description;
    @JsonProperty
    private double price;
    @JsonProperty
    private int maxParticipants;
    @JsonProperty
    private LocalDateTime dateTime;
    @JsonProperty
    private Localizable location;

    public Offer(User creator, Set<Tag> tags, String name, String description, double price,
                 int maxParticipants, LocalDateTime dateTime, Localizable location) {

        this.creator = Objects.requireNonNull(creator, ERROR_MESSAGE_NULL_CREATOR);
        this.participants = new HashSet<>();
        this.tags = Objects.requireNonNull(tags, ERROR_MESSAGE_NULL_TAGS);
        this.name = Objects.requireNonNull(name, ERROR_MESSAGE_NULL_NAME);
        this.description = Objects.requireNonNull(description, ERROR_MESSAGE_NULL_DESCRIPTION);
        if (price < DEFAULT_MIN_PRICE) {
            throw new IllegalArgumentException(ERROR_MESSAGE_ILLEGAL_PRICE);
        }
        this.price = price;
        if (maxParticipants < DEFAULT_MIN_PARTICIPANTS) {
            throw new IllegalArgumentException(ERROR_MESSAGE_ILLEGAL_MAX_PARTICIPANTS);
        }
        this.maxParticipants = maxParticipants;
        this.dateTime = Objects.requireNonNull(dateTime, ERROR_MESSAGE_NULL_DATE_TIME);
        this.location = Objects.requireNonNull(location, ERROR_MESSAGE_NULL_LOCATION);
    }

    @JsonCreator
    @PersistenceConstructor
    public Offer(@JsonProperty("identifier") String identifier,
                 @JsonProperty("reports") Collection<Report> reports,
                 @JsonProperty("creator") User creator,
                 @JsonProperty("participants") Set<User> participants,
                 @JsonProperty("tags") Set<Tag> tags,
                 @JsonProperty("name") String name,
                 @JsonProperty("description") String description,
                 @JsonProperty("price") double price,
                 @JsonProperty("maxParticipants") int maxParticipants,
                 @JsonProperty("dateTime") LocalDateTime dateTime,
                 @JsonProperty("location") Localizable location) {
        
        super(identifier, reports);
        this.creator = Objects.requireNonNull(creator, ERROR_MESSAGE_NULL_CREATOR);
        this.participants = Objects.requireNonNull(participants, ERROR_MESSAGE_NULL_PARTICIPANTS);
        this.tags = Objects.requireNonNull(tags, ERROR_MESSAGE_NULL_TAGS);
        this.name = Objects.requireNonNull(name, ERROR_MESSAGE_NULL_NAME);
        this.description = Objects.requireNonNull(description, ERROR_MESSAGE_NULL_DESCRIPTION);
        if (price < DEFAULT_MIN_PRICE) {
            throw new IllegalArgumentException(ERROR_MESSAGE_ILLEGAL_PRICE);
        }
        this.price = price;
        if (maxParticipants < DEFAULT_MIN_PARTICIPANTS) {
            throw new IllegalArgumentException(ERROR_MESSAGE_ILLEGAL_MAX_PARTICIPANTS);
        }
        this.maxParticipants = maxParticipants;
        this.dateTime = Objects.requireNonNull(dateTime, ERROR_MESSAGE_NULL_DATE_TIME);
        this.location = Objects.requireNonNull(location, ERROR_MESSAGE_NULL_LOCATION);
    }

    @JsonGetter
    public User getCreator() {
        return creator;
    }

    @JsonGetter
    public Collection<User> getParticipants() {
        return Collections.unmodifiableCollection(participants);
    }

    @JsonGetter
    public Collection<Tag> getTags() {
        return Collections.unmodifiableCollection(tags);
    }

    @JsonGetter
    public String getName() {
        return name;
    }

    @JsonGetter
    public String getDescription() {
        return description;
    }

    @JsonGetter
    public double getPrice() {
        return price;
    }

    @JsonGetter
    public int getMaxParticipants() {
        return maxParticipants;
    }

    @JsonGetter
    public LocalDateTime getDateTime() {
        return dateTime;
    }

    @JsonGetter
    public Localizable getLocation() {
        return location;
    }

    public void setName(String name) {
        Objects.requireNonNull(name, ERROR_MESSAGE_NULL_NAME);
        this.name = name;
    }

    public void setDescription(String description) {
        Objects.requireNonNull(description, ERROR_MESSAGE_NULL_DESCRIPTION);
        this.description = description;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setMaxParticipants(int maxParticipants) {
        this.maxParticipants = maxParticipants;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = Objects.requireNonNull(dateTime, ERROR_MESSAGE_NULL_DATE_TIME);
    }

    public void setLocation(Localizable location) {
        this.location = Objects.requireNonNull(location, ERROR_MESSAGE_NULL_LOCATION);
    }

    public void addParticipant(User participant) {
        participants.add(Objects.requireNonNull(participant, ERROR_MESSAGE_NULL_PARTICIPANT));
    }

    public void addTag(Tag tag) {

        tags.add(Objects.requireNonNull(tag, ERROR_MESSAGE_NULL_TAG));
    }

    public void removeParticipant(User participant) {
        participants.remove(Objects.requireNonNull(participant, ERROR_MESSAGE_NULL_PARTICIPANT));
    }

    public void removeTag(Tag tag) {
        tags.remove(Objects.requireNonNull(tag, ERROR_MESSAGE_NULL_TAG));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Offer offer = (Offer) o;
        return Double.compare(offer.price, price) == 0 &&
                maxParticipants == offer.maxParticipants &&
                Objects.equals(creator, offer.creator) &&
                Objects.equals(participants, offer.participants) &&
                Objects.equals(tags, offer.tags) &&
                Objects.equals(name, offer.name) &&
                Objects.equals(description, offer.description) &&
                Objects.equals(dateTime, offer.dateTime) &&
                Objects.equals(location, offer.location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), creator, participants, tags, name, description, price, maxParticipants, dateTime, location);
    }
}