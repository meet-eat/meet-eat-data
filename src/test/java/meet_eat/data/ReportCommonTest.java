package meet_eat.data;

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
        User user = userFactory.getValidObject();
        String message = "MyReportMessage";

        // Execution
        Report report = new Report(user, message);

        // Assertions
        assertNotNull(report);
        assertEquals(user, report.getReporter());
        assertEquals(message, report.getMessage());
    }

    @Test(expected = NullPointerException.class)
    public void testConstructorWithNullReporterAndMessage() {
        // Test data
        String message = "MyReportMessage";

        // Execution
        Report report = new Report(null, message);
    }

    @Test(expected = NullPointerException.class)
    public void testConstructorWithReporterAndNullMessage() {
        // Test data
        UserFactory userFactory = new UserFactory();
        User user = userFactory.getValidObject();

        // Execution
        Report report = new Report(user, null);
    }

    @Test
    public void testSetProcessed() {
        // Test data
        ReportFactory reportFactory = new ReportFactory();
        Report report = reportFactory.getValidObject();
        boolean processed = true;

        // Execution
        report.setProcessed(processed);

        // Assertions
        assertNotNull(report);
        assertEquals(processed, report.isProcessed());
    }

    @Test
    public void testEquals() {
        // Execution
        Report report = new ReportFactory().getValidObject();
        Report reportCopy = new Report(report.getReporter(), report.getMessage());

        // Assertions
        assertTrue(report.equals(report));
        assertFalse(report.equals(null));
        assertFalse(report.equals(new Object()));
        assertTrue(report.equals(reportCopy));
        assertEquals(report.hashCode(), reportCopy.hashCode());
    }
}
