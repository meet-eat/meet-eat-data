package meet_eat.data.entity.user.setting;

public class NotificationSetting implements Setting {

    private static final String ERROR_MESSAGE_NOT_IMPLEMENTED = "This function is not implemented yet.";
    private static final boolean NOTIFICATION_DEFAULT = true;
    private static final int MINUTES_UNTIL_OFFER_DEFAULT = 60;

    private boolean enabled;
    private int minutesUntilOffer;

    public NotificationSetting() {
        this.enabled = NOTIFICATION_DEFAULT;
        this.minutesUntilOffer = MINUTES_UNTIL_OFFER_DEFAULT;
    }

    public NotificationSetting(boolean enabled, int minutesUntilOffer) {
        this.enabled = enabled;
        this.minutesUntilOffer = minutesUntilOffer;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public int getMinutesUntilOffer() {
        return minutesUntilOffer;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public void setMinutesUntilOffer(int minutesUntilOffer) {
        this.minutesUntilOffer = minutesUntilOffer;
    }

    @Override
    public void apply() {
        // TODO Implement
        throw new UnsupportedOperationException(ERROR_MESSAGE_NOT_IMPLEMENTED);
    }
}