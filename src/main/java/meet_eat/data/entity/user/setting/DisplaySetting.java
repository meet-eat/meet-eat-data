package meet_eat.data.entity.user.setting;

public class DisplaySetting implements Setting {

    private static final String ERROR_MESSAGE_NOT_IMPLEMENTED = "This function is not implemented yet.";
    private static final ColorMode COLOR_MODE_DEFAULT = ColorMode.DARK;

    private ColorMode colorMode;

    public DisplaySetting() {
        this.colorMode = COLOR_MODE_DEFAULT;
    }

    public DisplaySetting(ColorMode colorMode) {
        this.colorMode = colorMode;
    }

    public ColorMode getColorMode() {
        return colorMode;
    }

    public void setColorMode(ColorMode colorMode) {
        this.colorMode = colorMode;
    }

    @Override
    public void apply() {
        // TODO Implement
        throw new UnsupportedOperationException(ERROR_MESSAGE_NOT_IMPLEMENTED);
    }
}