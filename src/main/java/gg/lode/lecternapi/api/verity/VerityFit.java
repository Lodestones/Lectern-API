package gg.lode.lecternapi.api.verity;

import java.util.Locale;

/**
 * How the Verity ball is sized against the hitbox of the entity it replaces.
 * <p>
 * A fixed-size ball only looks right on the entity it was authored for. Deriving the
 * diameter from the hitbox instead lets the same model sit correctly on a player, a slime,
 * or an armor stand without per-entity tuning.
 */
public enum VerityFit {
    /** A fixed half-block ball, regardless of hitbox. */
    FIXED,
    /** Diameter matches the hitbox width — the ball is as wide as what it replaced. */
    WIDTH,
    /** Diameter matches the hitbox height — fills a tall entity top to bottom. */
    HEIGHT,
    /** Diameter is the smaller of width and height, so the ball always fits inside the hitbox. */
    HITBOX;

    public static final VerityFit DEFAULT = WIDTH;

    /** Wire identifier sent to the client. */
    public String getId() {
        return name().toLowerCase(Locale.ROOT);
    }

    public static VerityFit byId(String id) {
        if (id == null) return DEFAULT;
        String normalized = id.trim().toLowerCase(Locale.ROOT);
        for (VerityFit fit : values()) {
            if (fit.getId().equals(normalized)) return fit;
        }
        return DEFAULT;
    }
}
