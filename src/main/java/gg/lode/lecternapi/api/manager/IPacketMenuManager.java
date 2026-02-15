package gg.lode.lecternapi.api.manager;

import gg.lode.lecternapi.api.menu.PacketMenu;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.Nullable;

/**
 * Manages opening, closing, and tracking {@link PacketMenu} instances for players.
 */
public interface IPacketMenuManager {

    /**
     * Opens a menu for a player. If the player already has a menu open, it is closed first.
     *
     * @param player the target player
     * @param menu   the menu to open
     */
    void open(Player player, PacketMenu menu);

    /**
     * Closes the currently open menu for a player, removing all elements.
     *
     * @param player the target player
     */
    void close(Player player);

    /**
     * Returns the currently open menu for a player, or null if none.
     */
    @Nullable
    PacketMenu getOpenMenu(Player player);

    /**
     * Returns whether the player has a menu open.
     */
    default boolean hasOpenMenu(Player player) {
        return getOpenMenu(player) != null;
    }
}
