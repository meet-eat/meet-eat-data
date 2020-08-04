package meet_eat.data.entity.user;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import meet_eat.data.Report;
import meet_eat.data.entity.Offer;
import meet_eat.data.entity.ReportableEntity;
import meet_eat.data.entity.user.rating.Rating;
import meet_eat.data.entity.user.rating.RatingBasis;
import meet_eat.data.entity.user.setting.DisplaySetting;
import meet_eat.data.entity.user.setting.NotificationSetting;
import meet_eat.data.entity.user.setting.Setting;
import meet_eat.data.predicate.OfferPredicate;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.DBRef;

public class User extends ReportableEntity<String> {

    private static final long serialVersionUID = -7918410988503545424L;

    private static final String ERROR_MESSAGE_TEMPLATE_NULL = "The %s must not be null.";
    private static final String ERROR_MESSAGE_NULL_RATINGS = String.format(ERROR_MESSAGE_TEMPLATE_NULL, "ratings");
    private static final String ERROR_MESSAGE_NULL_RATING = String.format(ERROR_MESSAGE_TEMPLATE_NULL, "rating");
    private static final String ERROR_MESSAGE_NULL_SUBSCRIPTIONS = String.format(ERROR_MESSAGE_TEMPLATE_NULL, "subscriptions");
    private static final String ERROR_MESSAGE_NULL_SUBSCRIPTION = String.format(ERROR_MESSAGE_TEMPLATE_NULL, "subscription");
    private static final String ERROR_MESSAGE_NULL_SETTINGS = String.format(ERROR_MESSAGE_TEMPLATE_NULL, "settings");
    private static final String ERROR_MESSAGE_NULL_SETTING = String.format(ERROR_MESSAGE_TEMPLATE_NULL, "setting");
    private static final String ERROR_MESSAGE_NULL_BOOKMARKS = String.format(ERROR_MESSAGE_TEMPLATE_NULL, "bookmarks");
    private static final String ERROR_MESSAGE_NULL_BOOKMARK = String.format(ERROR_MESSAGE_TEMPLATE_NULL, "bookmark");
    private static final String ERROR_MESSAGE_NULL_REVIEWER = String.format(ERROR_MESSAGE_TEMPLATE_NULL, "reviewer");
    private static final String ERROR_MESSAGE_NULL_ROLE = String.format(ERROR_MESSAGE_TEMPLATE_NULL, "role");
    private static final String ERROR_MESSAGE_NULL_EMAIL = String.format(ERROR_MESSAGE_TEMPLATE_NULL, "email");
    private static final String ERROR_MESSAGE_NULL_PASSWORD = String.format(ERROR_MESSAGE_TEMPLATE_NULL, "password");
    private static final String ERROR_MESSAGE_NULL_BIRTHDAY = String.format(ERROR_MESSAGE_TEMPLATE_NULL, "birthDay");
    private static final String ERROR_MESSAGE_NULL_NAME = String.format(ERROR_MESSAGE_TEMPLATE_NULL, "name");
    private static final String ERROR_MESSAGE_NULL_PHONE_NUMBER = String.format(ERROR_MESSAGE_TEMPLATE_NULL, "phoneNumber");
    private static final String ERROR_MESSAGE_NULL_DESCRIPTION = String.format(ERROR_MESSAGE_TEMPLATE_NULL, "description");

    private static final Role DEFAULT_ROLE = Role.USER;
    private static final int MIN_AMOUNT_RATINGS = 5;
    private static final int DEFAULT_NOT_ENOUGH_RATINGS = 0;
    private static final int ROUNDING_FACTOR = 10;

    @JsonProperty
    private final Collection<Rating> ratings;
    @DBRef
    @JsonProperty
    private final Set<User> subscriptions;
    @JsonProperty
    private final Set<Setting> settings;
    @DBRef
    @JsonProperty
    private final Set<Offer> bookmarks;
    @JsonProperty
    private Role role;
    @JsonProperty
    private Email email;
    @JsonProperty
    private Password password;
    @JsonProperty
    private LocalDate birthDay;
    @JsonProperty
    private String name;
    @JsonProperty
    private String phoneNumber;
    @JsonProperty
    private String description;
    @JsonProperty
    private boolean isVerified;
    @JsonProperty
    private final Collection<OfferPredicate> offerPredicates;

