package gg.lode.lecternapi.api.menu;

/**
 * Horizontal alignment for menu elements on screen.
 */
public enum HorizontalAlignment {
    LEFT("left"),
    CENTER("center"),
    RIGHT("right");

    private final String value;

    HorizontalAlignment(String value) {
        this.value = value;
    }

    /**
     * Returns the protocol string value sent to the Fabric client.
     */
    public String value() {
        return value;
    }
}
