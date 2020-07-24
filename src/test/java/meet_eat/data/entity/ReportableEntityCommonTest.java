package meet_eat.data.entity;

import meet_eat.data.Report;
import org.junit.Test;

import java.util.Collection;

import static org.junit.Assert.assertNotNull;

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
        // TODO
    }

    @Test(expected = NullPointerException.class)
    public void testConstructorNullIdentifier() {
        // Execution
        ConcreteReportableEntity entity = new ConcreteReportableEntity(null, null)
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
        // TODO
    }

    @Test(expected = NullPointerException.class)
    public void testAddReportNull() {
        // Excecution
        ConcreteReportableEntity entity = new ConcreteReportableEntity();
        entity.addReport(null);
    }
}
