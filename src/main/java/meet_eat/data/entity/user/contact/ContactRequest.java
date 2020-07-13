package meet_eat.data.entity.user.contact;

public class ContactRequest {
    
    private final User requester;
    private final User requestedUser;

    public ContactRequest(User requester, User requestedUser) {

        this.requester = requester;
        this.requestedUser = requestedUser;
    }

    public User getRequester() {

        return requester;
    }

    public User getRequestedUser() {

        return requestedUser;
    }
}