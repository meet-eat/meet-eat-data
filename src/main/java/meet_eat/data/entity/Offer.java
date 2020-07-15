package meet_eat.data.entity;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;

import meet_eat.data.Report;
import meet_eat.data.entity.user.User;
import meet_eat.data.location.Localizable;

public class Offer extends ReportableEntity {

    private final User creator;
    private final Collection<User> participants;
    private final Collection<Tag> tags;

    private String name;
    private String description;
    private double price;
    private int maxParticipants;
    private LocalDateTime dateTime;
    private Localizable location;

    public Offer(User creator, Collection<User> participants,
            Collection<Tag> tags, String name, String description, double price, int maxParticipants,
            LocalDateTime dateTime, Localizable location) {
        
        this.creator = creator;
        this.participants = participants;
        this.tags = tags;
        this.name = name;
        this.description = description;
        this.price = price;
        this.maxParticipants = maxParticipants;
        this.dateTime = dateTime;
        this.location = location;
    }

    private Offer(String identifier, Collection<Report> reports, User creator, Collection<User> participants,
            Collection<Tag> tags, String name, String description, double price, int maxParticipants,
            LocalDateTime dateTime, Localizable location) {
        
        super(identifier, reports);
        this.creator = creator;
        this.participants = participants;
        this.tags = tags;
        this.name = name;
        this.description = description;
        this.price = price;
        this.maxParticipants = maxParticipants;
        this.dateTime = dateTime;
        this.location = location;
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
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setMaxParticipants(int maxParticipants) {
        this.maxParticipants = maxParticipants;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public void setLocation(Localizable location) {
        this.location = location;
    }

    public void addParticipant(User participant) {
        participants.add(participant);
    }

    public void addTag(Tag tag) {
        tags.add(tag);
    }

    public void removeParticipant(User participant) {
        participants.remove(participant);
    }

    public void removeTag(Tag tag) {
        tags.remove(tag);
    }
}