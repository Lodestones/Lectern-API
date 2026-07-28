package gg.lode.lecternapi.api.verity;

import java.util.Locale;

/**
 * The animations the Verity monster model ships with.
 * <p>
 * Locomotion is normally inferred from the entity's own movement — the client cross-fades
 * {@link #IDLE}, {@link #WALK} and {@link #RUN} by speed, or their crawling counterparts —
 * so those rarely need naming explicitly. The one-shots are the interesting ones: they play
 * over the top of whatever the monster is doing and hand control back when they finish.
 * <p>
 * The wire identifiers are the clip names inside the model file rather than tidied-up
 * constants, so the two cannot drift apart: a rename in the asset is a rename here.
 */
public enum VerityClip {

    /** Standing still. Selected automatically when the entity isn't moving. */
    IDLE("Idle"),
    /** Walking. Cross-faded in automatically with speed. */
    WALK("Walk"),
    /** Running. Cross-faded in automatically at higher speed. */
    RUN("Run"),
    /** Idling on all fours. Used in place of {@link #IDLE} while crawling. */
    CRAWL_IDLE("Crawl Idle"),
    /** Crawling along. Used in place of {@link #WALK} while crawling. */
    CRAWL_WALK("Crawl Walk"),

    /**
     * The kill. Reaches down, lifts its victim, and bites at roughly 4.6 seconds in.
     * <p>
     * The only clip carrying its own camera: given a viewer, the client puts that player
     * behind it for the duration — on the floor in front of the monster, looking back up at
     * it. Paired with a grabbed victim, this is also what drives the bite and leaves a corpse.
     */
    JUMPSCARE("Jumpscare"),
    /** Transforming into the monster. */
    MONSTER_TRANSFORM("Monster Transform"),
    /** Transforming back into the ball. */
    BALL_TRANSFORM("Ball Transform");

    private final String id;

    VerityClip(String id) {
        this.id = id;
    }

    /** Wire identifier sent to the client — the clip's name inside the model. */
    public String getId() {
        return id;
    }

    /**
     * Command-friendly token — the lowercase enum name, e.g. {@code crawl_idle}.
     * <p>
     * Separate from {@link #getId()} because the wire identifiers are the clip names inside
     * the model and several contain spaces, which a single-word command argument cannot carry.
     */
    public String getToken() {
        return name().toLowerCase(Locale.ROOT);
    }

    /**
     * Resolves either form — the wire identifier ({@code "Crawl Idle"}) or the command token
     * ({@code "crawl_idle"}) — or null if it names no clip.
     */
    public static VerityClip parse(String value) {
        if (value == null) return null;
        VerityClip byId = byId(value);
        if (byId != null) return byId;
        String token = value.trim().toUpperCase(Locale.ROOT).replace(' ', '_');
        for (VerityClip clip : values()) {
            if (clip.name().equals(token)) return clip;
        }
        return null;
    }

    /** Resolves a wire identifier, case-insensitively, or null if it names no clip. */
    public static VerityClip byId(String id) {
        if (id == null) return null;
        String normalized = id.trim();
        for (VerityClip clip : values()) {
            if (clip.id.equalsIgnoreCase(normalized)) return clip;
        }
        return null;
    }
}
