package gg.lode.lecternapi.api.manager;

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
}
