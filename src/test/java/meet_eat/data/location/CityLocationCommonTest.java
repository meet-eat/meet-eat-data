package meet_eat.data.location;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class CityLocationCommonTest {

    @Test
    public void testConstructor() {
        // Test data
        String cityName = "Karlsruhe";

        // Execution
        CityLocation cityLocation = new CityLocation(cityName);

        // Assertions
        assertNotNull(cityLocation);
        assertEquals(cityName, cityLocation.getCityName());
    }

    @Test(expected = NullPointerException.class)
    public void testConstructorNullCityName() {
        // Execution
        CityLocation cityLocation = new CityLocation(null);
    }

    @Test
    public void testSetCityName() {
        // Test data
        String cityName = "TestStadt";

        // Execution
        CityLocation cityLocation = new CityLocation("");
        cityLocation.setCityName(cityName);

        // Assertions
        assertEquals(cityName, cityLocation.getCityName());
    }

    @Test(expected = NullPointerException.class)
    public void testSetNullCityName() {
        // Execution
        CityLocation cityLocation = new CityLocation("");
        cityLocation.setCityName(null);
    }
}
