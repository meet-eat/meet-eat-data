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
import meet_eat.data.comparator.OfferComparableField;
import meet_eat.data.comparator.OfferComparator;
import meet_eat.data.entity.Offer;
import meet_eat.data.entity.ReportableEntity;
import meet_eat.data.entity.user.rating.Rating;
import meet_eat.data.entity.user.rating.RatingBasis;
import meet_eat.data.entity.user.setting.DisplaySetting;
import meet_eat.data.entity.user.setting.NotificationSetting;
import meet_eat.data.entity.user.setting.Setting;
import meet_eat.data.location.Localizable;
import meet_eat.data.predicate.OfferPredicate;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.DBRef;

/**
 * Represents a {@link ReportableEntity}.
 */
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
    @DBRef(lazy = true)
    @JsonProperty
    private final Set<User> subscriptions;
    @JsonProperty
    private final Set<Setting> settings;
    @DBRef(lazy = true)
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
    @JsonProperty
    private OfferComparator offerComparator;
    @JsonProperty
    private Localizable localizable;

    /**
     * Creates a new user.
     *
     * @param email       the email address
     * @param password    the password
     * @param birthDay    the birthday
     * @param name        the display name
     * @param phoneNumber the phone number
     * @param description the user description
     * @param isVerified  the indicator if the user is verified or not
     * @param localizable the user location
     */
    public User(Email email, Password password, LocalDate birthDay, String name, String phoneNumber,
                String description, boolean isVerified, Localizable localizable) {

        ratings = new LinkedList<>();
        subscriptions = new HashSet<>();
        settings = new HashSet<>();
        bookmarks = new HashSet<>();
        offerPredicates = new LinkedList<>();
        role = DEFAULT_ROLE;
        offerComparator = new OfferComparator(OfferComparableField.TIME, localizable);

        this.email = Objects.requireNonNull(email, ERROR_MESSAGE_NULL_EMAIL);
        this.password = Objects.requireNonNull(password, ERROR_MESSAGE_NULL_PASSWORD);
        this.birthDay = Objects.requireNonNull(birthDay, ERROR_MESSAGE_NULL_BIRTHDAY);
        this.name = Objects.requireNonNull(name, ERROR_MESSAGE_NULL_NAME);
        this.phoneNumber = Objects.requireNonNull(phoneNumber, ERROR_MESSAGE_NULL_PHONE_NUMBER);
        this.description = Objects.requireNonNull(description, ERROR_MESSAGE_NULL_DESCRIPTION);
        this.isVerified = isVerified;
        this.localizable = Objects.requireNonNull(localizable);

        initSettings();
    }

    /**
     * Creates a new user.
     *
     * @param identifier      the identifier
     * @param reports         the reports received by other users
     * @param ratings         the ratings received by other users
     * @param subscriptions   the users subscribed by the user
     * @param settings        the user settings
     * @param bookmarks       the offers bookmarked by the user
     * @param role            the user role
     * @param email           the email address
     * @param password        the password
     * @param birthDay        the birthday
     * @param name            the display name
     * @param phoneNumber     the phone number
     * @param description     the user description
     * @param isVerified      indicator if the user is verified or not
     * @param offerPredicates the predicates used to filter offers
     * @param offerComparator the comparator used to sort offers
     * @param localizable     the user location
     */
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
                @JsonProperty("offerPredicates") Collection<OfferPredicate> offerPredicates,
                @JsonProperty("offerComparator") OfferComparator offerComparator,
                @JsonProperty("localizable") Localizable localizable) {

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
        this.offerComparator = Objects.requireNonNull(offerComparator);
        this.localizable = Objects.requireNonNull(localizable);
    }

    /**
     * Gets the ratings.
     *
     * @return the ratings
     */
    @JsonGetter
    public Collection<Rating> getRatings() {
        return Collections.unmodifiableCollection(ratings);
    }

    /**
     * Gets the subscriptions.
     *
     * @return the subscriptions
     */
    @JsonGetter
    public Set<User> getSubscriptions() {
        return Collections.unmodifiableSet(subscriptions);
    }

    /**
     * Gets the settings.
     *
     * @return the settings
     */
    @JsonGetter
    public Set<Setting> getSettings() {
        return Collections.unmodifiableSet(settings);
    }

    /**
     * Gets the bookmarks.
     *
     * @return the bookmarks
     */
    @JsonGetter
    public Set<Offer> getBookmarks() {
        return Collections.unmodifiableSet(bookmarks);
    }

    /**
     * Gets the role.
     *
     * @return the role
     */
    @JsonGetter
    public Role getRole() {
        return role;
    }

    /**
     * Gets the email address.
     *
     * @return the email address
     */
    @JsonGetter
    public Email getEmail() {
        return email;
    }

    /**
     * Gets the password
     *
     * @return the password
     */
    @JsonGetter
    public Password getPassword() {
        return password;
    }

    /**
     * Gets the birthday.
     *
     * @return the birthday
     */
    @JsonGetter
    public LocalDate getBirthDay() {
        return birthDay;
    }

    /**
     * Gets the name.
     *
     * @return the name
     */
    @JsonGetter
    public String getName() {
        return name;
    }

    /**
     * Gets the phone number
     *
     * @return the phone number
     */
    @JsonGetter
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Gets the description.
     *
     * @return the description
     */
    @JsonGetter
    public String getDescription() {
        return description;
    }

    /**
     * Gets the verified status of an user.
     *
     * @return {@code true} if the user is verified, {@code false} otherwise
     */
    @JsonGetter
    public boolean isVerified() {
        return isVerified;
    }

    /**
     * Gets the offer predicates.
     *
     * @return the offer predicates
     */
    @JsonGetter
    public Collection<OfferPredicate> getOfferPredicates() {
        return Collections.unmodifiableCollection(offerPredicates);
    }

    /**
     * Gets the offer comparator.
     *
     * @return the offer comparator
     */
    @JsonGetter
    public OfferComparator getOfferComparator() {
        return offerComparator;
    }

    /**
     * Gets the location.
     *
     * @return the location
     */
    @JsonGetter
    public Localizable getLocalizable() {
        return localizable;
    }

    /**
     * Sets the role.
     *
     * @param role the role
     */
    public void setRole(Role role) {
        this.role = Objects.requireNonNull(role, ERROR_MESSAGE_NULL_ROLE);
    }

    /**
     * Sets the email address.
     *
     * @param email the email address
     */
    public void setEmail(Email email) {
        this.email = Objects.requireNonNull(email, ERROR_MESSAGE_NULL_EMAIL);
    }

    /**
     * Sets the password.
     *
     * @param password the password
     */
    public void setPassword(Password password) {
        this.password = Objects.requireNonNull(password, ERROR_MESSAGE_NULL_PASSWORD);
    }

    /**
     * Sets the birthday.
     *
     * @param birthDay the birthday
     */
    public void setBirthDay(LocalDate birthDay) {
        this.birthDay = Objects.requireNonNull(birthDay, ERROR_MESSAGE_NULL_BIRTHDAY);
    }

    /**
     * Sets the name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = Objects.requireNonNull(name, ERROR_MESSAGE_NULL_NAME);
    }

    /**
     * Sets the phone number.
     *
     * @param phoneNumber the phone number
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = Objects.requireNonNull(phoneNumber, ERROR_MESSAGE_NULL_PHONE_NUMBER);
    }

    /**
     * Sets the description.
     *
     * @param description the description
     */
    public void setDescription(String description) {
        this.description = Objects.requireNonNull(description, ERROR_MESSAGE_NULL_DESCRIPTION);
    }

    /**
     * Sets the verified status of the user.
     *
     * @param isVerified the verified status
     */
    public void setVerified(boolean isVerified) {
        this.isVerified = isVerified;
    }

    /**
     * Sets the offer comparator.
     *
     * @param offerComparator the offer comparator
     */
    public void setOfferComparator(OfferComparator offerComparator) {
        this.offerComparator = Objects.requireNonNull(offerComparator);
    }

    /**
     * Sets the location.
     *
     * @param localizable the location
     */
    public void setLocalizable(Localizable localizable) {
        this.localizable = Objects.requireNonNull(localizable);
    }

    /**
     * Adds a user rating.
     *
     * @param rating the user rating
     */
    public void addRating(Rating rating) {
        ratings.add(Objects.requireNonNull(rating, ERROR_MESSAGE_NULL_RATING));
    }

    /**
     * Adds a user subscription.
     *
     * @param subscription the user subscription
     */
    public void addSubscription(User subscription) {
        subscriptions.add(Objects.requireNonNull(subscription, ERROR_MESSAGE_NULL_SUBSCRIPTION));
    }

    /**
     * Adds a setting.
     *
     * @param setting the setting
     */
    public void addSetting(Setting setting) {
        settings.add(Objects.requireNonNull(setting, ERROR_MESSAGE_NULL_SETTING));
    }

    /**
     * Adds an offer bookmark.
     *
     * @param bookmark the offer bookmark
     */
    public void addBookmark(Offer bookmark) {
        bookmarks.add(Objects.requireNonNull(bookmark, ERROR_MESSAGE_NULL_BOOKMARK));
    }

    /**
     * Adds an offer predicate.
     *
     * @param predicate the offer predicate
     */
    public void addOfferPredicate(OfferPredicate predicate) {
        offerPredicates.add(Objects.requireNonNull(predicate));
    }

    /**
     * Adds multiple offer predicates.
     *
     * @param predicates the offer predicates
     */
    public void addManyOfferPredicates(Collection<OfferPredicate> predicates) {
        offerPredicates.addAll(Objects.requireNonNull(predicates));
    }

    /**
     * Removes a rating based on the reviewer.
     *
     * @param reviewer the reviewer
     */
    public void removeRatingsByReviewer(User reviewer) {
        Objects.requireNonNull(reviewer, ERROR_MESSAGE_NULL_REVIEWER);
        ratings.removeIf(x -> x.getReviewer().equals(reviewer));
    }

    /**
     * Removes a user subscription.
     *
     * @param subscription the user subscription
     */
    public void removeSubscription(User subscription) {
        subscriptions.remove(Objects.requireNonNull(subscription, ERROR_MESSAGE_NULL_SUBSCRIPTION));
    }

    /**
     * Removes an offer bookmark.
     *
     * @param bookmark the offer bookmark
     */
    public void removeBookmark(Offer bookmark) {
        bookmarks.remove(Objects.requireNonNull(bookmark, ERROR_MESSAGE_NULL_BOOKMARK));
    }

    /**
     * Removes an offer predicate.
     *
     * @param predicate the offer predicate
     */
    public void removeOfferPredicate(OfferPredicate predicate) {
        offerPredicates.remove(Objects.requireNonNull(predicate));
    }

    /**
     * Removes all offer predicates.
     */
    public void clearOfferPredicates() {
        offerPredicates.clear();
    }

    /**
     * Gets the user host rating.
     *
     * @return the host rating if the user has at least {@link User#MIN_AMOUNT_RATINGS}, {@link User#DEFAULT_NOT_ENOUGH_RATINGS} otherwise
     */
    @JsonIgnore
    public double getHostRating() {
        return (countRatings(RatingBasis.HOST) >= MIN_AMOUNT_RATINGS)
                ? calculateAverageHostRating()
                : DEFAULT_NOT_ENOUGH_RATINGS;
    }

    /**
     * Gets the user guest rating.
     *
     * @return the guest rating if the user has at least {@link User#MIN_AMOUNT_RATINGS}, {@link User#DEFAULT_NOT_ENOUGH_RATINGS} otherwise
     */
    @JsonIgnore
    public double getGuestRating() {
        return (countRatings(RatingBasis.GUEST) >= MIN_AMOUNT_RATINGS)
                ? calculateAverageGuestRating()
                : DEFAULT_NOT_ENOUGH_RATINGS;
    }

    /**
     * Calculates the average host rating rounded to first decimal.
     *
     * @return the average host rating
     */
    private double calculateAverageHostRating() {
        RatingBasis basis = RatingBasis.HOST;
        double hostRating = (double) sumRatings(basis) / countRatings(basis);
        hostRating = roundToFirstDecimal(hostRating);
        return hostRating;
    }

    /**
     * Calculates the average guest rating rounded to first decimal.
     *
     * @return the average guest rating
     */
    private double calculateAverageGuestRating() {
        RatingBasis basis = RatingBasis.GUEST;
        double guestRating = (double) sumRatings(basis) / countRatings(basis);
        guestRating = roundToFirstDecimal(guestRating);
        return guestRating;
    }

    /**
     * Counts the amount of ratings based on {@link RatingBasis}.
     *
     * @param basis the rating basis
     * @return the amount of the specific ratings
     */
    private int countRatings(RatingBasis basis) {
        return ratings
                .stream()
                .filter(x -> x.getBasis().equals(basis))
                .collect(Collectors.toList())
                .size();
    }

    /**
     * Sums up the integer values of the ratings based on {@link RatingBasis}.
     *
     * @param basis the rating basis
     * @return the sum of the specific ratings
     */
    private int sumRatings(RatingBasis basis) {
        return ratings
                .stream()
                .filter(x -> x.getBasis().equals(basis))
                .mapToInt(x -> x.getValue().getIntegerValue())
                .sum();
    }

    /**
     * Rounds a rating to its first decimal.
     *
     * @param rating the rating value
     * @return the rating value rounded to first decimal
     */
    private double roundToFirstDecimal(double rating) {
        return (((double) Math.round(rating * ROUNDING_FACTOR)) / ROUNDING_FACTOR);
    }

    /**
     * Initializes default settings for the {@link User}.
     */
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
                Objects.equals(description, user.description) &&
                Objects.equals(offerPredicates, user.offerPredicates) &&
                Objects.equals(offerComparator, user.offerComparator) &&
                Objects.equals(localizable, user.localizable);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), ratings, subscriptions, settings, bookmarks, role, email, password, birthDay, name, phoneNumber, description, isVerified, offerPredicates, offerComparator, localizable);
    }
}