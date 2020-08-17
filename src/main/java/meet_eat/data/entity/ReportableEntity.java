package meet_eat.data.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import meet_eat.data.Report;
import meet_eat.data.entity.user.User;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Objects;

/**
 * Represents a reportable and identifiable entity.
 *
 * @param <U> the type of the identifier
 */
@Deprecated
public abstract class ReportableEntity<U extends Serializable> extends Entity<U> {

    private static final String ERROR_MESSAGE_TEMPLATE_NULL = "The %s must not be null.";
    private static final String ERROR_MESSAGE_NULL_REPORTS = String.format(ERROR_MESSAGE_TEMPLATE_NULL, "reports");
    private static final String ERROR_MESSAGE_NULL_REPORT = String.format(ERROR_MESSAGE_TEMPLATE_NULL, "report");

    @JsonProperty
    private final Collection<Report> reports;

    /**
     * Creates a reportable entity with an empty {@link Report} list.
     */
    protected ReportableEntity() {
        super();
        this.reports = new LinkedList<>();
    }

    /**
     * Creates a reportable entity.
     *
     * @param identifier the identifier
     * @param reports    the reports
     */
    @JsonCreator
    protected ReportableEntity(@JsonProperty("identifier") U identifier,
                               @JsonProperty("reports") Collection<Report> reports) {
        super(identifier);
        this.reports = Objects.requireNonNull(reports, ERROR_MESSAGE_NULL_REPORTS);
    }

    /**
     * Gets the reports.
     *
     * @return the reports
     */
    @JsonGetter
    public Collection<Report> getReports() {
        return Collections.unmodifiableCollection(reports);
    }

    /**
     * Adds a report that refers to the entity.
     *
     * @param report the report
     */
    public void addReport(Report report) {
        reports.add(Objects.requireNonNull(report, ERROR_MESSAGE_NULL_REPORT));
    }
}