package meet_eat.data.entity.user.setting;

import org.junit.Test;

import static org.junit.Assert.*;

public class NotificationSettingCommonTest {

    private static final boolean NOTIFICATION_DEFAULT = true;
    private static final int MINUTES_UNTIL_OFFER_DEFAULT = 60;

    @Test
    public void testConstructorEmpty() {
        // Execution
        NotificationSetting notificationSetting = new NotificationSetting();

        // Assertions
        assertEquals(NOTIFICATION_DEFAULT, notificationSetting.isEnabled());
        assertEquals(MINUTES_UNTIL_OFFER_DEFAULT, notificationSetting.getMinutesUntilOffer());
    }

    @Test
    public void testConstructor() {
        // Test data
        boolean enabled = false;
        int minutesUntilOffer = 120;

        // Execution
        NotificationSetting notificationSetting = new NotificationSetting(enabled, minutesUntilOffer);

        // Assertions
        assertEquals(enabled, notificationSetting.isEnabled());
        assertEquals(minutesUntilOffer, notificationSetting.getMinutesUntilOffer());
    }

    @Test
    public void testSetterEnabled() {
        // Test data
        boolean enabled = false;

        // Execution
        NotificationSetting notificationSetting = new NotificationSetting();
        notificationSetting.setEnabled(enabled);

        // Assertions
        assertEquals(enabled, notificationSetting.isEnabled());
    }

    @Test
    public void testSetterMinutes() {
        // Test data
        int minutesUntilOffer = 240;

        // Execution
        NotificationSetting notificationSetting = new NotificationSetting();
        notificationSetting.setMinutesUntilOffer(minutesUntilOffer);

        // Assertions
        assertEquals(minutesUntilOffer, notificationSetting.getMinutesUntilOffer());
    }

    @Test
    public void testEquals() {
        NotificationSetting notificatonSetting = new NotificationSetting(false, 60);
        NotificationSetting notificationSettingCopy = new NotificationSetting(notificatonSetting.isEnabled(), notificatonSetting.getMinutesUntilOffer());

        // Assertions
        assertEquals(notificatonSetting, notificatonSetting);
        assertNotEquals(null, notificatonSetting);
        assertNotEquals(notificatonSetting, new Object());
        assertEquals(notificatonSetting, notificationSettingCopy);
        assertEquals(notificatonSetting.hashCode(), notificationSettingCopy.hashCode());
    }
}
