package meet_eat.data;

/**
 * Represents types which can be passed into request headers used for HTTP requests.
 */
public final class RequestHeaderField {

    /**
     * The string representation of a token.
     */
    public static final String TOKEN = "token";

    /**
     * The string representation of a page.
     */
    public static final String PAGE = "page";

    /**
     * The string representation of predicates.
     */
    public static final String PREDICATES = "predicates";

    /**
     * The string representation of comparators.
     */
    public static final String COMPARATORS = "comparators";

    private RequestHeaderField() {
    }
}
