package gg.lode.lecternapi.api.manager;

import org.bukkit.entity.Player;

import java.util.UUID;

/**
 * Per-viewer tab-list decoration for players running the Lectern client mod: a styled
 * prefix and suffix around a player's tab name, and an explicit sort weight.
 * <p>
 * Unlike scoreboard teams — global, name-keyed, ordered by the alphabetical-team-name
 * hack — these decorations are per viewer (staff can see real ranks while players see
 * disguises), take full MiniMessage/JSON styling ({@code <gradient:gold:yellow>[MVP]}),
 * order by weight, and never touch the scoreboard, so they cannot collide with other
 * plugins' teams or leak a nick-spoofed player's real name through team entries.
 * <p>
 * Sorting: lower weight sorts higher; players without an entry sit at weight 0 in their
 * vanilla position. Weights re-order before the tab display cap, so a negative-weight
 * player is guaranteed a visible row on a full server.
 * <p>
 * Decorations compose with {@link INicknameManager} nicknames — the prefix wraps whatever
 * name the viewer is meant to see.
 */
public interface ITabListManager {

    /**
     * Sets a player's tab decoration as seen by the viewer.
     *
     * @param viewer     the player whose tab list changes
     * @param targetUuid the player being decorated
     * @param prefix     styled text before the name, or {@code ""}/null for none
     * @param suffix     styled text after the name, or {@code ""}/null for none
     * @param sortWeight lower sorts higher; 0 is the vanilla position
     */
    default void setTabEntry(Player viewer, UUID targetUuid, String prefix, String suffix, int sortWeight) {
    }

    /** Convenience overload taking the target player directly. */
    default void setTabEntry(Player viewer, Player target, String prefix, String suffix, int sortWeight) {
        setTabEntry(viewer, target.getUniqueId(), prefix, suffix, sortWeight);
    }

    /** Removes the target's decoration from the viewer's tab list. */
    default void removeTabEntry(Player viewer, UUID targetUuid) {
    }

    /** Removes every decoration from the viewer's tab list. */
    default void clearTabList(Player viewer) {
    }
}
