package meet_eat.data.factory;

import meet_eat.data.entity.user.Email;
import meet_eat.data.entity.user.Password;
import meet_eat.data.entity.user.User;

import java.time.LocalDate;

public class UserFactory extends ObjectFactory<User> {

    private EmailFactory emailFactory;
    private PasswordFactory passwordFactory;

    public UserFactory() {
        emailFactory = new EmailFactory();
        passwordFactory = new PasswordFactory();
    }

    @Override
    protected User createObject() {
        Email email = emailFactory.getValidObject();
        Password password = passwordFactory.getValidObject();
        LocalDate birthDay = LocalDate.EPOCH;
        String name = "TestUser" + objectCounter;
        String phoneNumber = Integer.toString(objectCounter);
        String description = "I am " + name + " and this is my description.";
        return new User(email, password, birthDay, name, phoneNumber, description, false);
    }
}
