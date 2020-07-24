package meet_eat.data;

import static org.junit.Assert.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import meet_eat.data.entity.user.Email;
import meet_eat.data.entity.user.Password;
import meet_eat.data.entity.user.User;
import org.junit.Ignore;
import org.junit.Test;

import java.time.LocalDate;

public class ObjectJsonParserCommonTest {

    @Test
    public void testConstructorEmpty() {
        // Execution
        ObjectJsonParser objectJsonParser = new ObjectJsonParser();

        // Assertions
        assertNotNull(objectJsonParser);
        assertNotNull(objectJsonParser.getObjectMapper());
    }

    @Test
    public void testConstructor() {
        // Test data
        ObjectMapper objectMapper = new ObjectMapper();

        // Execution
        ObjectJsonParser objectJsonParser = new ObjectJsonParser(objectMapper);

        // Assertions
        assertNotNull(objectJsonParser);
        assertNotNull(objectJsonParser.getObjectMapper());
        assertEquals(objectMapper, objectJsonParser.getObjectMapper());
    }

    @Test
    public void testParseString() {
        // Test data
        String testString = "Hello World!";

        // Execution
        ObjectJsonParser objectJsonParser = new ObjectJsonParser();
        String jsonString = objectJsonParser.parseObjectToJsonString(testString);

        // Assertions
        assertNotNull(jsonString);
        assertEquals(testString, objectJsonParser.parseJsonStringToObject(jsonString, String.class));
    }

    @Test
    public void testParsePage() {
        // Test data
        Page page = new Page(2, 25);

        // Execution
        ObjectJsonParser objectJsonParser = new ObjectJsonParser();
        String jsonString = objectJsonParser.parseObjectToJsonString(page);
        Page parsedObject = objectJsonParser.parseJsonStringToObject(jsonString, Page.class);

        // Assertions
        assertNotNull(jsonString);
        assertNotNull(parsedObject);
        assertEquals(page.getIndex(), parsedObject.getIndex());
        assertEquals(page.getSize(), parsedObject.getSize());
    }

    @Test
    @Ignore // TODO Has to be fixed
    public void testParseReport() {
        // Test data
        User user = new User(new Email("noreply.meet.eat@gmail.com"), new Password("AbcdefghijkL1!"),
                LocalDate.now(), "Max Mustermann", "+49 12345678", "Empty Description", false);
        Report report = new Report(user, "Max does not use power saving mode.");

        // Execution
        ObjectJsonParser objectJsonParser = new ObjectJsonParser();
        String jsonString = objectJsonParser.parseObjectToJsonString(report);
        Report parsedObject = objectJsonParser.parseJsonStringToObject(jsonString, Report.class);

        // Assertions
        assertNotNull(jsonString);
        assertNotNull(parsedObject);
        assertEquals(report.getReporter(), parsedObject.getReporter());
        assertEquals(report.getMessage(), parsedObject.getMessage());
    }

    // TODO Add and repair all data classes
}
