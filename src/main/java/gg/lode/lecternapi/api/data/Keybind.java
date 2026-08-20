package gg.lode.lecternapi.api.data;

/**
 * A Minecraft keybind that can be disabled or enabled on the Lectern client.
 *
 * <p>The string each one carries is the identifier the client matches on, so a constant only works
 * if the client knows the same spelling. Every value here is one the client maps.
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
    SPRINT("sprint"),
    INVENTORY("inventory"),
    DROP("drop"),
    SWAP_OFFHAND("swap_offhand"),
    PICK_ITEM("pick_item"),
    CHAT("chat"),
    COMMAND("command"),
    /** Tab — the player list. */
    PLAYER_LIST("player_list"),
    ADVANCEMENTS("advancements"),
    TOGGLE_PERSPECTIVE("toggle_perspective"),
    SMOOTH_CAMERA("smooth_camera"),
    SPECTATOR_OUTLINES("spectator_outlines"),
    SCREENSHOT("screenshot"),
    FULLSCREEN("fullscreen"),
    SAVE_HOTBAR_TOOLBAR("save_hotbar_toolbar"),
    LOAD_HOTBAR_TOOLBAR("load_hotbar_toolbar"),
    HOTBAR_1("hotbar_1"),
    HOTBAR_2("hotbar_2"),
    HOTBAR_3("hotbar_3"),
    HOTBAR_4("hotbar_4"),
    HOTBAR_5("hotbar_5"),
    HOTBAR_6("hotbar_6"),
    HOTBAR_7("hotbar_7"),
    HOTBAR_8("hotbar_8"),
    HOTBAR_9("hotbar_9");

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
