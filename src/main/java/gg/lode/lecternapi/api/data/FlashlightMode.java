package gg.lode.lecternapi.api.data;

/**
 * The three states a flashlight can be in.
 *
 * <p>{@link #FLICKER} is its own state rather than a separate switch because a flickering light
 * that is switched off is not a thing anyone needs to express, and two booleans would allow it.
 */
public enum FlashlightMode {

    /** No flashlight at all. The client drops the shader pack and the world lights normally again. */
    OFF,

    /**
     * The darkness stays, the beam does not. Someone holding a flashlight they have switched off:
     * the pack keeps rendering, so the room is still as black as it was.
     */
    DISABLED,

    /** A steady beam. */
    ON,

    /** A beam that cuts out at random, for a light that is about to give up. */
    FLICKER
}
