package meet_eat.data.entity;

import meet_eat.data.Report;
import meet_eat.data.factory.ReportFactory;
import org.junit.Test;

import java.util.Collection;
import java.util.LinkedList;

import static org.junit.Assert.*;

public class ReportableEntityCommonTest {

    private class ConcreteReportableEntity extends ReportableEntity<String> {

        public ConcreteReportableEntity() {
            super();
        }

        public ConcreteReportableEntity(String identifier, Collection<Report> reports) {
            super(identifier, reports);
        }
    }

    @Test
    public void testConstructorEmpty() {
        // Execution
        ConcreteReportableEntity entity = new ConcreteReportableEntity();

        // Assertions
        assertNotNull(entity);
    }

    @Test
    public void testConstructor() {
        // Test data
        Collection<Report> reports = new LinkedList<>();
        ReportFactory reportFactory = new ReportFactory();
        Report report = reportFactory.getValidObject();
        reports.add(report);
        String identifier = "TestIdentifierXY";

        // Execution
        ConcreteReportableEntity entity = new ConcreteReportableEntity(identifier, reports);

        // Assertions
        assertTrue(entity.getReports().contains(report));
    }

    @Test(expected = NullPointerException.class)
    public void testConstructorNullIdentifier() {
        // Execution
        ConcreteReportableEntity entity = new ConcreteReportableEntity(null, null);
    }

    @Test(expected = NullPointerException.class)
    public void testConstructorNullReport() {
        // Test data
        String identifier = "ThisIsASpecialIdentifier123!";

        // Execution
        ConcreteReportableEntity entity = new ConcreteReportableEntity(identifier, null);
    }

    @Test
    public void testAddReport() {
        // Test data
        Collection<Report> reports = new LinkedList<>();
        ReportFactory reportFactory = new ReportFactory();
        Report report1 = reportFactory.getValidObject();
        Report report2 = reportFactory.getValidObject();
        reports.add(report1);
        reports.add(report2);

        // Execution
        ConcreteReportableEntity entity = new ConcreteReportableEntity();
        entity.addReport(report1);
        entity.addReport(report2);

        // Assertions
        assertFalse(report1.equals(report2));
        assertTrue(entity.getReports().containsAll(reports));
    }

    @Test(expected = NullPointerException.class)
    public void testAddReportNull() {
        // Execution
        ConcreteReportableEntity entity = new ConcreteReportableEntity();
        entity.addReport(null);
    }
}
