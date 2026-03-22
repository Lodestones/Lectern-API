package gg.lode.lecternapi.api.manager;

import gg.lode.lecternapi.api.prompt.ModalPromptButton;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Manages screen-level effects for players running the Lectern client mod.
 * Controls flash overlays, shaders, xray, and prevent-disconnect.
 */
public interface IScreenManager {

    /**
     * Triggers a screen flash effect on the player's client.
     *
     * @param player the target player
     * @param red red component (0-255)
     * @param green green component (0-255)
     * @param blue blue component (0-255)
     * @param alpha alpha component (0-255)
     * @param layer the render layer
     * @param durationIn fade-in duration in seconds
     * @param durationStay stay duration in seconds
     * @param durationOut fade-out duration in seconds
     */
    void flash(Player player, int red, int green, int blue, int alpha, int layer, float durationIn, float durationStay, float durationOut);

    /**
     * Enables or disables the xray effect on the player's client.
     *
     * @param player the target player
     * @param enabled whether to enable xray
     * @param range the render range in blocks (only used when enabling)
     */
    void setXray(Player player, boolean enabled, int range);

    /**
     * Adds a block type to the xray highlight list.
     *
     * @param player the target player
     * @param material the block identifier (e.g. "minecraft:iron_ore")
     * @param red red highlight color (0-255)
     * @param green green highlight color (0-255)
     * @param blue blue highlight color (0-255)
     */
    void addXrayBlock(Player player, String material, int red, int green, int blue);

    /**
     * Removes a block type from the xray highlight list.
     */
    void removeXrayBlock(Player player, String material);

    /**
     * Prevents the player from disconnecting or opening the pause menu.
     */
    void setPreventDisconnect(Player player, boolean enabled);

    /**
     * Forces a shader pack on the player's client. The shader pack must be available
     * as a ZIP file at {@code assets/lectern/shader-templates/<shaderName>.zip}, either
     * bundled in the Lectern mod or provided via a server resource pack.
     * <p>
     * While active, the player cannot switch away from the forced shader.
     *
     * @param player the target player
     * @param shaderName the shader pack name (e.g. "backrooms")
     */
    void forceShader(Player player, String shaderName);

    /**
     * Stops forcing a shader pack on the player's client and disables shaders.
     *
     * @param player the target player
     */
    void stopForceShader(Player player);

    /**
     * Enables the motion blur effect with the specified intensity.
     *
     * @param player the target player
     * @param intensity the blur intensity (0.0 to 1.0)
     */
    void startMotionBlur(Player player, float intensity);

    /**
     * Disables the motion blur effect.
     */
    void stopMotionBlur(Player player);

    /**
     * Enables or disables the outline shader (motion sense) effect.
     */
    void setOutlineShader(Player player, boolean enabled);

    /**
     * Displays a modal prompt on the player's client with a title, markdown content, and configurable buttons.
     *
     * @param player   the target player
     * @param promptId a unique identifier for this prompt instance
     * @param title    the window title
     * @param content  markdown-formatted body text
     * @param buttons  the list of buttons to display
     */
    void showModalPrompt(Player player, String promptId, String title, String content, List<ModalPromptButton> buttons);

    /**
     * Closes any currently displayed modal prompt on the player's client.
     *
     * @param player the target player
     */
    void closeModalPrompt(Player player);

    /**
     * Swaps the visual rendering of a target player's main hand and off hand items
     * as seen by the viewer. This is purely visual — it does not affect gameplay or actual item slots.
     *
     * @param viewer the player whose client will render the swap
     * @param target the UUID of the player whose hands should appear swapped
     * @param enabled whether to enable the swap effect
     */
    void setSwapHands(Player viewer, UUID target, boolean enabled);

    /**
     * Clears all swap hand effects for the viewer.
     *
     * @param viewer the player whose swap effects should be cleared
     */
    void clearSwapHands(Player viewer);

    /**
     * Requests a screenshot from the player's client. The screenshot is captured
     * silently and uploaded back to the server in chunks. When the transfer completes,
     * a {@link gg.lode.lecternapi.api.event.ClientScreenshotEvent} is fired with the
     * saved file.
     *
     * @param player the target player
     */
    void requestScreenshot(Player player);

