package gg.lode.lecternapi.api.manager;

import org.bukkit.entity.Player;

/**
 * Manages ping effects for players running the Lectern client mod.
 * Controls world-space ping markers and sound visualization pings.
 */
public interface IPingManager {

    // --- Pings ---

    /**
     * Adds a ping marker at a world position, visible to the target player.
     * Renders a ping icon with a title label, distance readout, and animated wave pulse.
     *
     * @param player the target player
     * @param id a unique identifier for this ping
     * @param title the text displayed above the ping
     * @param x the world x coordinate
     * @param y the world y coordinate
     * @param z the world z coordinate
     */
    void addPing(Player player, String id, String title, float x, float y, float z);

    /**
     * Removes a ping marker by its identifier.
     *
     * @param player the target player
     * @param id the ping identifier to remove
     */
    void removePing(Player player, String id);

    /**
     * Clears all ping markers on the target player's client.
     *
     * @param player the target player
     */
    void clearPings(Player player);

    // --- Sound Pings ---

    /**
     * Sends a sound ping to the player's client, rendering a marker at the
     * specified world coordinates.
     *
     * @param player the target player
     * @param x the world x coordinate
     * @param y the world y coordinate
     * @param z the world z coordinate
     * @param timeLeft duration in ticks before the ping expires
     * @param textureId the texture identifier for the ping icon
     */
    void sendSoundPing(Player player, double x, double y, double z, int timeLeft, String textureId);
}
