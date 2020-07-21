package meet_eat.data.entity.user.setting;

import java.util.Objects;

public class DisplaySetting implements Setting {

    private static final String ERROR_MESSAGE_NOT_IMPLEMENTED = "This function is not implemented yet.";
    private static final String ERROR_MESSAGE_TEMPLATE_NULL = "The %s must not be null.";
    private static final String ERROR_MESSAGE_NULL_COLOR_MODE = String.format(ERROR_MESSAGE_TEMPLATE_NULL, "colorMode");
    private static final ColorMode COLOR_MODE_DEFAULT = ColorMode.DARK;

    private ColorMode colorMode;

    public DisplaySetting() {
        this.colorMode = COLOR_MODE_DEFAULT;
    }

    public DisplaySetting(ColorMode colorMode) {
        this.colorMode = Objects.requireNonNull(colorMode, ERROR_MESSAGE_NULL_COLOR_MODE);;
    }

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
}