package meet_eat.data.entity.user.contact;

import java.util.Objects;

public class ContactRequest {
    
    private static final String ERROR_MESSAGE_TEMPLATE = "The %s must not be null.";
    private static final String ERROR_MESSAGE_NULL_REQUESTER = String.format(ERROR_MESSAGE_TEMPLATE, "requester");
    private static final String ERROR_MESSAGE_NULL_REQUESTED_USER = String.format(ERROR_MESSAGE_TEMPLATE, "requestedUser");

    private final User requester;
    private final User requestedUser;

    public ContactRequest(User requester, User requestedUser) {

        Objects.requireNonNull(requester, ERROR_MESSAGE_NULL_REQUESTER);
        Objects.requireNonNull(requestedUser, ERROR_MESSAGE_NULL_REQUESTED_USER);

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