    public User(Email email, Password password, LocalDate birthDay, String name, String phoneNumber,
                String description, boolean isVerified) {

        ratings = new LinkedList<>();
        subscriptions = new HashSet<>();
        settings = new HashSet<>();
        bookmarks = new HashSet<>();
        offerPredicates = new LinkedList<>();
        role = DEFAULT_ROLE;

        this.email = Objects.requireNonNull(email, ERROR_MESSAGE_NULL_EMAIL);
        this.password = Objects.requireNonNull(password, ERROR_MESSAGE_NULL_PASSWORD);
        this.birthDay = Objects.requireNonNull(birthDay, ERROR_MESSAGE_NULL_BIRTHDAY);
        this.name = Objects.requireNonNull(name, ERROR_MESSAGE_NULL_NAME);
        this.phoneNumber = Objects.requireNonNull(phoneNumber, ERROR_MESSAGE_NULL_PHONE_NUMBER);
        this.description = Objects.requireNonNull(description, ERROR_MESSAGE_NULL_DESCRIPTION);
        this.isVerified = isVerified;

        initSettings();
    }

    @JsonCreator
    @PersistenceConstructor
    public User(@JsonProperty("identifier") String identifier,
                @JsonProperty("reports") Collection<Report> reports,
                @JsonProperty("ratings") Collection<Rating> ratings,
                @JsonProperty("subscriptions") Set<User> subscriptions,
                @JsonProperty("settings") Set<Setting> settings,
                @JsonProperty("bookmarks") Set<Offer> bookmarks,
                @JsonProperty("role") Role role,
                @JsonProperty("email") Email email,
                @JsonProperty("password") Password password,
                @JsonProperty("birthDay") LocalDate birthDay,
                @JsonProperty("name") String name,
                @JsonProperty("phoneNumber") String phoneNumber,
                @JsonProperty("description") String description,
                @JsonProperty("isVerified") boolean isVerified,
                @JsonProperty("offerPredicates") Collection<OfferPredicate> offerPredicates) {

        super(identifier, reports);
        this.ratings = Objects.requireNonNull(ratings, ERROR_MESSAGE_NULL_RATINGS);
        this.subscriptions = Objects.requireNonNull(subscriptions, ERROR_MESSAGE_NULL_SUBSCRIPTIONS);
        this.settings = Objects.requireNonNull(settings, ERROR_MESSAGE_NULL_SETTINGS);
        this.bookmarks = Objects.requireNonNull(bookmarks, ERROR_MESSAGE_NULL_BOOKMARKS);
        this.role = Objects.requireNonNull(role, ERROR_MESSAGE_NULL_ROLE);
        this.email = Objects.requireNonNull(email, ERROR_MESSAGE_NULL_EMAIL);
        this.password = Objects.requireNonNull(password, ERROR_MESSAGE_NULL_PASSWORD);
        this.birthDay = Objects.requireNonNull(birthDay, ERROR_MESSAGE_NULL_BIRTHDAY);
        this.name = Objects.requireNonNull(name, ERROR_MESSAGE_NULL_NAME);
        this.phoneNumber = Objects.requireNonNull(phoneNumber, ERROR_MESSAGE_NULL_PHONE_NUMBER);
        this.description = Objects.requireNonNull(description, ERROR_MESSAGE_NULL_DESCRIPTION);
        this.isVerified = isVerified;
        this.offerPredicates = Objects.requireNonNull(offerPredicates);
    }

    @JsonGetter
    public Collection<Rating> getRatings() {
        return Collections.unmodifiableCollection(ratings);
    }

    @JsonGetter
    public Set<User> getSubscriptions() {
        return Collections.unmodifiableSet(subscriptions);
    }

    @JsonGetter
    public Set<Setting> getSettings() {
        return Collections.unmodifiableSet(settings);
    }

    @JsonGetter
    public Set<Offer> getBookmarks() {
        return Collections.unmodifiableSet(bookmarks);
    }

    @JsonGetter
    public Role getRole() {
        return role;
    }

    @JsonGetter
    public Email getEmail() {
        return email;
    }

    @JsonGetter
    public Password getPassword() {
        return password;
    }

    @JsonGetter
    public LocalDate getBirthDay() {
        return birthDay;
    }

    @JsonGetter
    public String getName() {
        return name;
    }

    @JsonGetter
    public String getPhoneNumber() {
        return phoneNumber;
    }

    @JsonGetter
    public String getDescription() {
        return description;
    }

    @JsonGetter
    public boolean isVerified() {
        return isVerified;
    }

    @JsonGetter
    public Collection<OfferPredicate> getOfferPredicates() {
        return Collections.unmodifiableCollection(offerPredicates);
    }

    public void setRole(Role role) {
        this.role = Objects.requireNonNull(role, ERROR_MESSAGE_NULL_ROLE);
    }

    public void setEmail(Email email) {
        this.email = Objects.requireNonNull(email, ERROR_MESSAGE_NULL_EMAIL);
    }

    public void setPassword(Password password) {
        this.password = Objects.requireNonNull(password, ERROR_MESSAGE_NULL_PASSWORD);
    }

