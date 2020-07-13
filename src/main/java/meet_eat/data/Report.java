package meet_eat.data;

import java.util.Objects;

public class Report {
    
    private static final String ERROR_MESSAGE_TEMPLATE_NULL = "The %s must not be null.";
    private static final String ERROR_MESSAGE_NULL_REPORTER = String.format(ERROR_MESSAGE_TEMPLATE_NULL, "reporter");
    private static final String ERROR_MESSAGE_NULL_MESSAGE = String.format(ERROR_MESSAGE_TEMPLATE_NULL, "message");

    private final User reporter;
    private final String message;
    private boolean processed;

    public Report(User reporter, String message) {
        Objects.requireNonNull(reporter, ERROR_MESSAGE_NULL_REPORTER);
        Objects.requireNonNull(message, ERROR_MESSAGE_NULL_MESSAGE);

        this.reporter = reporter;
        this.message = message;
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