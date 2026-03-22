package gg.lode.lecternapi.api.manager;

import org.bukkit.entity.Player;

/**
 * Manages flare effects for players running the Lectern client mod.
 * Controls world-space flare projectiles with configurable color, physics, and lifetime.
 *
 * <p>Flares come in two modes:</p>
 * <ul>
 *   <li><b>Illuminating</b> ({@code trackable=false}): Falls with gravity and drag,
 *       sticks to blocks on contact. Good for lighting up caves or marking ground positions.</li>
 *   <li><b>Signal</b> ({@code trackable=true}): Drifts with near-zero gravity and minimal drag,
 *       visible from extreme distances. Good for sky markers and long-range signals.</li>
 * </ul>
 */
public interface IFlareManager {

    /**
     * Spawns a flare at the given position with initial velocity.
     *
     * @param player    the target player
     * @param id        a unique identifier for this flare
     * @param x         the world x spawn coordinate
     * @param y         the world y spawn coordinate
     * @param z         the world z spawn coordinate
     * @param velocityX initial x velocity (blocks per tick)
     * @param velocityY initial y velocity (blocks per tick)
     * @param velocityZ initial z velocity (blocks per tick)
     * @param red       red color component (0-255)
     * @param green     green color component (0-255)
     * @param blue      blue color component (0-255)
     * @param size      base size of the flare (0.1+)
     * @param fadeIn    fade-in duration in seconds
     * @param duration  full-brightness duration in seconds
     * @param fadeOut   fade-out duration in seconds
     * @param trackable if true, creates a signal flare (floats); if false, illuminating (falls with gravity)
     */
    void addFlare(Player player, String id, float x, float y, float z,
                  float velocityX, float velocityY, float velocityZ,
                  int red, int green, int blue,
                  float size, float fadeIn, float duration, float fadeOut, boolean trackable);

    /**
     * Spawns a stationary flare at the given position with no initial velocity.
     *
     * @param player    the target player
     * @param id        a unique identifier for this flare
     * @param x         the world x spawn coordinate
     * @param y         the world y spawn coordinate
     * @param z         the world z spawn coordinate
     * @param red       red color component (0-255)
     * @param green     green color component (0-255)
     * @param blue      blue color component (0-255)
     * @param size      base size of the flare (0.1+)
     * @param fadeIn    fade-in duration in seconds
     * @param duration  full-brightness duration in seconds
     * @param fadeOut   fade-out duration in seconds
     * @param trackable if true, creates a signal flare (floats); if false, illuminating (falls with gravity)
     */
    void addFlare(Player player, String id, float x, float y, float z,
                  int red, int green, int blue,
                  float size, float fadeIn, float duration, float fadeOut, boolean trackable);

    /**
     * Removes a flare by its identifier, triggering a smooth fade-out.
     *
     * @param player the target player
     * @param id     the flare identifier to remove
     */
    void removeFlare(Player player, String id);

    /**
     * Clears all active flares on the target player's client.
     *
     * @param player the target player
     */
    void clearFlares(Player player);
}