    public void setBirthDay(LocalDate birthDay) {
        this.birthDay = Objects.requireNonNull(birthDay, ERROR_MESSAGE_NULL_BIRTHDAY);
    }

    public void setName(String name) {
        this.name = Objects.requireNonNull(name, ERROR_MESSAGE_NULL_NAME);
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = Objects.requireNonNull(phoneNumber, ERROR_MESSAGE_NULL_PHONE_NUMBER);
    }

    public void setDescription(String description) {
        this.description = Objects.requireNonNull(description, ERROR_MESSAGE_NULL_DESCRIPTION);
    }

    public void setVerified(boolean isVerified) {
        this.isVerified = isVerified;
    }

    public void addRating(Rating rating) {
        ratings.add(Objects.requireNonNull(rating, ERROR_MESSAGE_NULL_RATING));
    }

    public void addSubscription(User subscription) {
        subscriptions.add(Objects.requireNonNull(subscription, ERROR_MESSAGE_NULL_SUBSCRIPTION));
    }

    public void addSetting(Setting setting) {
        settings.add(Objects.requireNonNull(setting, ERROR_MESSAGE_NULL_SETTING));
    }

    public void addBookmark(Offer bookmark) {
        Objects.requireNonNull(bookmark, ERROR_MESSAGE_NULL_BOOKMARK);
        bookmarks.add(bookmark);
    }

    public void addOfferPredicate(OfferPredicate predicate) {
        Objects.requireNonNull(predicate);
        offerPredicates.add(predicate);
    }

    public void removeRatingsByReviewer(User reviewer) {
        Objects.requireNonNull(reviewer, ERROR_MESSAGE_NULL_REVIEWER);
        ratings.removeIf(x -> x.getReviewer().equals(reviewer));
    }

    public void removeSubscriptions(User subscription) {
        subscriptions.remove(Objects.requireNonNull(subscription, ERROR_MESSAGE_NULL_SUBSCRIPTION));
    }

    public void removeBookmark(Offer bookmark) {
        bookmarks.remove(Objects.requireNonNull(bookmark, ERROR_MESSAGE_NULL_BOOKMARK));
    }

    public void removeOfferPredicate(OfferPredicate predicate) {
        offerPredicates.remove(Objects.requireNonNull(predicate));
    }

    @JsonIgnore
    public double getHostRating() {
        return (countRatings(RatingBasis.HOST) >= MIN_AMOUNT_RATINGS)
                ? calculateAverageHostRating()
                : DEFAULT_NOT_ENOUGH_RATINGS;
    }

    @JsonIgnore
    public double getGuestRating() {
        return (countRatings(RatingBasis.GUEST) >= MIN_AMOUNT_RATINGS)
                ? calculateAverageGuestRating()
                : DEFAULT_NOT_ENOUGH_RATINGS;
    }

    private double calculateAverageHostRating() {
        RatingBasis basis = RatingBasis.HOST;
        double hostRating = (double) sumRatings(basis) / countRatings(basis);
        hostRating = roundToFirstDecimal(hostRating / ratings.size());
        return hostRating;
    }

    private double calculateAverageGuestRating() {
        RatingBasis basis = RatingBasis.GUEST;
        double guestRating = (double) sumRatings(basis) / countRatings(basis);
        guestRating = roundToFirstDecimal(guestRating / ratings.size());
        return guestRating;
    }

    private int countRatings(RatingBasis basis) {
        return ratings
            .stream()
            .filter(x -> x.getBasis().equals(basis))
            .collect(Collectors.toList())
            .size();
    }

    private int sumRatings(RatingBasis basis) {
        return ratings
            .stream()
            .filter(x -> x.getBasis().equals(basis))
            .mapToInt(x -> x.getValue().getIntegerValue())
            .sum();
    }

    private double roundToFirstDecimal(double rating) {
        return (((double) Math.round(rating * ROUNDING_FACTOR)) / ROUNDING_FACTOR);
    }

    private void initSettings() {
        settings.add(new DisplaySetting());
        settings.add(new NotificationSetting());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        User user = (User) o;
        return isVerified == user.isVerified &&
                Objects.equals(ratings, user.ratings) &&
                Objects.equals(subscriptions, user.subscriptions) &&
                Objects.equals(settings, user.settings) &&
                Objects.equals(bookmarks, user.bookmarks) &&
                role == user.role &&
                Objects.equals(email, user.email) &&
                Objects.equals(password, user.password) &&
                Objects.equals(birthDay, user.birthDay) &&
                Objects.equals(name, user.name) &&
                Objects.equals(phoneNumber, user.phoneNumber) &&
                Objects.equals(description, user.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), ratings, subscriptions, settings, bookmarks, role, email, password,
                birthDay, name, phoneNumber, description, isVerified);
    }
}