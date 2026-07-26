package gg.lode.lecternapi.api.manager;

import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import java.util.UUID;

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

    /**
     * Forces zoom on or off for the player. When forced on, the client behaves
     * as if the zoom keybind is held until forced off.
     *
     * @param player the target player
     * @param enabled true to force zoom on, false to release
     */
    void setZoom(Player player, boolean enabled);

    /**
     * Removes the &plusmn;90&deg; pitch clamp on the player's camera, letting it keep
     * rotating past straight up or straight down and come out upside down behind them.
     * <p>
     * Uses the sensible defaults for the compensations: inverted mouse look while upside
     * down, normal walk direction, inverted walk direction while swimming.
     *
     * @param enabled whether the pitch clamp is lifted
     */
    default void setUnlockedCamera(Player player, boolean enabled) {
        setUnlockedCamera(player, enabled, true, false, true);
    }

    /**
     * Removes the &plusmn;90&deg; pitch clamp on the player's camera, with control over the
     * compensations applied once the view passes vertical.
     * <p>
     * Past vertical the camera faces opposite the player's yaw, so mouse look, walk
     * direction and the sprint-jump impulse all read as mirrored. Each switch below undoes
     * that for one of them; turn them off if you want the raw, disorienting behaviour.
     *
     * @param enabled whether the pitch clamp is lifted
     * @param invertMouse flip horizontal look while upside down, so dragging right still turns right
     * @param invertMovement flip walk direction while upside down
     * @param invertMovementSwimming flip walk direction while upside down and swimming, where
     *                               looking past vertical is a normal way to move
     */
    default void setUnlockedCamera(Player player, boolean enabled, boolean invertMouse, boolean invertMovement, boolean invertMovementSwimming) {
        // Backward-compatible no-op fallback; the real implementation overrides this.
    }

    /**
     * Puts the viewer's camera behind Verity's face while it's being carried — the held
     * ball's point of view, looking back up at whoever picked it up.
     * <p>
     * Engages by itself once the holder has a Verity-tagged item in their main hand, and
     * releases when they put it away, so it can be armed ahead of the moment it's needed.
     *
     * @param player the player whose camera is taken over
     * @param holder whose hand the camera rides in; null for the viewer's own hand
     */
    default void setVerityCam(Player player, UUID holder) {
        setVerityCam(player, holder, false, true, true, 0.45f, 0.32f, -0.28f, 0.0f, 0.0f);
    }

    /**
     * Puts the viewer's camera behind Verity's face, with full control over where the ball
     * sits and where it aims.
     * <p>
     * The viewer and the holder are independent — the camera is placed entirely on the
     * viewing client, so one player can be put behind the ball in another player's hand.
     * <p>
     * Hand offsets are relative to the holder's eyes and rotate with them: {@code forward}
     * out along their look, {@code side} to their right (negated for the off hand),
     * {@code up} vertically. The defaults approximate where a held item renders; raise
     * {@code forward} to pull the camera away from the holder's face.
     *
     * @param player the player whose camera is taken over
     * @param holder whose hand the camera rides in; null for the viewer's own hand
     * @param offHand ride the off hand instead of the main hand
     * @param requireHeldItem only engage while the holder actually has a Verity-tagged item
     * @param lookAtHolder aim back at the holder's face; false aims out along their look direction
     * @param yawOffset degrees added to the resulting aim
     * @param pitchOffset degrees added to the resulting aim
     */
    default void setVerityCam(Player player, UUID holder, boolean offHand, boolean requireHeldItem,
                              boolean lookAtHolder, float forward, float side, float up,
                              float yawOffset, float pitchOffset) {
        // Backward-compatible no-op fallback; the real implementation overrides this.
    }

    /**
     * Returns the viewer's camera to normal.
     */
    default void stopVerityCam(Player player) {
        // Backward-compatible no-op fallback; the real implementation overrides this.
    }
}
