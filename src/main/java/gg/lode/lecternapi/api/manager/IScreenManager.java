package gg.lode.lecternapi.api.manager;

import gg.lode.lecternapi.api.prompt.ModalPromptButton;
import org.bukkit.entity.Player;

import java.util.List;
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
}
