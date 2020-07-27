package meet_eat.data.entity.user.setting;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class NotificationSetting implements Setting {

    private static final String ERROR_MESSAGE_NOT_IMPLEMENTED = "This function is not implemented yet.";
    private static final boolean NOTIFICATION_DEFAULT = true;
    private static final int MINUTES_UNTIL_OFFER_DEFAULT = 60;

    @JsonProperty
    private boolean enabled;
    @JsonProperty
    private int minutesUntilOffer;

    public NotificationSetting() {
        this.enabled = NOTIFICATION_DEFAULT;
        this.minutesUntilOffer = MINUTES_UNTIL_OFFER_DEFAULT;
    }

    @JsonCreator
    public NotificationSetting(@JsonProperty("enabled") boolean enabled, @JsonProperty("minutesUntilOffer") int minutesUntilOffer) {
        this.enabled = enabled;
        this.minutesUntilOffer = minutesUntilOffer;
    }

    @JsonGetter
    public boolean isEnabled() {
        return enabled;
    }

    @JsonGetter
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NotificationSetting that = (NotificationSetting) o;
        return enabled == that.enabled &&
                minutesUntilOffer == that.minutesUntilOffer;
    }

    @Override
    public int hashCode() {
        return Objects.hash(enabled, minutesUntilOffer);
    }
}