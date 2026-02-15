package gg.lode.lecternapi.api.manager;

import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

/**
 * Manages camera effects for players running the Lectern client mod.
 * Controls camera position, rotation, field of view, screen shake, and perspective.
 */
public interface ICameraManager {

    /**
     * Moves the player's camera to a specific position and rotation.
     *
     * @param player the target player
     * @param position the world position to move the camera to
     * @param yaw the camera yaw rotation
     * @param pitch the camera pitch rotation
     * @param roll the camera roll rotation
     */
    void moveCamera(Player player, Vector position, float yaw, float pitch, float roll);

    /**
     * Stops the camera override and returns control to the player.
     */
    void stopCamera(Player player);

    /**
     * Sets the player's field of view modifier.
     *
     * @param player the target player
     * @param fov the FOV value
     */
    void setFov(Player player, float fov);

    /**
     * Resets the player's field of view to default.
     */
    void resetFov(Player player);

    /**
     * Starts a screen shake effect on the player.
     *
     * @param player the target player
     * @param durationTicks the duration in ticks
     * @param intensity the shake intensity
     */
    void screenshake(Player player, int durationTicks, float intensity);

    /**
     * Stops any active screen shake on the player.
     */
    void stopScreenshake(Player player);

    /**
     * Enables or disables smooth camera movement on the player.
     */
    void setSmoothCamera(Player player, boolean enabled);

    /**
     * Forces the player into third-person camera mode.
     */
    void setForceThirdPerson(Player player, boolean enabled);

    /**
     * Forces the player into shoulder surfing camera mode.
     */
    void setForceShoulderSurf(Player player, boolean enabled);

    /**
     * Enables or disables the body-follow-camera effect where the player's
     * body rotation follows the camera direction.
     */
    void setBodyFollowCam(Player player, boolean enabled);
}
