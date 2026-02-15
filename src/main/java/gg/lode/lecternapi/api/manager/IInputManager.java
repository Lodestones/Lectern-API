package gg.lode.lecternapi.api.manager;

import gg.lode.lecternapi.api.data.Keybind;
import org.bukkit.entity.Player;

/**
 * Manages input effects for players running the Lectern client mod.
 * Controls keybind disabling, inverting, and custom keybind registration.
 */
public interface IInputManager {

    /**
     * Disables a keybind on the player's client.
     *
     * @param player the target player
     * @param keybind the keybind to disable
     */
    void disableKeybind(Player player, Keybind keybind);

    /**
     * Re-enables a previously disabled keybind on the player's client.
     *
     * @param player the target player
     * @param keybind the keybind to re-enable
     */
    void enableKeybind(Player player, Keybind keybind);

    /**
     * Clears all disabled keybinds, restoring normal input.
     */
    void clearDisabledKeybinds(Player player);

    /**
     * Inverts all keybinds on the player's client.
     * W/S, A/D, sneak/jump, and attack/use are swapped.
     *
     * @param player the target player
     * @param inverted true to invert, false to restore normal input
     */
    void setKeybindsInverted(Player player, boolean inverted);

    /**
     * Disables the player's ability to interact with entity hitboxes.
     */
    void setDisableHitbox(Player player, boolean disabled);

    /**
     * Locks or unlocks the player's head/camera movement.
     * When locked, the player cannot rotate their camera regardless of mouse input.
     *
     * @param player the target player
     * @param locked true to lock head movement, false to unlock
     */
    void setHeadLocked(Player player, boolean locked);

    /**
     * Disables or enables chunk reload on the player's client.
     * When disabled, the client will not reload chunks.
     *
     * @param player the target player
     * @param disabled true to disable chunk reload, false to enable
     */
    void setDisableChunkReload(Player player, boolean disabled);

    /**
     * Disables or enables chunk boundary rendering on the player's client.
     * When disabled, the client will not render chunk boundaries (F3+G).
     *
     * @param player the target player
     * @param disabled true to disable chunk boundaries, false to enable
     */
    void setDisableChunkBoundaries(Player player, boolean disabled);

    // --- Custom Keybinds ---

    /**
     * Registers a custom keybind on the player's client.
     * The keybind appears in the player's controls settings and fires {@code KeybindPressedEvent} when pressed.
     * If a keybind with the same ID already exists, it is replaced.
     *
     * @param player the target player
     * @param id a unique identifier for this keybind (e.g. "myplugin:ability")
     * @param translationKey the display name / translation key shown in controls (e.g. "Use Ability")
     * @param defaultKey the default GLFW key code (e.g. {@code GLFW.GLFW_KEY_G} = 71)
     * @param category the category name in controls (e.g. "My Plugin")
     */
    void registerKeybind(Player player, String id, String translationKey, int defaultKey, String category);

    /**
     * Unregisters a custom keybind from the player's client.
     *
     * @param player the target player
     * @param id the keybind identifier to remove
     */
    void unregisterKeybind(Player player, String id);

    /**
     * Clears all custom keybinds on the player's client.
     *
     * @param player the target player
     */
    void clearKeybinds(Player player);
}