    /**
     * Displays the player's hand held out with an item floating and spinning above it.
     * If {@code itemId} is empty, the player's currently held item is used.
     *
     * @param player    the target player
     * @param itemId    the item identifier (e.g. "minecraft:diamond_sword"), or empty string to use held item
     * @param spinSpeed spin speed multiplier (1.0 = default)
     * @param bobSpeed  vertical bob speed multiplier (1.0 = default)
     * @param bobHeight vertical bob amplitude (0.08 = default)
     */
    void showHandPose(Player player, String itemId, float spinSpeed, float bobSpeed, float bobHeight);

    /**
     * Displays the player's hand held out with an item floating and spinning above it,
     * with full control over arm and item transforms.
     *
     * @param player    the target player
     * @param itemId    the item identifier (e.g. "minecraft:diamond_sword"), or empty string to use held item
     * @param armX      arm X position offset (-0.25 default)
     * @param armY      arm Y position offset (-0.2 default)
     * @param armZ      arm Z position offset (-0.8 default)
     * @param armPitch  arm pitch rotation in degrees (-50 default)
     * @param armYaw    arm yaw rotation in degrees (-20 default)
     * @param armRoll   arm roll rotation in degrees (45 default)
     * @param itemX     item X position offset (-0.05 default)
     * @param itemY     item Y position offset (0.5 default)
     * @param itemZ     item Z position offset (-0.15 default)
     * @param itemScale item scale multiplier (0.3 default)
     * @param spinSpeed spin speed multiplier (2.0 default)
     * @param bobSpeed  vertical bob speed multiplier (1.5 default)
     * @param bobHeight vertical bob amplitude (0.03 default)
     */
    void showHandPose(Player player, String itemId,
                      float armX, float armY, float armZ,
                      float armPitch, float armYaw, float armRoll,
                      float itemX, float itemY, float itemZ, float itemScale,
                      float spinSpeed, float bobSpeed, float bobHeight);

    /**
     * Displays the player's hand held out with their currently held item floating
     * and spinning above it, using default animation values.
     *
     * @param player the target player
     */
    void showHandPose(Player player);

    /**
     * Stops the item showcase effect.
     *
     * @param player the target player
     */
    void stopHandPose(Player player);

    /**
     * Plays a named hand animation on the player's client. The animation must be loaded
     * via resource pack ({@code assets/lectern/animations/}) or previously sent inline.
     *
     * @param player    the target player
     * @param animationName the animation identifier (e.g. "animation.hand.inspect")
     * @param itemId    the item to display, or empty string to use held item
     * @param loop      whether the animation should loop
     * @param speed     playback speed multiplier (1.0 = normal)
     */
    void playHandAnimation(Player player, String animationName, String itemId, boolean loop, float speed);

    /**
     * Plays a named hand animation using the player's held item with default speed and no looping.
     *
     * @param player        the target player
     * @param animationName the animation identifier
     */
    void playHandAnimation(Player player, String animationName);

    /**
     * Stops the currently playing hand animation.
     *
     * @param player the target player
     */
    void stopHandAnimation(Player player);

    /**
     * Crossfades from the current hand animation to a new one over the specified duration.
     *
     * @param player        the target player
     * @param animationName the animation to transition to
     * @param blendDuration blend duration in seconds
     * @param speed         playback speed multiplier
     */
    void crossfadeHandAnimation(Player player, String animationName, float blendDuration, float speed);

    /**
     * Sends an animation JSON directly to the player's client, making it available
     * for playback without requiring a resource pack.
     *
     * @param player        the target player
     * @param animationName the name to register the animation under
     * @param animationJson the full bedrock animation JSON string
     */
    void loadInlineHandAnimation(Player player, String animationName, String animationJson);

    /**
     * Sets the carry pose on a target player as seen by the viewer.
     * The target player's arms will be raised above their head as if carrying a crate.
     * This is purely visual and does not affect gameplay.
     *
     * @param viewer  the player whose client will render the carry pose
     * @param target  the UUID of the player who should appear carrying
     * @param enabled whether to enable the carry pose
     */
    void setCarry(Player viewer, UUID target, boolean enabled);

