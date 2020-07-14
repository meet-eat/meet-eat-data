package meet_eat.data.entity.user;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.function.Predicate;

import meet_eat.data.entity.user.rating.Rating;

public class User {
    
    private static final String ERROR_MESSAGE_TEMPLATE = "The %s must not be null.";
    private static final String ERROR_MESSAGE_NULL_BIRTHDAY = String.format(ERROR_MESSAGE_TEMPLATE, "birthDay");
    private static final String ERROR_MESSAGE_NULL_IMAGE = String.format(ERROR_MESSAGE_TEMPLATE, "image");
    private static final String ERROR_MESSAGE_NULL_ROLE = String.format(ERROR_MESSAGE_TEMPLATE, "role");

    private final Set<Rating> ratings;
    private final Set<User> subscriptions;
    private final Set<Setting> settings;
    private final Set<Predicate> offerPredicates;
    private final Set<Offer> bookmarks;
    private final LocalDate birthDay;
    private final Image image;
    private final Role role;

    private String email;
    private String password;
    private String name;
    private String description;
    private String phoneNumber;
    private boolean isVerified;

    public User(LocalDate birthDay, Image image, Role role, String email, String password,
                String name, String description, String phoneNumber, boolean isVerified) {

        ratings = new HashSet<>();
        subscriptions = new HashSet<>();
        settings = new HashSet<>();
        offerPredicates = new HashSet<>();
        bookmarks = new HashSet<>();

        Objects.requireNonNull(birthDay, ERROR_MESSAGE_NULL_BIRTHDAY);
        Objects.requireNonNull(image, ERROR_MESSAGE_NULL_IMAGE);
        Objects.requireNonNull(role, ERROR_MESSAGE_NULL_ROLE);

        this.birthDay = birthDay;
        this.image = image;
        this.role = role;

        this.email = email;
        this.password = password;
        this.name = name;
        this.description = description;
        this.phoneNumber = phoneNumber;
        this.isVerified = isVerified;
    }

    
}