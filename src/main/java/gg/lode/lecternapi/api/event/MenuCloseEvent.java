package gg.lode.lecternapi.api.event;

import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

/**
 * Fired when a player closes a Lectern client-side menu.
 */
public class MenuCloseEvent extends LecternClientEvent {

    private static final HandlerList HANDLERS = new HandlerList();

    public MenuCloseEvent(Player player) {
        super(player, "MENU_CLOSE");
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return HANDLERS;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }
}
