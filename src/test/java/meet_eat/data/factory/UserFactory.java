package meet_eat.data.factory;

import meet_eat.data.Report;
import meet_eat.data.entity.Offer;
import meet_eat.data.entity.user.Email;
import meet_eat.data.entity.user.Password;
import meet_eat.data.entity.user.Role;
import meet_eat.data.entity.user.User;
import meet_eat.data.entity.user.rating.Rating;
import meet_eat.data.entity.user.setting.Setting;

import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class UserFactory extends ObjectFactory<User> {

    private static final int AMOUNT_REPORTS = 2;
    private static final int AMOUNT_RATINGS = 5;
    private static final int AMOUNT_SUBSCRIPTIONS = 2;
    private static final int AMOUNT_SETTINGS = 2;
    private static final int AMOUNT_BOOKMARKS = 2;
    private static final Role DEFAULT_ROLE = Role.USER;

    private ReportFactory reportFactory;
    private RatingFactory ratingFactory;
    private SettingFactory settingFactory;
    private OfferFactory offerFactory;
    private EmailFactory emailFactory;
    private PasswordFactory passwordFactory;

    public UserFactory() {

        reportFactory = new ReportFactory();
        ratingFactory = new RatingFactory();
        settingFactory = new SettingFactory();
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
        for (int i = 0; i < AMOUNT_SETTINGS; i++) {
            settings.add(settingFactory.getValidObject());
        }
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
                birthDay, name, phoneNumber, description, false);
    }
}
