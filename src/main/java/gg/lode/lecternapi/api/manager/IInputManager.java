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
     * Lets a player move through blocks without leaving the game mode they are in.
     *
     * <p>Spectator is the only mode where vanilla turns collision off, and entering it costs the
     * hotbar, the hand and every interaction. This gives the same freedom of movement while the
     * player stays exactly who they were — for a replay's viewers, who need to get inside a
     * building to watch a fight and still hold the controls the replay hands them.
     *
     * <p>Flight comes with it: a player with collision off and their feet on nothing would fall
     * until the world ran out.
     *
     * <p><b>The caller owns the server's half.</b> Collision is decided on the client, but the
     * server independently rejects a player who ends up inside a block and teleports them back —
     * so this must be paired with {@code player.setNoPhysics(true)}, or the client will pass
     * through the wall and be pulled straight back out of it. Setting it back to false belongs with
     * turning this off.
     *
     * <p>A no-op fallback so a consumer built against this interface still links against an older
     * implementation; the real implementation overrides it.
     *
     * @param player the target player
     * @param enabled true to pass through blocks, false to collide again
     */
    default void setNoClip(Player player, boolean enabled) {
    }

    /**
     * The same, but only while the player is actually flying.
     *
     * <p>The player decides, tick by tick, by flying or not: in the air they pass through the
     * world, and the moment they drop out of flight they stand on it again. Usually what is wanted
     * — it keeps walking, landing and standing on things intact, and makes passing through a wall
     * something done on purpose rather than the only way to move.
     *
     * <p>Decided on the client from its own abilities rather than asked of the server, so the
     * changeover happens on the tick the key is pressed rather than a packet later. The server's
     * half should be conditioned the same way: allow the movement only while the player is flying.
     *
     * <p>A no-op fallback so a consumer built against this interface still links against an older
     * implementation; the real implementation overrides it.
     *
     * @param player the target player
     * @param enabled true to pass through blocks while flying, false to collide again
     */
    default void setNoClipWhileFlying(Player player, boolean enabled) {
    }


    /**
     * Passing through blocks with gravity left alone, so the player falls through the floor and
     * keeps falling. Flight is untouched, unlike {@link #setNoClip(Player, boolean)}, which takes it
     * over to stop exactly this.
     *
     * <p>Nothing catches them. Whoever turns this on is expected to be watching, and to put them
     * somewhere before the world runs out.
     *
     * @param player the target player
     * @param enabled true to fall through the world, false to collide again
     */
    default void setNoClipFalling(Player player, boolean enabled) {
        // Backward-compatible no-op fallback; the real implementation overrides this.
    }
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
     * Registers a keybind at a position within its category on the controls screen,
     * starting at index 0. Keys with the same index sort alphabetically; keybinds
     * registered without an index sit at 0.
     */
    default void registerKeybind(Player player, String id, String translationKey, int defaultKey, String category, int sortIndex) {
        registerKeybind(player, id, translationKey, defaultKey, category);
    }

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

    /**
     * Disables or enables 1.21.11 spear usage (lunge + attack) on the player's
     * client. When disabled, any item in the {@code #minecraft:spears} tag cannot
     * be lunged or used until re-enabled. Tracked, so it survives a reconnect.
     *
     * @param player   the target player
     * @param disabled true to block spear usage, false to restore it
     */
    void setDisableSpearUsage(Player player, boolean disabled);

    /**
     * Disables or enables the end-gateway light beam render on the player's
     * client. When disabled, end gateway blocks no longer render their vertical
     * beam. Tracked, so it survives a reconnect.
     *
     * @param player   the target player
     * @param disabled true to hide the end-gateway beam, false to restore it
     */
    void setDisableEndBeam(Player player, boolean disabled);
}
