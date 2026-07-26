package gg.lode.lecternapi.api.verity;

import java.util.Locale;

/**
 * Face states the Verity model can wear.
 * <p>
 * Each state has an idle look and, where one exists, a matching talking look that the client
 * swaps in while the instance is flagged as talking. Several states share a talking face
 * (the three {@code SERIOUS} variants all talk the same way) and a few have none, in which
 * case the idle face is reused.
 */
public enum VerityFace {
    HAPPY,
    HAPPY_SLEEP,
    NEUTRAL,
    SERIOUS_1,
    SERIOUS_2,
    SERIOUS_3,
    EVIL,
    SMILING_EVIL,
    CRAZY,
    HURT,
    NOFACE;

    public static final VerityFace DEFAULT = HAPPY;

    /** Wire identifier sent to the client. */
    public String getId() {
        return name().toLowerCase(Locale.ROOT);
    }

    /** Resolves a face by its wire id, falling back to {@link #DEFAULT} for anything unknown. */
    public static VerityFace byId(String id) {
        if (id == null) return DEFAULT;
        String normalized = id.trim().toLowerCase(Locale.ROOT);
        for (VerityFace face : values()) {
            if (face.getId().equals(normalized)) return face;
        }
        return DEFAULT;
    }
}
