package gg.lode.lecternapi.api.manager;

import org.bukkit.Location;
import org.bukkit.entity.Player;

/**
 * Plays Effekseer particle effects on a player's client.
 * <p>
 * Effekseer effects are authored VFX files ({@code .efkefc}) shipped inside the Lectern
 * client mod or a resource pack, loaded by the client under an id such as
 * {@code lodestone:explosion}. They are a different animal from {@link IParticleManager}'s
 * vanilla-style particles: a single play can be an entire choreographed effect — beams,
 * shockwaves, ribbons — with a lifetime of its own.
 * <p>
 * A play can optionally carry an <b>emitter name</b>, which turns it into an addressable
 * slot: the same name can later be moved, retuned via dynamic inputs, triggered, or stopped.
 * Playing again under the same name replaces the previous emitter. An empty name is
 * fire-and-forget.
 * <p>
 * Effects the client does not have are silently ignored — a missing visual stays cosmetic.
 * All rotations are radians; scale of 1 is the authored size.
 */
public interface IEffekManager {

    /**
     * Plays an effect at a position.
     *
     * @param player      the target player
     * @param effekId     the effect id as loaded by the client, e.g. {@code lodestone:explosion}
     * @param emitterName addressable name for this emitter, or {@code ""} for fire-and-forget
     * @param x           world X
     * @param y           world Y
     * @param z           world Z
     * @param rotX        rotation around X in radians
     * @param rotY        rotation around Y in radians
     * @param rotZ        rotation around Z in radians
     * @param scaleX      X scale, 1 = authored size
     * @param scaleY      Y scale
     * @param scaleZ      Z scale
     */
    default void playEffek(Player player, String effekId, String emitterName,
                           double x, double y, double z,
                           float rotX, float rotY, float rotZ,
                           float scaleX, float scaleY, float scaleZ) {
    }

    /** Fire-and-forget play at a location, unrotated, at authored size. */
    default void playEffek(Player player, String effekId, Location location) {
        playEffek(player, effekId, "", location.getX(), location.getY(), location.getZ(),
                0, 0, 0, 1, 1, 1);
    }

    /** Fire-and-forget play at a location with a uniform scale. */
    default void playEffek(Player player, String effekId, Location location, float scale) {
        playEffek(player, effekId, "", location.getX(), location.getY(), location.getZ(),
                0, 0, 0, scale, scale, scale);
    }

    /** Named play at a location — the emitter stays addressable under {@code emitterName}. */
    default void playEffek(Player player, String effekId, String emitterName, Location location, float scale) {
        playEffek(player, effekId, emitterName, location.getX(), location.getY(), location.getZ(),
                0, 0, 0, scale, scale, scale);
    }

    /**
     * Moves a named emitter to a new position. Unknown names are ignored.
     */
    default void moveEffek(Player player, String effekId, String emitterName,
                           double x, double y, double z) {
    }

    /** Convenience overload of {@link #moveEffek(Player, String, String, double, double, double)}. */
    default void moveEffek(Player player, String effekId, String emitterName, Location location) {
        moveEffek(player, effekId, emitterName, location.getX(), location.getY(), location.getZ());
    }

    /**
     * Sets a dynamic input on a named emitter — the four float knobs (slots 0-3) an
     * Effekseer effect exposes to its host, with effect-defined meaning.
     *
     * @param slot  dynamic input slot, 0-3
     * @param value the new value
     */
    default void setEffekParam(Player player, String effekId, String emitterName, int slot, float value) {
    }

    /**
     * Fires a trigger (slots 0-3) on a named emitter — effect-defined one-shot signals,
     * e.g. "detonate now".
     *
     * @param slot trigger slot, 0-3
     */
    default void triggerEffek(Player player, String effekId, String emitterName, int slot) {
    }

    /**
     * Stops a named emitter of an effect. With an empty name, stops every emitter of that
     * effect on the player's client.
     */
    default void stopEffek(Player player, String effekId, String emitterName) {
    }

    /** Stops every emitter of every effect on the player's client. */
    default void clearEffeks(Player player) {
    }
}
