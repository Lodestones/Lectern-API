package gg.lode.lecternapi.api.manager;

import org.bukkit.entity.Player;

import java.util.UUID;

/**
 * Manages aura effects for players running the Lectern client mod.
 * Provides three aura types:
 *
 * <p><b>Chronite Aura:</b> A totem-style particle burst with orbiting rings
 * and converging spark particles. Configurable color. Plays on a target entity for ~2 seconds.</p>
 *
 * <p><b>Shockwave Aura:</b> An expanding shockwave ring at a world position with
 * configurable color and radius. The ring expands quickly then decelerates while fading out.</p>
 *
 * <p><b>Locational Aura:</b> A fixed-radius circle rendered flat on the ground at a world
 * position. Configurable color, radius, and lifespan. Fades in and out smoothly.</p>
 */
public interface IAuraManager {

    // --- Chronite Aura (totem-style) ---

    /**
     * Plays the chronite aura effect on a target entity as seen by the viewer.
     * The effect renders orbiting rings around the target with
     * converging spark particles, lasting approximately 2 seconds.
     *
     * @param viewer the player whose client will render the effect
     * @param target the UUID of the entity to play the aura on
     * @param red    red color component (0-255)
     * @param green  green color component (0-255)
     * @param blue   blue color component (0-255)
     */
    void playChroniteAura(Player viewer, UUID target, int red, int green, int blue);

    /**
     * Stops the chronite aura on a target entity.
     *
     * @param viewer the player whose client will stop rendering the effect
     * @param target the UUID of the entity
     */
    void stopChroniteAura(Player viewer, UUID target);

    /**
     * Clears all chronite aura effects on the viewer's client.
     *
     * @param viewer the player whose effects should be cleared
     */
    void clearChroniteAuras(Player viewer);

    // --- Shockwave Aura (expanding ring) ---

    /**
     * Plays an expanding shockwave ring effect at the specified world position.
     * The ring expands outward with deceleration and fades out.
     * Supports arbitrarily large radii — only the visible arc near the player is rendered.
     *
     * @param player the target player
     * @param id     a unique identifier for this shockwave
     * @param x      the world x coordinate (center of the shockwave)
     * @param y      the world y coordinate (ground level)
     * @param z      the world z coordinate (center of the shockwave)
     * @param red    red color component (0-255)
     * @param green  green color component (0-255)
     * @param blue   blue color component (0-255)
     * @param radius   the maximum expansion radius in blocks
     * @param speed    speed multiplier (1.0 = default, 2.0 = twice as fast, 0.5 = half speed)
     * @param reversed if true, the ring starts at full radius and contracts inward to the origin
     */
    void playShockwaveAura(Player player, String id, float x, float y, float z,
                           int red, int green, int blue, float radius, float speed, boolean reversed);

    /**
     * Stops a shockwave aura by its identifier.
     *
     * @param player the target player
     * @param id     the shockwave identifier
     */
    void stopShockwaveAura(Player player, String id);

    /**
     * Clears all shockwave aura effects on the target player's client.
     *
     * @param player the target player
     */
    void clearShockwaveAuras(Player player);

    // --- Locational Aura (fixed circle) ---

    /**
     * Adds a fixed-radius ring aura at the specified world position.
     * The ring is rendered flat on the ground with a smooth fade-in.
     * It persists until explicitly removed via {@link #removeLocationalAura}.
     *
     * @param player    the target player
     * @param id        a unique identifier for this aura (used to remove it later)
     * @param x         the world x coordinate (center)
     * @param y         the world y coordinate (ground level)
     * @param z         the world z coordinate (center)
     * @param red       red color component (0-255)
     * @param green     green color component (0-255)
     * @param blue      blue color component (0-255)
     * @param radius    the ring radius in blocks
     * @param thickness the ring line thickness (0.01 = thin hairline, 0.5 = very thick, default ~0.05)
     */
    void addLocationalAura(Player player, String id, float x, float y, float z,
                           int red, int green, int blue, float radius, float thickness);

    /**
     * Removes a locational aura by its identifier.
     *
     * @param player the target player
     * @param id     the aura identifier
     */
    void removeLocationalAura(Player player, String id);

    /**
     * Clears all locational aura effects on the target player's client.
     *
     * @param player the target player
     */
    void clearLocationalAuras(Player player);
}
