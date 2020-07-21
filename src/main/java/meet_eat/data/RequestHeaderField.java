package meet_eat.data;

import java.util.Objects;

public enum RequestHeaderField {

    TOKEN,

    PAGE,

    PREDICATES,

    COMPARATORS;

    private final String identifier;

    private RequestHeaderField() {
        this.identifier = this.name().toLowerCase();
    }

    private RequestHeaderField(String identifier) {
        this.identifier = identifier;
    }

    public String getIdentifier() {
        return this.name().toLowerCase();
    }
}
