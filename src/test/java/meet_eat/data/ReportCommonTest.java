package meet_eat.data;

import meet_eat.data.entity.relation.Report;
import meet_eat.data.entity.user.User;
import meet_eat.data.factory.ReportFactory;
import meet_eat.data.factory.UserFactory;
import org.junit.Test;

import static org.junit.Assert.*;

public class ReportCommonTest {

    @Test
    public void testConstructor() {
        // Test data
        UserFactory userFactory = new UserFactory();
        User reporter = userFactory.getValidObject();
        User reported = userFactory.getValidObject();
        String message = "MyReportMessage";

        // Execution
        Report report = new Report(reporter, reported, message);

        // Assertions
        assertNotNull(report);
        assertEquals(reporter, report.getSource());
        assertEquals(reported, report.getTarget());
        assertEquals(message, report.getMessage());
    }

    @Test(expected = NullPointerException.class)
    public void testConstructorWithNullSource() {
        // Test data
        UserFactory userFactory = new UserFactory();
        String message = "MyReportMessage";
        User reported = userFactory.getValidObject();

        // Execution
        new Report(null, reported, message);
    }

    @Test(expected = NullPointerException.class)
    public void testConstructorWithNullTarget() {
        // Test data
        UserFactory userFactory = new UserFactory();
        User reporter = userFactory.getValidObject();
        String message = "MyReportMessage";

        // Execution
        new Report(reporter, null, message);
    }

    @Test(expected = NullPointerException.class)
    public void testConstructorWithNullMessage() {
        // Test data
        UserFactory userFactory = new UserFactory();
        User reporter = userFactory.getValidObject();
        User reported = userFactory.getValidObject();

        // Execution
        new Report(reporter, reported, null);
    }

    @Test
    public void testSetProcessed() {
        // Test data
        ReportFactory reportFactory = new ReportFactory();
        Report report = reportFactory.getValidObject();

        // Execution
        report.setProcessed(true);

        // Assertions
        assertNotNull(report);
        assertTrue(report.isProcessed());
    }
}
