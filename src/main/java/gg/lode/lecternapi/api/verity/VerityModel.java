package gg.lode.lecternapi.api.verity;

import java.util.Locale;

/** Which Verity model an entity wears. */
public enum VerityModel {
    /** The face sphere. */
    BALL,
    /** The cardboard shipping box the ball arrives in. */
    BOX;

    public static final VerityModel DEFAULT = BALL;

    /** Wire identifier sent to the client. */
    public String getId() {
        return name().toLowerCase(Locale.ROOT);
    }

    public static VerityModel byId(String id) {
        if (id == null) return DEFAULT;
        return "box".equalsIgnoreCase(id.trim()) ? BOX : BALL;
    }
}
