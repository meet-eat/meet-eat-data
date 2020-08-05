package meet_eat.data.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import meet_eat.data.Report;
import meet_eat.data.entity.user.User;

@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS)
@JsonSubTypes({
        @JsonSubTypes.Type(value = User.class),
        @JsonSubTypes.Type(value = Offer.class),
})
public abstract class ReportableEntity<U extends Serializable> extends Entity<U> {

    private static final String ERROR_MESSAGE_TEMPLATE_NULL = "The %s must not be null.";
    private static final String ERROR_MESSAGE_NULL_REPORTS = String.format(ERROR_MESSAGE_TEMPLATE_NULL, "reports");
    private static final String ERROR_MESSAGE_NULL_REPORT = String.format(ERROR_MESSAGE_TEMPLATE_NULL, "report");

    @JsonProperty
    private final Collection<Report> reports;

    protected ReportableEntity() {
        super();
        this.reports = new LinkedList<>();
    }

    @JsonCreator
    protected ReportableEntity(@JsonProperty("identifier") U identifier, @JsonProperty("reports") Collection<Report> reports) {
        super(identifier);
        this.reports = Objects.requireNonNull(reports, ERROR_MESSAGE_NULL_REPORTS);
    }

    @JsonGetter
    public Collection<Report> getReports() {
        return Collections.unmodifiableCollection(reports);
    }

    public void addReport(Report report) {
        reports.add(Objects.requireNonNull(report, ERROR_MESSAGE_NULL_REPORT));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ReportableEntity<?> that = (ReportableEntity<?>) o;
        return reports.containsAll(that.reports);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), reports);
    }
}