package gg.lode.lecternapi.api.menu;

/**
 * Vertical alignment for menu elements on screen.
 */
public enum VerticalAlignment {
    TOP("top"),
    CENTER("center"),
    BOTTOM("bottom");

    private final String value;

    VerticalAlignment(String value) {
        this.value = value;
    }

    /**
     * Returns the protocol string value sent to the Fabric client.
     */
    public String value() {
        return value;
    }
}
