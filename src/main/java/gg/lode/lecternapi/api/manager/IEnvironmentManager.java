package gg.lode.lecternapi.api.manager;

import gg.lode.lecternapi.api.data.FlashlightMode;
import org.bukkit.entity.Player;

/**
 * Manages environment effects for players running the Lectern client mod.
 * Controls fog, sky, moon colors, darkness, and fog density.
 */
public interface IEnvironmentManager {

    /**
     * Overrides the fog color on the player's client.
     *
     * @param player the target player
     * @param red red component (0-255)
     * @param green green component (0-255)
     * @param blue blue component (0-255)
     */
    void setFogColor(Player player, int red, int green, int blue);

    /**
     * Resets the fog color to the default.
     */
    void resetFogColor(Player player);

    /**
     * Overrides the sky color on the player's client.
     */
    void setSkyColor(Player player, int red, int green, int blue);

    /**
     * Resets the sky color to the default.
     */
    void resetSkyColor(Player player);

    /**
     * Overrides the moon color on the player's client.
     */
    void setMoonColor(Player player, int red, int green, int blue);

    /**
     * Resets the moon color to the default.
     */
    void resetMoonColor(Player player);

    /**
     * Enables or disables the true darkness effect, which overrides the
     * lightmap to create complete darkness.
     */
    void setTrueDarkness(Player player, boolean enabled);

    /**
     * Enables or disables dynamic lighting on the player's client. When enabled,
     * entities holding light-emitting items (torches, lanterns, glowstone, etc.)
     * cast block-light onto nearby surfaces.
     */
    void setDynamicLighting(Player player, boolean enabled);

    /**
     * Gives the player a flashlight: a cone of light thrown from their head along the direction
     * they are looking, with the world around them dimmed so the beam is what shows them anything.
     *
     * <p>Other players running the client mod see this player's beam too. They are told who is
     * carrying one and nothing else, since their own client already tracks where everyone is and
     * which way they face, so the beam stays smooth without a packet per tick.
     *
     * <p>How good it looks depends on what the viewer has installed. With Iris the client forces
     * Lectern's own shader pack, which lights surfaces properly and can take the world to black.
     * Without it the client falls back to a post-processing pass that can only brighten what is
     * already on screen, so the beam is dimmer and the dark is never total.
     *
     * @param player the target player
     * @param enabled whether this player carries a flashlight
     */
    default void setFlashlight(Player player, boolean enabled) {
        // Default rather than abstract so a loader shading this interface still links against an
        // older impl blob, the same reason disconnectPlayer is a default on ILecternAPI.
    }

    /**
     * As {@link #setFlashlight(Player, boolean)}, with the beam tuned.
     *
     * @deprecated The client owns the shape of the beam and ignores these. It draws every beam in
     *     the room itself, per pixel, rather than through a shader pack, and the shape is a
     *     judgement about how a room feels that can only be made while looking at one: it is tuned
     *     in game with {@code /lecternc flashlight tune} and the values that come out of that are
     *     what ships. A server sending its own guess pulled it back out of shape, and did it
     *     differently on each server. Still sent and still harmless, so callers keep linking; the
     *     values are read off the packet and dropped. Use
     *     {@link #setFlashlight(Player, FlashlightMode)} instead.
     *
     * @param range how far the beam reaches before it fades out, in blocks
     * @param strength overall power of the beam
     * @param volumetric how visible the beam is hanging in the air, 0.0 for clean air
     * @param red red component of the beam colour (0-255)
     * @param green green component of the beam colour (0-255)
     * @param blue blue component of the beam colour (0-255)
     */
    @Deprecated
    default void setFlashlight(Player player, boolean enabled, float range, float strength,
                               float volumetric, int red, int green, int blue) {
    }

    /**
     * As {@link #setFlashlight(Player, boolean)}, choosing between a steady beam and one that
     * flickers. {@link FlashlightMode#OFF} is the same as passing {@code false}.
     */
    default void setFlashlight(Player player, FlashlightMode mode) {
    }

    /**
     * As {@link #setFlashlight(Player, FlashlightMode)}, with the beam tuned.
     *
     * @deprecated The client owns the shape and ignores these, for the reasons given on
     *     {@link #setFlashlight(Player, boolean, float, float, float, int, int, int)}. Use
     *     {@link #setFlashlight(Player, FlashlightMode)} instead.
     */
    @Deprecated
    default void setFlashlight(Player player, FlashlightMode mode, float range, float strength,
                               float volumetric, int red, int green, int blue) {
    }

    /** Whether this player is currently carrying a flashlight. */
    default boolean hasFlashlight(Player player) {
        return false;
    }

    /** What state this player's flashlight is in. */
    default FlashlightMode getFlashlightMode(Player player) {
        return FlashlightMode.OFF;
    }

    /**
     * Enables dense fog rendering on the player's client.
     *
     * @param player the target player
     * @param fogStart the distance at which fog begins
     * @param fogEnd the distance at which fog is fully opaque
     * @param alpha the fog opacity (0.0 to 1.0)
     */
    void startDenseFog(Player player, float fogStart, float fogEnd, float alpha);

    /**
     * Disables dense fog rendering on the player's client.
     */
    void stopDenseFog(Player player);

    /**
     * Forces fullbright (night vision without UI overlay) on or off for the player.
     *
     * @param player the target player
     * @param enabled true to enable fullbright, false to disable
     */
    void setFullbright(Player player, boolean enabled);
}
