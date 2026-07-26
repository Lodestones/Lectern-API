package gg.lode.lecternapi.api.hud;

import java.util.Locale;

/**
 * An individual piece of the vanilla HUD that can be hidden on its own.
 * <p>
 * For taking the whole interface away at once, hide the HUD instead — this is for stripping
 * out only what breaks a scene (hearts and hunger for something that shouldn't feel like a
 * survival game) while leaving chat, crosshair and the rest in place.
 */
public enum HudElement {
    /** The food bar. */
    HUNGER,
    /** The hotbar and its item slots. */
    HOTBAR,
    /** The health bar. */
    HEARTS,
    /** The armour bar. */
    ARMOR,
    /** The air bubbles shown underwater. */
    OXYGEN,
    /** The experience bar and the level number above it. */
    EXPERIENCE;

    /** Wire identifier sent to the client. */
    public String getId() {
        return name().toLowerCase(Locale.ROOT);
    }

    /** Resolves an element by its wire id, or null if unknown. */
    public static HudElement byId(String id) {
        if (id == null) return null;
        String normalized = id.trim().toLowerCase(Locale.ROOT);
        for (HudElement element : values()) {
            if (element.getId().equals(normalized)) return element;
        }
        return null;
    }
}
