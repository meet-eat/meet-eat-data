package meet_eat.data.entity.user.setting;

import org.junit.Test;

import static org.junit.Assert.*;

public class DisplaySettingCommonTest {

    private static final ColorMode COLOR_MODE_DEFAULT = ColorMode.DARK;

    @Test
    public void testConstructorEmpty() {
        // Execution
        DisplaySetting displaySetting = new DisplaySetting();

        // Assertions
        assertEquals(COLOR_MODE_DEFAULT, displaySetting.getColorMode());
    }

    @Test
    public void testConstructorColorMode() {
        // Test data
        ColorMode colorMode = ColorMode.SYSTEM;

        // Execution
        DisplaySetting displaySetting = new DisplaySetting(colorMode);

        // Assertions
        assertEquals(colorMode, displaySetting.getColorMode());
    }

    @Test(expected = NullPointerException.class)
    public void testConstructorNull() {
        // Execution
        DisplaySetting displaySetting = new DisplaySetting(null);
    }

    @Test
    public void testSetterColorMode() {
        // Test data
        ColorMode colorMode = ColorMode.DARK;
        DisplaySetting displaySetting = new DisplaySetting();

        // Execution
        displaySetting.setColorMode(colorMode);

        // Assertions
        assertEquals(colorMode, displaySetting.getColorMode());
    }

    @Test(expected = NullPointerException.class)
    public void testSetterColorModeNull() {
        // Test data
        DisplaySetting displaySetting = new DisplaySetting();

        // Execution
        displaySetting.setColorMode(null);
    }

    @Test
    public void testEquals() {
        DisplaySetting displaySetting = new DisplaySetting(ColorMode.DARK);
        DisplaySetting displaySettingCopy = new DisplaySetting(displaySetting.getColorMode());

        // Assertions
        assertTrue(displaySetting.equals(displaySetting));
        assertFalse(displaySetting.equals(null));
        assertFalse(displaySetting.equals(new Object()));
        assertTrue(displaySetting.equals(displaySettingCopy));
        assertEquals(displaySetting.hashCode(), displaySettingCopy.hashCode());
    }
}