    /**
     * Clears all carry poses for the viewer.
     *
     * @param viewer the player whose carry effects should be cleared
     */
    void clearCarry(Player viewer);

    /**
     * Freezes a target player's body movement as seen by the viewer.
     * The target's limb animations and body rotation will be locked in place,
     * making them appear frozen. This is purely visual on the viewer's client.
     *
     * @param viewer  the player whose client will render the frozen body
     * @param target  the UUID of the player whose body should appear frozen
     * @param enabled whether to enable the freeze effect
     */
    void setFreezePlayerBody(Player viewer, UUID target, boolean enabled);

    /**
     * Clears all freeze player body effects for the viewer.
     *
     * @param viewer the player whose freeze effects should be cleared
     */
    void clearFreezePlayerBody(Player viewer);

    /**
     * Plays a named body animation on a target player as seen by the viewer.
     * The animation must be loaded via resource pack or previously sent inline.
     *
     * @param viewer        the player whose client will render the animation
     * @param target        the UUID of the player to animate
     * @param animationName the animation identifier
     * @param loop          whether the animation should loop
     * @param speed         playback speed multiplier (1.0 = normal)
     */
    void playBodyAnimation(Player viewer, UUID target, String animationName, boolean loop, float speed);

    /**
     * Plays a named body animation with default speed and no looping.
     *
     * @param viewer        the player whose client will render the animation
     * @param target        the UUID of the player to animate
     * @param animationName the animation identifier
     */
    void playBodyAnimation(Player viewer, UUID target, String animationName);

    /**
     * Stops the body animation on a target player as seen by the viewer.
     *
     * @param viewer the player whose client will stop rendering the animation
     * @param target the UUID of the player whose animation should stop
     */
    void stopBodyAnimation(Player viewer, UUID target);

    /**
     * Crossfades from the current body animation to a new one on a target player.
     *
     * @param viewer        the player whose client will render the transition
     * @param target        the UUID of the player to animate
     * @param animationName the animation to transition to
     * @param blendDuration blend duration in seconds
     * @param speed         playback speed multiplier
     */
    void crossfadeBodyAnimation(Player viewer, UUID target, String animationName, float blendDuration, float speed);

    /**
     * Clears all body animations for the viewer.
     *
     * @param viewer the player whose body animation effects should be cleared
     */
    void clearBodyAnimations(Player viewer);

    /**
     * Sends a body animation JSON directly to the player's client, making it available
     * for playback without requiring a resource pack.
     *
     * @param viewer        the target player
     * @param animationName the name to register the animation under
     * @param animationJson the full bedrock animation JSON string
     */
    void loadInlineBodyAnimation(Player viewer, String animationName, String animationJson);

    /**
     * Sets state-based body animations for a target player. The client automatically
     * detects the target's movement state (idle, walking, sprinting, etc.) and plays
     * the corresponding animation, crossfading between states.
     *
     * <p>Valid state names: IDLE, WALK, SPRINT, JUMP, FALL, SWIM, SNEAK, RIDE</p>
     *
     * @param viewer           the player whose client will render the animations
     * @param target           the UUID of the player to animate
     * @param stateAnimations  map of state name to animation name
     * @param crossfadeDuration blend duration in seconds when transitioning between states
     */
    void setBodyAnimationStates(Player viewer, UUID target, Map<String, String> stateAnimations, float crossfadeDuration);

    /**
     * Clears state-based body animations for a target player.
     *
     * @param viewer the player whose client will stop state animations
     * @param target the UUID of the player whose state animations should stop
     */
    void clearBodyAnimationStates(Player viewer, UUID target);

    /**
     * Plays the totem pop-up screen animation on the player's client.
     * This is the vanilla totem of undying animation (item zooms into center of screen
     * and fades out) without spawning any particles.
     *
     * @param player the target player
     * @param itemId the item identifier to display (e.g. "minecraft:totem_of_undying",
     *               "minecraft:diamond_sword"), or empty string for the default totem
     */
    void playTotemScreen(Player player, String itemId);

    /**
     * Plays the default totem of undying screen animation on the player's client.
     *
     * @param player the target player
     */
    void playTotemScreen(Player player);
}
