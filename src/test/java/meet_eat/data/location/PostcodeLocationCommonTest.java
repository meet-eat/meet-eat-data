package meet_eat.data.location;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class PostcodeLocationCommonTest {

    @Test
    public void testConstructor() {
        // Test data
        String postcode = "76137";

        // Execution
        PostcodeLocation postcodeLocation = new PostcodeLocation(postcode);

        // Assertions
        assertNotNull(postcodeLocation);
        assertEquals(postcode, postcodeLocation.getPostcode());
    }

    @Test(expected = NullPointerException.class)
    public void testConstructorNullPostcode() {
        // Execution
        new PostcodeLocation(null);
    }

    @Test
    public void testSetPostcode() {
        // Test data
        String postcode = "ExamplePostcode123";

        // Execution
        PostcodeLocation postcodeLocation = new PostcodeLocation("");
        postcodeLocation.setPostcode(postcode);

        // Assertions
        assertEquals(postcode, postcodeLocation.getPostcode());
    }

    @Test(expected = NullPointerException.class)
    public void testSetNullPostcode() {
        // Execution
        PostcodeLocation postcodeLocation = new PostcodeLocation("");
        postcodeLocation.setPostcode(null);
    }
}
