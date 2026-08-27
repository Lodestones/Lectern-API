package gg.lode.lecternapi.api.data;

/**
 * A camera perspective, matching the three the vanilla F5 key cycles through.
 *
 * <p>Sent to the client by name rather than by ordinal, so reordering this enum cannot silently
 * change what an older or newer client renders.
 */
public enum Perspective {

    /** Down the player's own eyes. */
    FIRST_PERSON,

    /** Behind the player, looking the way they are looking. */
    THIRD_PERSON_BACK,

    /** In front of the player, looking back at their face. */
    THIRD_PERSON_FRONT;

    /** The wire name, and the name a config or command would use. */
    public String id() {
        return name();
    }

    /** Parses a wire name, falling back to first person for anything unrecognised. */
    public static Perspective from(String id) {
        if (id == null) return FIRST_PERSON;
        for (Perspective perspective : values()) {
            if (perspective.name().equalsIgnoreCase(id)) return perspective;
        }
        return FIRST_PERSON;
    }
}
