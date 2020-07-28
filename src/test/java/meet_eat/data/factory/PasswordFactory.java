package meet_eat.data.factory;

import meet_eat.data.entity.user.Password;

public class PasswordFactory extends ObjectFactory<Password> {

    @Override
    protected Password createObject() {
        return Password.createHashedPassword("MyVeryStr0ngPW!" + objectCounter);
    }
}
