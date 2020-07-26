package meet_eat.data;

import static org.junit.Assert.*;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import meet_eat.data.entity.user.Email;
import meet_eat.data.entity.user.Password;
import meet_eat.data.entity.user.User;
import meet_eat.data.location.CityLocation;
import meet_eat.data.location.Localizable;
import meet_eat.data.location.PostcodeLocation;
import meet_eat.data.location.SphericalLocation;
import meet_eat.data.location.SphericalPosition;
import meet_eat.data.location.UnlocalizableException;
import org.junit.Ignore;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;

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
        assertEquals(page, parsedObject);
    }

    @Test
    @Ignore // TODO Has to be fixed
    public void testParseReport() {
        // Test data
        User user = new User(new Email("noreply.meet.eat@gmail.com"), Password.createHashedPassword("AbcdefghijkL1!"),
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

    @Test
    public void testParseSphericalPosition() {
        // Test data
        double allowedDelta = 0d;
        SphericalPosition sphericalPosition = new SphericalPosition(-10d,  10d);

        // Execution
        ObjectJsonParser objectJsonParser = new ObjectJsonParser();
        String jsonString = objectJsonParser.parseObjectToJsonString(sphericalPosition);
        SphericalPosition parsedObject = objectJsonParser.parseJsonStringToObject(jsonString, SphericalPosition.class);

        // Assertions
        assertNotNull(jsonString);
        assertNotNull(parsedObject);
        assertEquals(sphericalPosition, parsedObject);
    }

    @Test
    public void testParseSphericalLocation() {
        // Test data
        SphericalPosition sphericalPosition = new SphericalPosition(-10d,  10d);
        SphericalLocation sphericalLocation = new SphericalLocation(sphericalPosition);

        // Execution
        ObjectJsonParser objectJsonParser = new ObjectJsonParser();
        String jsonString = objectJsonParser.parseObjectToJsonString(sphericalLocation);
        SphericalLocation parsedObject = objectJsonParser.parseJsonStringToObject(jsonString, SphericalLocation.class);

        // Assertions
        assertNotNull(jsonString);
        assertNotNull(parsedObject);
        assertEquals(sphericalLocation, parsedObject);
    }

    @Test
    public void testParseSphericalLocationAsLocalizable() throws UnlocalizableException {
        // Test data
        SphericalPosition sphericalPosition = new SphericalPosition(-10d,  10d);
        Localizable localizable = new SphericalLocation(sphericalPosition);

        // Execution
        ObjectJsonParser objectJsonParser = new ObjectJsonParser();
        String jsonString = objectJsonParser.parseObjectToJsonString(localizable);
        Localizable parsedObject = objectJsonParser.parseJsonStringToObject(jsonString, Localizable.class);

        // Assertions
        assertNotNull(jsonString);
        assertNotNull(parsedObject);
        assertEquals(SphericalLocation.class, parsedObject.getClass());
        assertEquals(localizable, parsedObject);
    }

    @Test
    public void testParseCityLocation() {
        // Test data
        CityLocation cityLocation = new CityLocation("Karlsruhe");

        // Execution
        ObjectJsonParser objectJsonParser = new ObjectJsonParser();
        String jsonString = objectJsonParser.parseObjectToJsonString(cityLocation);
        CityLocation parsedObject = objectJsonParser.parseJsonStringToObject(jsonString, CityLocation.class);

        // Assertions
        assertNotNull(jsonString);
        assertNotNull(parsedObject);
        assertEquals(cityLocation, parsedObject);
    }

    @Test
    public void testParseCityLocationAsLocalizable() {
        // Test data
        Localizable localizable = new CityLocation("Karlsruhe");

        // Execution
        ObjectJsonParser objectJsonParser = new ObjectJsonParser();
        String jsonString = objectJsonParser.parseObjectToJsonString(localizable);
        Localizable parsedObject = objectJsonParser.parseJsonStringToObject(jsonString, Localizable.class);

        // Assertions
        assertNotNull(jsonString);
        assertNotNull(parsedObject);
        assertEquals(CityLocation.class, parsedObject.getClass());
        assertEquals(localizable, parsedObject);

    }

    @Test
    public void testParsePostcodeLocation() {
        // Test data
        PostcodeLocation postcodeLocation = new PostcodeLocation("76131");

        // Execution
        ObjectJsonParser objectJsonParser = new ObjectJsonParser();
        String jsonString = objectJsonParser.parseObjectToJsonString(postcodeLocation);
        PostcodeLocation parsedObject = objectJsonParser.parseJsonStringToObject(jsonString, PostcodeLocation.class);

        // Assertions
        assertNotNull(jsonString);
        assertNotNull(parsedObject);
        assertEquals(postcodeLocation, parsedObject);
    }

    @Test
    public void testParsePostcodeLocationAsLocalizable() {
        // Test data
        Localizable localizable = new PostcodeLocation("76131");

        // Execution
        ObjectJsonParser objectJsonParser = new ObjectJsonParser();
        String jsonString = objectJsonParser.parseObjectToJsonString(localizable);
        Localizable parsedObject = objectJsonParser.parseJsonStringToObject(jsonString, Localizable.class);

        // Assertions
        assertNotNull(jsonString);
        assertNotNull(parsedObject);
        assertEquals(PostcodeLocation.class, parsedObject.getClass());
        assertEquals(localizable, parsedObject);
    }

    @Test
    public void testParseHomogeneousLocalizableCollection() {
        // Test data
        Localizable localizableFst = new PostcodeLocation("76131");
        Localizable localizableSnd = new PostcodeLocation("76131");
        Localizable localizableTrd = new PostcodeLocation("76131");
        Collection<Localizable> localizables = new LinkedList<>();
        localizables.add(localizableFst);
        localizables.add(localizableSnd);
        localizables.add(localizableTrd);

        // Execution
        ObjectJsonParser objectJsonParser = new ObjectJsonParser();
        String jsonString = objectJsonParser.parseObjectToJsonString(localizables);
        JavaType type = objectJsonParser.getObjectMapper().getTypeFactory().
                constructCollectionType(LinkedList.class, Localizable.class);
        Collection<Localizable> parsedObject = objectJsonParser.parseJsonStringToObject(jsonString, type);

        // Assertions
        assertNotNull(jsonString);
        assertNotNull(parsedObject);
        assertTrue(parsedObject.containsAll(localizables));

        parsedObject.removeAll(localizables);
        assertEquals(0, parsedObject.size());
    }

    @Test
    public void testParseHeterogeneousLocalizableCollection() {
        // Test data
        Localizable localizableFst = new PostcodeLocation("76131");
        Localizable localizableSnd = new SphericalLocation(new SphericalPosition(-10, 10));
        Localizable localizableTrd = new CityLocation("Karlsruhe");
        Collection<Localizable> localizables = new LinkedList<>();
        localizables.add(localizableFst);
        localizables.add(localizableSnd);
        localizables.add(localizableTrd);

        // Execution
        ObjectJsonParser objectJsonParser = new ObjectJsonParser();
        String jsonString = objectJsonParser.parseObjectToJsonString(localizables);
        JavaType type = objectJsonParser.getObjectMapper().getTypeFactory().
                constructCollectionType(LinkedList.class, Localizable.class);
        Collection<Localizable> parsedObject = objectJsonParser.parseJsonStringToObject(jsonString, type);

        System.out.println(jsonString);

        // Assertions
        assertNotNull(jsonString);
        assertNotNull(parsedObject);
        assertTrue(parsedObject.containsAll(localizables));

        parsedObject.removeAll(localizables);
        assertEquals(0, parsedObject.size());
    }

    @Test
    public void testParseHeterogeneousLocalizableCollectionWithDefaultDeserializer() {
        // Test data
        Localizable localizableFst = new PostcodeLocation("76131");
        Localizable localizableSnd = new SphericalLocation(new SphericalPosition(-10, 10));
        Localizable localizableTrd = new CityLocation("Karlsruhe");
        Collection<Localizable> localizables = new LinkedList<>();
        localizables.add(localizableFst);
        localizables.add(localizableSnd);
        localizables.add(localizableTrd);

        // Execution - Serialization
        ObjectJsonParser objectSerializer = new ObjectJsonParser();
        String jsonString = objectSerializer.parseObjectToJsonString(localizables);
        JavaType type = objectSerializer.getObjectMapper().getTypeFactory().
                constructCollectionType(LinkedList.class, Localizable.class);

        // Execution - Deserialization
        ObjectJsonParser objectDeserializer = new ObjectJsonParser(new ObjectMapper());
        Collection<Localizable> parsedObject = objectDeserializer.parseJsonStringToObject(jsonString, type);

        // Assertions
        assertNotNull(jsonString);
        assertNotNull(parsedObject);
        assertTrue(parsedObject.containsAll(localizables));

        parsedObject.removeAll(localizables);
        assertEquals(0, parsedObject.size());
    }

    @Test
    public void testParseIntegerCollectionWithDefaultDeserializer() {
        // Test data
        Collection<Integer> integers = new LinkedList<>();
        integers.add(0);
        integers.add(4);
        integers.add(2);

        // Execution - Serialization
        ObjectJsonParser objectSerializer = new ObjectJsonParser();
        String jsonString = objectSerializer.parseObjectToJsonString(integers);
        JavaType type = objectSerializer.getObjectMapper().getTypeFactory().
                constructCollectionType(LinkedList.class, Integer.class);

        // Execution - Deserialization
        ObjectJsonParser objectDeserializer = new ObjectJsonParser(new ObjectMapper());
        Collection<Localizable> parsedObject = objectDeserializer.parseJsonStringToObject(jsonString, type);

        // Assertions
        assertNotNull(jsonString);
        assertNotNull(parsedObject);
        assertTrue(parsedObject.containsAll(integers));

        parsedObject.removeAll(integers);
        assertEquals(0, parsedObject.size());
    }

    @Test
    public void testParseStringCollectionWithDefaultDeserializer() {
        // Test data
        Collection<String> strings = new LinkedList<>();
        strings.add("Hello ");
        strings.add("World");
        strings.add("!");

        // Execution - Serialization
        ObjectJsonParser objectSerializer = new ObjectJsonParser();
        String jsonString = objectSerializer.parseObjectToJsonString(strings);
        JavaType type = objectSerializer.getObjectMapper().getTypeFactory().
                constructCollectionType(LinkedList.class, String.class);

        // Execution - Deserialization
        ObjectJsonParser objectDeserializer = new ObjectJsonParser(new ObjectMapper());
        Collection<Localizable> parsedObject = objectDeserializer.parseJsonStringToObject(jsonString, type);

        // Assertions
        assertNotNull(jsonString);
        assertNotNull(parsedObject);
        assertTrue(parsedObject.containsAll(strings));

        parsedObject.removeAll(strings);
        assertEquals(0, parsedObject.size());
    }

    // TODO Add and repair all data classes
}
