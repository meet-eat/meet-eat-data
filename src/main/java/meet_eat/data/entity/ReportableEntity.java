package meet_eat.data.entity;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;

import meet_eat.data.Report;

public abstract class ReportableEntity extends Entity {
    
    private final Collection<Report> reports;

    protected ReportableEntity() {
        super();
        this.reports = new LinkedList<>();
    }

    protected ReportableEntity(String identifier, Collection<Report> reports) {
        super(identifier);
        this.reports = reports;
    }

    public Collection<Report> getReports() {
        return Collections.unmodifiableCollection(reports);
    }

    public void addReport(Report report) {
        reports.add(report);
    }
}