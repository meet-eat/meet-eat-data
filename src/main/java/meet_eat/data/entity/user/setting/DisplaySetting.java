package meet_eat.data.entity.user.setting;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class DisplaySetting implements Setting {

    private static final String ERROR_MESSAGE_NOT_IMPLEMENTED = "This function is not implemented yet.";
    private static final String ERROR_MESSAGE_TEMPLATE_NULL = "The %s must not be null.";
    private static final String ERROR_MESSAGE_NULL_COLOR_MODE = String.format(ERROR_MESSAGE_TEMPLATE_NULL, "colorMode");
    private static final ColorMode COLOR_MODE_DEFAULT = ColorMode.DARK;

    @JsonProperty
    private ColorMode colorMode;

    public DisplaySetting() {
        this.colorMode = COLOR_MODE_DEFAULT;
    }

    @JsonCreator
    public DisplaySetting(@JsonProperty("colorMode") ColorMode colorMode) {
        this.colorMode = Objects.requireNonNull(colorMode, ERROR_MESSAGE_NULL_COLOR_MODE);;
    }

    @JsonGetter
    public ColorMode getColorMode() {
        return colorMode;
    }

    public void setColorMode(ColorMode colorMode) {
        this.colorMode = Objects.requireNonNull(colorMode, ERROR_MESSAGE_NULL_COLOR_MODE);;
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
        DisplaySetting that = (DisplaySetting) o;
        return colorMode == that.colorMode;
    }

    @Override
    public int hashCode() {
        return Objects.hash(colorMode);
    }
}