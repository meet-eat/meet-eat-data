package meet_eat.data;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import meet_eat.data.entity.ReportableEntity;
import meet_eat.data.entity.user.User;

/**
 * Represents a report of a {@link ReportableEntity} with a given {@link User}.
 */
public class Report {

    private static final String ERROR_MESSAGE_TEMPLATE_NULL = "The %s must not be null.";
    private static final String ERROR_MESSAGE_NULL_REPORTER = String.format(ERROR_MESSAGE_TEMPLATE_NULL, "reporter");
    private static final String ERROR_MESSAGE_NULL_MESSAGE = String.format(ERROR_MESSAGE_TEMPLATE_NULL, "message");

    @JsonProperty
    private final User reporter;
    @JsonProperty
    private final String message;
    @JsonProperty
    private boolean processed;

    /**
     * Creates a report.
     *
     * @param reporter the reporter
     * @param message  the message
     */
    @JsonCreator
    public Report(@JsonProperty("reporter") User reporter, @JsonProperty("message") String message) {
        this.reporter = Objects.requireNonNull(reporter, ERROR_MESSAGE_NULL_REPORTER);
        this.message = Objects.requireNonNull(message, ERROR_MESSAGE_NULL_MESSAGE);
    }

    /**
     * Gets the current reporter.
     *
     * @return the reporter
     */
    @JsonGetter
    public User getReporter() {
        return reporter;
    }

    /**
     * Gets the current message given by a {@link User}.
     *
     * @return the report message
     */
    @JsonGetter
    public String getMessage() {
        return message;
    }

    /**
     * Gets the report processed status.
     *
     * @return {@code true} if the report is processed, {@code false} otherwise
     */
    @JsonGetter
    public boolean isProcessed() {
        return processed;
    }

    /**
     * Sets the processed status.
     *
     * @param processed indicator, if a {@link Report} has been processed or not
     */
    @JsonSetter("processed")
    public void setProcessed(@JsonProperty("processed") boolean processed) {
        this.processed = processed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Report report = (Report) o;
        return processed == report.processed &&
                Objects.equals(reporter, report.reporter) &&
                Objects.equals(message, report.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(reporter, message, processed);
    }
}