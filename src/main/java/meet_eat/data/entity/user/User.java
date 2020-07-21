package meet_eat.data.entity.user;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import meet_eat.data.Report;
import meet_eat.data.entity.Offer;
import meet_eat.data.entity.ReportableEntity;
import meet_eat.data.entity.user.rating.Rating;
import meet_eat.data.entity.user.rating.RatingBasis;
import meet_eat.data.entity.user.setting.Setting;

public class User extends ReportableEntity {
    
    private static final String ERROR_MESSAGE_TEMPLATE_NULL = "The %s must not be null.";
    private static final String ERROR_MESSAGE_NULL_RATINGS = String.format(ERROR_MESSAGE_TEMPLATE_NULL, "ratings");
    private static final String ERROR_MESSAGE_NULL_RATING = String.format(ERROR_MESSAGE_TEMPLATE_NULL, "rating");
    private static final String ERROR_MESSAGE_NULL_SUBSCRIPTIONS = String.format(ERROR_MESSAGE_TEMPLATE_NULL, "subscriptions");
    private static final String ERROR_MESSAGE_NULL_SUBSCRIPTION = String.format(ERROR_MESSAGE_TEMPLATE_NULL, "subscription");
    private static final String ERROR_MESSAGE_NULL_SETTINGS = String.format(ERROR_MESSAGE_TEMPLATE_NULL, "settings");
    private static final String ERROR_MESSAGE_NULL_SETTING = String.format(ERROR_MESSAGE_TEMPLATE_NULL, "setting");
    private static final String ERROR_MESSAGE_NULL_OFFER_PREDICATES = String.format(ERROR_MESSAGE_TEMPLATE_NULL, "offerPredicates");
    private static final String ERROR_MESSAGE_NULL_OFFER_PREDICATE = String.format(ERROR_MESSAGE_TEMPLATE_NULL, "offerPredicate");
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
    private static final int ROUNDING_FACTOR = 10;

    private final Collection<Rating> ratings;
    private final Set<User> subscriptions;
    private final Set<Setting> settings;
    private final Set<Predicate<Offer>> offerPredicates;
    private final Set<Offer> bookmarks;

    private Role role;
    private Email email;
    private Password password;
    private LocalDate birthDay;
    private String name;
    private String phoneNumber;
    private String description;
    private boolean isVerified;

    public User(Email email, Password password, LocalDate birthDay, String name, String phoneNumber,
                String description, boolean isVerified) {

        ratings = new LinkedList<>();
        subscriptions = new HashSet<>();
        settings = new HashSet<>();
        offerPredicates = new HashSet<>();
        bookmarks = new HashSet<>();
        role = DEFAULT_ROLE;

        this.email = Objects.requireNonNull(email, ERROR_MESSAGE_NULL_EMAIL);
        this.password = Objects.requireNonNull(password, ERROR_MESSAGE_NULL_PASSWORD);
        this.birthDay = Objects.requireNonNull(birthDay, ERROR_MESSAGE_NULL_BIRTHDAY);
        this.name = Objects.requireNonNull(name, ERROR_MESSAGE_NULL_NAME);
        this.phoneNumber = Objects.requireNonNull(phoneNumber, ERROR_MESSAGE_NULL_PHONE_NUMBER);
        this.description = Objects.requireNonNull(description, ERROR_MESSAGE_NULL_DESCRIPTION);
        this.isVerified = isVerified;
    }

    public User(String identifier, Collection<Report> reports, Collection<Rating> ratings, Set<User> subscriptions,
                Set<Setting> settings, Set<Predicate<Offer>> offerPredicates, Set<Offer> bookmarks, Role role,
                Email email, Password password, LocalDate birthDay, String name, String phoneNumber, String description,
                boolean isVerified) {

        super(identifier, reports);
        this.ratings = Objects.requireNonNull(ratings, ERROR_MESSAGE_NULL_RATINGS);
        this.subscriptions = Objects.requireNonNull(subscriptions, ERROR_MESSAGE_NULL_SUBSCRIPTIONS);
        this.settings = Objects.requireNonNull(settings, ERROR_MESSAGE_NULL_SETTINGS);
        this.offerPredicates = Objects.requireNonNull(offerPredicates, ERROR_MESSAGE_NULL_OFFER_PREDICATES);
        this.bookmarks = Objects.requireNonNull(bookmarks, ERROR_MESSAGE_NULL_BOOKMARKS);
        this.role = Objects.requireNonNull(role, ERROR_MESSAGE_NULL_ROLE);
        this.email = Objects.requireNonNull(email, ERROR_MESSAGE_NULL_EMAIL);
        this.password = Objects.requireNonNull(password, ERROR_MESSAGE_NULL_PASSWORD);
        this.birthDay = Objects.requireNonNull(birthDay, ERROR_MESSAGE_NULL_BIRTHDAY);
        this.name = Objects.requireNonNull(name, ERROR_MESSAGE_NULL_NAME);
        this.phoneNumber = Objects.requireNonNull(phoneNumber, ERROR_MESSAGE_NULL_PHONE_NUMBER);
        this.description = Objects.requireNonNull(description, ERROR_MESSAGE_NULL_DESCRIPTION);
        this.isVerified = isVerified;
    }

    public Collection<Rating> getRatings() {
        return Collections.unmodifiableCollection(ratings);
    }

    public Set<User> getSubscriptions() {
        return Collections.unmodifiableSet(subscriptions);
    }

    public Set<Setting> getSettings() {
        return Collections.unmodifiableSet(settings);
    }

    public Set<Predicate<Offer>> getPredicates() {
        return Collections.unmodifiableSet(offerPredicates);
    }

    public Set<Offer> getBookmarks() {
        return Collections.unmodifiableSet(bookmarks);
    }

    public Role getRole() {
        return role;
    }

    public Email getEmail() {
        return email;
    }

    public Password getPassword() {
        return password;
    }

    public LocalDate getBirthDay() {
        return birthDay;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getDescription() {
        return description;
    }

    public boolean isVerified() {
        return isVerified;
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

    public void addPredicate(Predicate<Offer> predicate) {
        offerPredicates.add(Objects.requireNonNull(predicate, ERROR_MESSAGE_NULL_OFFER_PREDICATE));
    }

    public void addBookmark(Offer bookmark) {
        Objects.requireNonNull(bookmark, ERROR_MESSAGE_NULL_BOOKMARK);
        bookmarks.add(bookmark);
    }

    public void removeRatingsByReviewer(User reviewer) {
        Objects.requireNonNull(reviewer, ERROR_MESSAGE_NULL_REVIEWER);
        ratings.removeIf(x -> x.getReviewer().equals(reviewer));
    }

    public void removeSubscriptions(User subscription) {
        subscriptions.remove(Objects.requireNonNull(subscription, ERROR_MESSAGE_NULL_SUBSCRIPTION));
    }

    public void removePredicate(Predicate<Offer> predicate) {
        offerPredicates.remove(Objects.requireNonNull(predicate, ERROR_MESSAGE_NULL_OFFER_PREDICATE));
    }

    public void removeBookmark(Offer bookmark) {
        bookmarks.remove(Objects.requireNonNull(bookmark, ERROR_MESSAGE_NULL_BOOKMARK));
    }

    public double getHostRating() {
        return (countRatings(RatingBasis.HOST) >= MIN_AMOUNT_RATINGS) ? calculateAverageHostRating() : 0d;
    }

    public double getGuestRating() {
        return (countRatings(RatingBasis.GUEST) >= MIN_AMOUNT_RATINGS) ? calculateAverageGuestRating() : 0d;
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
}