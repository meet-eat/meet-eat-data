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

import meet_eat.data.entity.Offer;
import meet_eat.data.entity.ReportableEntity;
import meet_eat.data.entity.user.rating.Rating;
import meet_eat.data.entity.user.rating.RatingBasis;
import meet_eat.data.entity.user.setting.Setting;

public class User extends ReportableEntity {
    
    private static final String ERROR_MESSAGE_TEMPLATE_NULL = "The %s must not be null.";
    private static final String ERROR_MESSAGE_NULL_BIRTHDAY = String.format(ERROR_MESSAGE_TEMPLATE_NULL, "birthDay");
    private static final String ERROR_MESSAGE_NULL_ROLE = String.format(ERROR_MESSAGE_TEMPLATE_NULL, "role");
    private static final int MIN_AMOUNT_RATINGS = 5;
    private static final int ROUNDING_FACTOR = 10;

    private final Collection<Rating> ratings;
    private final Set<User> subscriptions;
    private final Set<Setting> settings;
    private final Set<Predicate<Offer>> offerPredicates;
    private final Set<Offer> bookmarks;

    private LocalDate birthDay;
    private Role role;
    private Email email;
    private Password password;
    private String name;
    private String description;
    private String phoneNumber;
    private boolean isVerified;

    public User(LocalDate birthDay, Role role, Email email, Password password,
            String name, String description, String phoneNumber, boolean isVerified) {

        ratings = new LinkedList<>();
        subscriptions = new HashSet<>();
        settings = new HashSet<>();
        offerPredicates = new HashSet<>();
        bookmarks = new HashSet<>();

        Objects.requireNonNull(birthDay, ERROR_MESSAGE_NULL_BIRTHDAY);
        Objects.requireNonNull(role, ERROR_MESSAGE_NULL_ROLE);

        this.birthDay = birthDay;
        this.role = role;
        this.email = email;
        this.password = password;
        this.name = name;
        this.description = description;
        this.phoneNumber = phoneNumber;
        this.isVerified = isVerified;
    }

    public Collection<Rating> getRatings() {
        return Collections.unmodifiableCollection(ratings);
    }

    public Collection<User> getSubscriptions() {
        return Collections.unmodifiableCollection(subscriptions);
    }

    public Collection<Setting> getSettings() {
        return Collections.unmodifiableCollection(settings);
    }

    public Collection<Predicate<Offer>> getPredicates() {
        return Collections.unmodifiableCollection(offerPredicates);
    }

    public Collection<Offer> getBookmarks() {
        return Collections.unmodifiableCollection(bookmarks);
    }

    public LocalDate getBirthDay() {
        return birthDay;
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

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public boolean isVerified() {
        return isVerified;
    }

    public void addRating(Rating rating) {
        ratings.add(rating);
    }

    public void addSubscription(User subscription) {
        subscriptions.add(subscription);
    }

    public void addSetting(Setting setting) {
        settings.add(setting);
    }

    public void addPredicate(Predicate<Offer> predicate) {
        offerPredicates.add(predicate);
    }

    public void addBookmark(Offer bookmark) {
        bookmarks.add(bookmark);
    }

    public void setBirthDay(LocalDate birthDay) {
        Objects.requireNonNull(birthDay, ERROR_MESSAGE_NULL_BIRTHDAY);
        this.birthDay = birthDay;
    }

    public void setRole(Role role) {
        Objects.requireNonNull(role, ERROR_MESSAGE_NULL_ROLE);
        this.role = role;
    }

    public void setEmail(Email email) {
        this.email = email;
    }

    public void setPassword(Password password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setVerified(boolean isVerified) {
        this.isVerified = isVerified;
    }

    public void removeRatingsByReviewer(User reviewer) {
        ratings.removeIf(x -> x.getReviewer().equals(reviewer));
    }

    public void removeSubscriptions(User subscription) {
        subscriptions.remove(subscription);
    }

    public void removePredicate(Predicate<Offer> predicate) {
        offerPredicates.remove(predicate);
    }

    public void removeBookmark(Offer bookmark) {
        bookmarks.remove(bookmark);
    }

    public double getHostRating() {
        return (countRatings(RatingBasis.HOST) >= MIN_AMOUNT_RATINGS) ? calculateAverageHostRating() : 0;
    }

    public double getGuestRating() {
        return (countRatings(RatingBasis.GUEST) >= MIN_AMOUNT_RATINGS) ? calculateAverageGuestRating() : 0;
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