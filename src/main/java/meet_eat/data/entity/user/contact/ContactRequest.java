package meet_eat.data.entity.user.contact;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import meet_eat.data.entity.user.User;

public class ContactRequest {
    
    private static final String ERROR_MESSAGE_TEMPLATE_NULL = "The %s must not be null.";
    private static final String ERROR_MESSAGE_NULL_REQUESTER = String.format(ERROR_MESSAGE_TEMPLATE_NULL, "requester");
    private static final String ERROR_MESSAGE_NULL_REQUESTED_USER = String.format(ERROR_MESSAGE_TEMPLATE_NULL, "requestedUser");

    @JsonProperty
    private final User requester;
    @JsonProperty
    private final User requestedUser;

    @JsonCreator
    public ContactRequest(@JsonProperty("requester") User requester, @JsonProperty("requestedUser") User requestedUser) {
        this.requester = Objects.requireNonNull(requester, ERROR_MESSAGE_NULL_REQUESTER);;
        this.requestedUser = Objects.requireNonNull(requestedUser, ERROR_MESSAGE_NULL_REQUESTED_USER);;
    }

    @JsonGetter
    public User getRequester() {
        return requester;
    }

    @JsonGetter
    public User getRequestedUser() {
        return requestedUser;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactRequest that = (ContactRequest) o;
        return Objects.equals(requester, that.requester) &&
                Objects.equals(requestedUser, that.requestedUser);
    }

    @Override
    public int hashCode() {
        return Objects.hash(requester, requestedUser);
    }
}