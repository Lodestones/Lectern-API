package gg.lode.lecternapi.api.data;

/**
 * Represents a Minecraft keybind that can be disabled/enabled on the Lectern client.
 */
public enum Keybind {
    ATTACK("attack"),
    USE("use"),
    FORWARD("forward"),
    BACK("back"),
    LEFT("left"),
    RIGHT("right"),
    JUMP("jump"),
    SNEAK("sneak"),
    INVENTORY("inventory"),
    TOGGLE_PERSPECTIVE("togglePerspective");

    private final String key;

    Keybind(String key) {
        this.key = key;
    }

    /**
     * Returns the string key identifier sent to the client.
     */
    public String key() {
        return key;
    }
}
