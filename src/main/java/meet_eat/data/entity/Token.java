package meet_eat.data.entity;

import meet_eat.data.entity.user.User;

public class Token extends Entity {
    
    private final User user;
    private final String value;

    public Token(User user, String value) {
        this.user = user;
        this.value = value;
    }

    public Token(String identifier, User user, String value) {
        super(identifier);
        this.user = user;
        this.value = value;
    }

    public User getUser() {
        return user;
    }

    public String getValue() {
        return value;
    }
}