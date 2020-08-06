package meet_eat.data;

import meet_eat.data.entity.Offer;
import meet_eat.data.entity.Tag;
import meet_eat.data.entity.user.User;

/**
 * Represents descriptor of URI entity endpoint paths.
 */
public final class EndpointPath {

    /**
     * The URI endpoint path for: login
     */
    public static final String LOGIN = "/login";

    /**
     * The URI endpoint path for: logout
     */
    public static final String LOGOUT = "/logout";

    /**
     * The URI endpoint path for: {@link Tag}s
     */
    public static final String TAGS = "/tags";

    /**
     * The URI endpoint path for: {@link Offer}s
     */
    public static final String OFFERS = "/offers";

    /**
     * The URI endpoint path for: {@link User}s
     */
    public static final String USERS = "/users";

    private EndpointPath() {
    }
}
