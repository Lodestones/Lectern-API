package gg.lode.lecternapi.api.hud;

import java.util.Locale;

/**
 * Easing curves for HUD element move animations.
 */
public enum HudEasing {
    LINEAR,
    EASE_IN,
    EASE_OUT,
    EASE_IN_OUT;

    /** Wire identifier sent to the client. */
    public String getId() {
        return name().toLowerCase(Locale.ROOT);
    }
}
