package meet_eat.data.entity;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;

import meet_eat.data.Report;
import meet_eat.data.entity.user.User;
import meet_eat.data.location.Localizable;

public class Offer extends ReportableEntity<String> {

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

    private final User creator;
    private final Collection<User> participants;
    private final Collection<Tag> tags;

    private String name;
    private String description;
    private double price;
    private int maxParticipants;
    private LocalDateTime dateTime;
    private Localizable location;

    public Offer(User creator, Collection<Tag> tags, String name, String description, double price,
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

    public Offer(String identifier, Collection<Report> reports, User creator, Collection<Tag> tags, String name,
                 String description, double price, int maxParticipants, LocalDateTime dateTime, Localizable location) {
        
        super(identifier, reports);
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

    public User getCreator() {
        return creator;
    }

    public Collection<User> getParticipants() {
        return Collections.unmodifiableCollection(participants);
    }

    public Collection<Tag> getTags() {
        return Collections.unmodifiableCollection(tags);
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public int getMaxParticipants() {
        return maxParticipants;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

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
}