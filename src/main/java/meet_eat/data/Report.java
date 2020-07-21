package meet_eat.data;

import java.util.Objects;

import meet_eat.data.entity.user.User;

public class Report {
    
    private static final String ERROR_MESSAGE_TEMPLATE_NULL = "The %s must not be null.";
    private static final String ERROR_MESSAGE_NULL_REPORTER = String.format(ERROR_MESSAGE_TEMPLATE_NULL, "reporter");
    private static final String ERROR_MESSAGE_NULL_MESSAGE = String.format(ERROR_MESSAGE_TEMPLATE_NULL, "message");

    private final User reporter;
    private final String message;
    private boolean processed;

    public Report(User reporter, String message) {
        this.reporter = Objects.requireNonNull(reporter, ERROR_MESSAGE_NULL_REPORTER);
        this.message = Objects.requireNonNull(message, ERROR_MESSAGE_NULL_MESSAGE);
    }

    public User getReporter() {
        return reporter;
    }
    
    public String getMessage() {
        return message;
    }

    public boolean isProcessed() {
        return processed;
    }

    public void setProcessed(boolean processed) {
        this.processed = processed;
    }
}