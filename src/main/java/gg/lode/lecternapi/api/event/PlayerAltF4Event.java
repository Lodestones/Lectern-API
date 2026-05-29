package gg.lode.lecternapi.api.event;

import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

/**
 * Fired when a Lectern client presses Alt+F4 to close the game window.
 * The event fires before the window actually closes; the client will
 * still proceed to disconnect unless other client-side effects block it.
 */
public class PlayerAltF4Event extends LecternClientEvent {

    private static final HandlerList HANDLERS = new HandlerList();

    public PlayerAltF4Event(Player player) {
        super(player, "ALT_F4");
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return HANDLERS;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }
}
