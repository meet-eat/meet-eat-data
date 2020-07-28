package meet_eat.data.factory;

import meet_eat.data.Report;
import meet_eat.data.entity.Offer;
import meet_eat.data.entity.user.Email;
import meet_eat.data.entity.user.Password;
import meet_eat.data.entity.user.Role;
import meet_eat.data.entity.user.User;
import meet_eat.data.entity.user.rating.Rating;
import meet_eat.data.entity.user.setting.ColorMode;
import meet_eat.data.entity.user.setting.DisplaySetting;
import meet_eat.data.entity.user.setting.NotificationSetting;
import meet_eat.data.entity.user.setting.Setting;

import java.time.LocalDate;
import java.util.*;

public class UserFactory extends ObjectFactory<User> {

    private static final int AMOUNT_REPORTS = 2;
    private static final int AMOUNT_RATINGS = 5;
    private static final int AMOUNT_SUBSCRIPTIONS = 2;
    private static final int AMOUNT_BOOKMARKS = 2;
    private static final Role DEFAULT_ROLE = Role.USER;
    private static final boolean DEFAULT_NOTIFICATION = false;
    private static final boolean DEFAULT_VERIFIED = false;

    private ReportFactory reportFactory;
    private RatingFactory ratingFactory;
    private OfferFactory offerFactory;
    private EmailFactory emailFactory;
    private PasswordFactory passwordFactory;

    public UserFactory() {

        reportFactory = new ReportFactory();
        ratingFactory = new RatingFactory();
        offerFactory = new OfferFactory();
        emailFactory = new EmailFactory();
        passwordFactory = new PasswordFactory();
    }

    @Override
    protected User createObject() {
        String identifier = Integer.toString(objectCounter);

        Collection<Report> reports = new LinkedList<>();
        for (int i = 0; i < AMOUNT_REPORTS; i++) {
            reports.add(reportFactory.getValidObject());
        }

        Collection<Rating> ratings = new LinkedList<>();
        for (int i = 0; i < AMOUNT_RATINGS; i++) {
            ratings.add(ratingFactory.getValidObject());
        }

        Set<User> subscriptions = new HashSet<>();
        for (int i = 0; i < AMOUNT_SUBSCRIPTIONS; i++) {
            subscriptions.add(getValidObject());
        }

        Set<Setting> settings = new HashSet<>();
        Setting notificationSetting = new NotificationSetting(DEFAULT_NOTIFICATION, objectCounter);
        Setting displaySetting = new DisplaySetting(getRandomEnumValue(ColorMode.class));
        settings.add(notificationSetting);
        settings.add(displaySetting);

        Set<Offer> bookmarks = new HashSet<>();
        for (int i = 0; i < AMOUNT_BOOKMARKS; i++) {
            bookmarks.add(offerFactory.getValidObject());
        }

        Role role = DEFAULT_ROLE;
        Email email = emailFactory.getValidObject();
        Password password = passwordFactory.getValidObject();
        LocalDate birthDay = LocalDate.EPOCH;
        String name = "TestUser" + objectCounter;
        String phoneNumber = Integer.toString(objectCounter);
        String description = "I am " + name + " and this is my description.";
        return new User(identifier, reports, ratings, subscriptions, settings, bookmarks, role, email, password,
                birthDay, name, phoneNumber, description, DEFAULT_VERIFIED);
    }

    private <T extends Enum<T>> T getRandomEnumValue(Class<T> clazz) {
        List<T> values = Arrays.asList(clazz.getEnumConstants());
        Collections.shuffle(values);
        return values.stream().findFirst().get();
    }
}
