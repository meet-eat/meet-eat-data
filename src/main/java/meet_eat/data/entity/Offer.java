package meet_eat.data.entity;

import java.time.LocalDateTime;
import java.util.Collection;

public class Offer extends ReportableEntity {
    
    private final User creator;
    private final Collection<User> participants;
    private final Collection<Tag> tags;

    private String name;
    private String description;
    private double price;
    private int maxParticipants;
    private Image image;
    private LocalDateTime dateTime;
    private Localizable location;

    // TODO Complete implementation
}