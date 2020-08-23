package meet_eat.data.entity;

import com.google.common.collect.Lists;
import meet_eat.data.entity.relation.Report;
import meet_eat.data.factory.ReportFactory;
import org.junit.Test;

import java.util.Collection;
import java.util.LinkedList;

import static org.junit.Assert.*;

public class ReportableEntityCommonTest {

    private static class ConcreteReportableEntity extends ReportableEntity<String> {

        private static final long serialVersionUID = 4690431729311269618L;

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


    @Test
    public void testConstructorNullIdentifier() {
        // Test data
        Collection<Report> reports = new LinkedList<>();
        ReportFactory reportFactory = new ReportFactory();
        Report report = reportFactory.getValidObject();
        reports.add(report);

        // Execution
        ConcreteReportableEntity entity = new ConcreteReportableEntity(null, reports);

        // Assertions
        assertNotNull(entity);
        assertNull(entity.getIdentifier());
    }

    @Test(expected = NullPointerException.class)
    public void testConstructorNullReport() {
        // Test data
        String identifier = "ThisIsASpecialIdentifier123!";

        // Execution
        new ConcreteReportableEntity(identifier, null);
    }

    @Test
    public void testAddReport() {
        // Test data
        ReportFactory reportFactory = new ReportFactory();
        Report report1 = reportFactory.getValidObject();
        Report report2 = reportFactory.getValidObject();

        // Execution
        ConcreteReportableEntity entity = new ConcreteReportableEntity();
        entity.addReport(report1);
        entity.addReport(report2);

        // Assertions
        assertTrue(entity.getReports().stream().anyMatch(x -> x.getMessage().equals(report1.getMessage())));
        assertTrue(entity.getReports().stream().anyMatch(x -> x.getMessage().equals(report2.getMessage())));
    }

    @Test(expected = NullPointerException.class)
    public void testAddReportNull() {
        // Execution
        ConcreteReportableEntity entity = new ConcreteReportableEntity();
        entity.addReport(null);
    }

    @Test
    public void testEquals() {
        // Execution
        Collection<Report> reports = new LinkedList<>();
        LinkedList<Report> reportsCopy = Lists.newLinkedList(reports);
        ConcreteReportableEntity entity = new ConcreteReportableEntity("Identifier", reports);
        ConcreteReportableEntity entityCopy = new ConcreteReportableEntity(entity.getIdentifier(), reportsCopy);

        // Assertions
        assertEquals(entity, entity);
        assertNotEquals(null, entity);
        assertNotEquals(entity, new Object());
        assertEquals(entity, entityCopy);
        assertEquals(entity.hashCode(), entityCopy.hashCode());
    }
}
