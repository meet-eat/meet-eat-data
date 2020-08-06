package meet_eat.data.factory;

import meet_eat.data.Report;
import meet_eat.data.comparator.OfferComparableField;
import meet_eat.data.comparator.OfferComparator;
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
import meet_eat.data.location.Localizable;
import meet_eat.data.location.SphericalLocation;
import meet_eat.data.location.SphericalPosition;
import meet_eat.data.predicate.OfferPredicate;
import meet_eat.data.predicate.numeric.DoubleOperation;
import meet_eat.data.predicate.numeric.PricePredicate;

import java.time.LocalDate;
import java.util.*;

public class UserFactory extends ObjectFactory<User> {

    private static final Role DEFAULT_ROLE = Role.USER;
    private static final boolean DEFAULT_NOTIFICATION = false;
    private static final boolean DEFAULT_VERIFIED = false;

    private EmailFactory emailFactory;
    private PasswordFactory passwordFactory;

    public UserFactory() {
        super();
        emailFactory = new EmailFactory();
        passwordFactory = new PasswordFactory();
    }

    @Override
    protected User createObject() {
        String identifier = Integer.toString(objectCounter);
        Collection<Report> reports = new LinkedList<>();
        Collection<Rating> ratings = new LinkedList<>();
        Set<User> subscriptions = new HashSet<>();
        Set<Setting> settings = new HashSet<>();
        settings.add(new NotificationSetting(DEFAULT_NOTIFICATION, objectCounter));
        settings.add(new DisplaySetting(getRandomEnumValue(ColorMode.class)));
        Set<Offer> bookmarks = new HashSet<>();
        Role role = DEFAULT_ROLE;
        Email email = emailFactory.getValidObject();
        Password password = passwordFactory.getValidObject();
        LocalDate birthDay = LocalDate.EPOCH;
        String name = "TestUser" + objectCounter;
        String phoneNumber = Integer.toString(objectCounter);
        String description = "I am " + name + " and this is my description.";
        Collection<OfferPredicate> offerPredicates = new LinkedList<OfferPredicate>();
        offerPredicates.add(new PricePredicate(DoubleOperation.LESS, 20d));
        Localizable localizable = new SphericalLocation(new SphericalPosition(0, 0));
        OfferComparator offerComparator = new OfferComparator(OfferComparableField.TIME, localizable);
        return new User(identifier, reports, ratings, subscriptions, settings, bookmarks, role, email, password,
                birthDay, name, phoneNumber, description, DEFAULT_VERIFIED, offerPredicates, offerComparator, localizable);
    }

    private <T extends Enum<T>> T getRandomEnumValue(Class<T> clazz) {
        List<T> values = Arrays.asList(clazz.getEnumConstants());
        Collections.shuffle(values);
        return values.stream().findFirst().get();
    }
}
