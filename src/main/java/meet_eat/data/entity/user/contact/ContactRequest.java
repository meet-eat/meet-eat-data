package meet_eat.data.entity.user.contact;

import java.util.Objects;

import meet_eat.data.entity.user.User;

public class ContactRequest {
    
    private static final String ERROR_MESSAGE_TEMPLATE_NULL = "The %s must not be null.";
    private static final String ERROR_MESSAGE_NULL_REQUESTER = String.format(ERROR_MESSAGE_TEMPLATE_NULL, "requester");
    private static final String ERROR_MESSAGE_NULL_REQUESTED_USER = String.format(ERROR_MESSAGE_TEMPLATE_NULL, "requestedUser");

    private final User requester;
    private final User requestedUser;

    public ContactRequest(User requester, User requestedUser) {
        this.requester = Objects.requireNonNull(requester, ERROR_MESSAGE_NULL_REQUESTER);;
        this.requestedUser = Objects.requireNonNull(requestedUser, ERROR_MESSAGE_NULL_REQUESTED_USER);;
    }

    public User getRequester() {
        return requester;
    }

    public User getRequestedUser() {
        return requestedUser;
    }
}