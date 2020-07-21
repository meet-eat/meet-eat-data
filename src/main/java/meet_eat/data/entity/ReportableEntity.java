package meet_eat.data.entity;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Objects;

import meet_eat.data.Report;

public abstract class ReportableEntity extends Entity {

    private static final String ERROR_MESSAGE_TEMPLATE_NULL = "The %s must not be null.";
    private static final String ERROR_MESSAGE_NULL_REPORTS = String.format(ERROR_MESSAGE_TEMPLATE_NULL, "reports");

    private final Collection<Report> reports;

    protected ReportableEntity() {
        super();
        this.reports = new LinkedList<>();
    }

    protected ReportableEntity(String identifier, Collection<Report> reports) {
        super(identifier);
        Objects.requireNonNull(reports, ERROR_MESSAGE_NULL_REPORTS);
        this.reports = reports;
    }

    public Collection<Report> getReports() {
        return Collections.unmodifiableCollection(reports);
    }

    public void addReport(Report report) {
        reports.add(report);
    }
}