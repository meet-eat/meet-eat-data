package meet_eat.data.entity;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Objects;

import meet_eat.data.Report;

public abstract class ReportableEntity<U> extends Entity<U> {

    private static final String ERROR_MESSAGE_TEMPLATE_NULL = "The %s must not be null.";
    private static final String ERROR_MESSAGE_NULL_REPORTS = String.format(ERROR_MESSAGE_TEMPLATE_NULL, "reports");
    private static final String ERROR_MESSAGE_NULL_REPORT = String.format(ERROR_MESSAGE_TEMPLATE_NULL, "report");

    private final Collection<Report> reports;

    protected ReportableEntity() {
        super();
        this.reports = new LinkedList<>();
    }

    protected ReportableEntity(U identifier, Collection<Report> reports) {
        super(identifier);
        this.reports = Objects.requireNonNull(reports, ERROR_MESSAGE_NULL_REPORTS);
    }

    public Collection<Report> getReports() {
        return Collections.unmodifiableCollection(reports);
    }

    public void addReport(Report report) {
        reports.add(Objects.requireNonNull(report, ERROR_MESSAGE_NULL_REPORT));
    }
}